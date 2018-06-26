/*
package br.edu.ifsudestemg.corrida.controller;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRSaver;
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

    public JasperReport gerarJasper(String relatorio, String nome) throws JRException {
        InputStream relatorioStream = getClass().getResourceAsStream(relatorio);
        System.out.println("5******************************************");
        if (relatorioStream == null)
            return null;

        JasperReport jasperReport = JasperCompileManager.compileReport(relatorioStream);
        JRSaver.saveObject(jasperReport, nome + ".jasper");
        return jasperReport;
    }

    public Connection abrirConexao() {
        try {
            System.out.println("4******************************************");
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/corrida", "root", "");

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public JasperPrint exibirRelatorio(JasperReport relatorio, HashMap parametros, Connection conexao) throws JRException {
        // parametros.put("PAR_codCurso", Integer.parseInt(request.getParameter("txtCodCurso")));
        System.out.println("3******************************************");
        return JasperFillManager.fillReport(relatorio, parametros, conexao);
    }

    @GetMapping(value = "")
    public String percursos(Model model){
        System.out.println("2******************************************");
        return "relatorio/pesquisar";
    }

    @GetMapping(value = "{report}")
    public void gerarRelatorio(HttpServletRequest request, HttpServletResponse response, @PathVariable String report) throws JRException, IOException, SQLException {
        String nomeRelatorio = report;
        System.out.println("1******************************************");
        System.out.printf(report);
        Connection conexao = this.abrirConexao();


        JasperReport relatorio = this.gerarJasper("/reports/" + nomeRelatorio + ".jrxml", nomeRelatorio);
        if (relatorio != null) {
            HashMap parametros = new HashMap();
            this.exibirRelatorio(relatorio, parametros, conexao);
            byte[] relat = JasperExportManager.exportReportToPdf(this.exibirRelatorio(relatorio, parametros, conexao));
            response.setHeader("Content-Disposition", "attachment;filename=" + nomeRelatorio + ".pdf");
            response.setContentType("application/pdf");
            response.getOutputStream().write(relat);
        }
        conexao.close();

    }
}
*/