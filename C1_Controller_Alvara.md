```java
package br.gov.ba.saj.smtt.cadmototaxistas.reports.controller;

import java.io.IOException;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.gov.ba.saj.smtt.cadmototaxistas.reports.enums.TipoRelatorio;
import br.gov.ba.saj.smtt.cadmototaxistas.reports.service.JasperService;

/**
 * Controlador responsável por gerenciar as requisições relacionadas à geração
 * de relatórios JasperReports.
 * <p>
 * Esta classe possui endpoints para visualização e download de relatórios em
 * formato PDF.
 * </p>
 *
 * @author: Albert Silva
 */
@Controller
@RequestMapping("/relatorios")
public class JasperController {

    @Autowired
    private JasperService jasperService;

    /**
     * Exibe a página HTML com a lista de opções de relatórios disponíveis.
     *
     * @return o nome da view (template) correspondente à listagem de relatórios.
     */
    @GetMapping("/lista")
    public String listarRelatorios() {
        return "relatorio/lista";
    }

    /**
     * Gera e exibe ou realiza o download de um relatório PDF JasperReports conforme
     * os parâmetros fornecidos.
     *
     * @param code     código do relatório a ser gerado (deve estar mapeado no
     *                 serviço).
     * @param acao     define a ação: "v" para visualizar inline ou "d" para
     *                 download.
     * @param tipo     tipo do relatório a ser gerado, conforme o enum
     *                 {@link TipoRelatorio}.
     * @param response objeto {@link HttpServletResponse} usado para configurar e
     *                 enviar a resposta HTTP.
     * @throws IOException se ocorrer falha ao escrever a resposta HTTP.
     */
    @GetMapping("/pdf/condutores")
    public void exibirRelatorio01(
            @RequestParam("code") String code,
            @RequestParam("acao") String acao,
            @RequestParam("tipo") TipoRelatorio tipo,
            HttpServletResponse response) throws IOException {

        try {
            byte[] bytes = jasperService.gerarPDF(code, tipo);

            if (bytes == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Relatório não encontrado.");
                return;
            }

            response.setContentType(MediaType.APPLICATION_PDF_VALUE);

            // Define o cabeçalho com base na ação: visualizar ("v") ou baixar
            if (acao.equals("v")) {
                response.setHeader("Content-disposition", "inline; filename=relatorio-" + code + ".pdf");
            } else {
                response.setHeader("Content-disposition", "attachment; filename=" + tipo + "-" + code + ".pdf");
            }

            response.getOutputStream().write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao gerar o relatório.");
        }
    }

}
```
