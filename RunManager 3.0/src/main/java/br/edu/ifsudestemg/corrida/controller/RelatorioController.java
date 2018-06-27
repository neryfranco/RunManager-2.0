package br.edu.ifsudestemg.corrida.controller;

import br.edu.ifsudestemg.corrida.repository.CorridaRepository;
import br.edu.ifsudestemg.corrida.repository.OrganizadorRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/relatorio")
public class RelatorioController {

    @GetMapping(value = "")
    public String relatorios(Model model) {
        return "relatorio/pesquisar";
    }

    public Connection abrirConexao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/corrida", "root", "");

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public JasperReport gerarJasper(String relatorio, String nome) throws JRException {
        InputStream relatorioStream = getClass().getResourceAsStream(relatorio);

        if (relatorioStream == null)
            return null;

        JasperReport jasperReport = JasperCompileManager.compileReport(relatorioStream);
        JRSaver.saveObject(jasperReport, nome + ".jasper");
        return jasperReport;
    }


    public JasperPrint exibirRelatorio(JasperReport relatorio, HashMap parametros, Connection conexao) throws JRException {
        return JasperFillManager.fillReport(relatorio, parametros, conexao);
    }

    @GetMapping(value = "{nomeRelatorio}")
    public void gerarRelatorio(HttpServletRequest request, HttpServletResponse response, @PathVariable String nomeRelatorio) throws JRException, IOException, SQLException {
        Connection conexao = this.abrirConexao();

        JasperReport relatorio = this.gerarJasper("/reports/" + nomeRelatorio + ".jrxml", nomeRelatorio);
        if (relatorio != null) {
            HashMap parametros = new HashMap();
            byte[] relat = JasperExportManager.exportReportToPdf(this.exibirRelatorio(relatorio, parametros, conexao));
            response.setHeader("Content-Disposition", "attachment;filename=" + nomeRelatorio + ".pdf");
            response.setContentType("application/pdf");
            response.getOutputStream().write(relat);
        }
        conexao.close();

    }


    @GetMapping(value = "gerar/{report}/{tipo}/{parametro}")// site.com/administrador/edit/1/
    public void gerarRelatorioCorridaEstado(HttpServletRequest request, HttpServletResponse response,
                                            @PathVariable String report,@PathVariable String tipo,
                                            @PathVariable String parametro) throws JRException, IOException, SQLException {
        String nomeRelatorio = report;
        Connection conexao = this.abrirConexao();

        JasperReport relatorio = this.gerarJasper("/reports/" + nomeRelatorio + ".jrxml", nomeRelatorio);
        if (relatorio != null) {
            HashMap parametros = new HashMap();

            parametros.put("P_"+tipo, parametro);

            JasperPrint JP = JasperFillManager.fillReport(relatorio, parametros, conexao);
            byte[] relat = JasperExportManager.exportReportToPdf(this.exibirRelatorio(relatorio, parametros, conexao));
            response.setHeader("Content-Disposition", "attachment;filename=" + nomeRelatorio + ".pdf");
            response.setContentType("application/pdf");
            response.getOutputStream().write(relat);
        }
        conexao.close();
    }

}
