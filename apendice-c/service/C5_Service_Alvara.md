<h2 align="center">CAD-MOTOTAXISTA - Documentação Técnica</h2>

<br>

### Service do Módulo de Alvará

O **Service** desta subseção é responsável pela **geração dos relatórios JasperReports** utilizados pelo sistema, incluindo a emissão de alvarás dos mototaxistas e motofretistas vinculados ao cadastro de condutores e motocicletas.

Principais responsabilidades:

- Integrar com o mecanismo de geração de relatórios (.jasper);
- Injetar parâmetros e estabelecer conexão com o banco de dados;
- Centralizar as regras de geração, garantindo rastreabilidade, padronização e manutenção simplificada dos relatórios.

---

```java
package br.gov.ba.saj.smtt.cadmototaxistas.reports.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import br.gov.ba.saj.smtt.cadmototaxistas.reports.enums.TipoRelatorio;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * Serviço responsável pela geração de relatórios utilizando JasperReports.
 *
 * <p>
 * Os arquivos `.jasper` devem estar disponíveis no classpath do projeto,
 * dentro do diretório {@code resources/reports}, e devem seguir o padrão de
 * nomenclatura {@code tipos-sanguineo-{code}.jasper}.
 * </p>
 *
 * <p>
 * Este serviço permite a geração de relatórios em PDF, com suporte a
 * parâmetros dinâmicos.
 * </p>
 *
 * @author: Albert Silva
 */
@Service
public class JasperService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JasperService.class);

    private static final String JASPER_DIRETORIO = "classpath:reports/";
    private static final String JASPER_SUFIXO = ".jasper";

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private DataSource dataSource;

    /**
     * Cria um mapa com os parâmetros padrão usados em todos os relatórios.
     *
     * @return mapa contendo os parâmetros padrão (imagens, logos, etc.).
     */
    private Map<String, Object> criarParametrosPadrao() {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("LOGO_SINALEIRA", JASPER_DIRETORIO);
        return parametros;
    }

    /**
     * Gera um relatório JasperReports em formato PDF com apenas os parâmetros
     * padrão (LOGO_SINALEIRA e IMAGEM_MOTO).
     *
     * @param code o código do relatório a ser gerado (ex: "grupo-sanguineo").
     * @param tipo o tipo de relatório a ser gerado, conforme o enum {@link TipoRelatorio}.
     * @return um array de bytes representando o conteúdo do PDF gerado.
     * @throws RuntimeException em caso de falha na geração.
     */
    public byte[] gerarPDF(String code, TipoRelatorio tipo) {
        return gerarPDF(code, tipo, null);
    }

    /**
     * Gera um relatório JasperReports em formato PDF com parâmetros adicionais.
     *
     * @param code o código do relatório a ser gerado (ex:"grupo-sanguineo").
     * @param tipo o tipo de relatório a ser gerado, conforme o enum {@link TipoRelatorio}.
     * @param parametrosExtras parâmetros adicionais a serem utilizados no relatório.
     * @return um array de bytes representando o conteúdo do PDF gerado.
     * @throws RuntimeException em caso de falha na geração.
     */
    public byte[] gerarPDF(String code, TipoRelatorio tipo, Long parametrosExtras) {
        byte[] bytes = null;
        try {
            Map<String, Object> parametros = criarParametrosPadrao();
        } catch (IOException | JRException | SQLException e) {
            LOGGER.error("Erro ao gerar relatório Jasper ::: ", e);
            throw new RuntimeException(e);
        }
        return bytes;
    }
}
```
