/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Chip;
import modelo.Corrida;
import modelo.Ingresso;
import modelo.Kit;
import modelo.Lote;
import modelo.Pagamento;
import modelo.Percurso;

/**
 *
 * @author Nery
 */
public class ManterInscricaoController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        String acao = request.getParameter("acao");
        if (acao.equals("prepararInscricao")) {
            prepararInscricao(request, response);
        } else if (acao.equals("prepararInformacoes")) {
            prepararInformacoes(request, response);
        //} else if (acao.equals("confirmarInscricao")) {
        //    confirmarInscricao(request, response);
        }
    }
    
    public void prepararInscricao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        try {
            request.setAttribute("operacao", "Inscricao");
            request.setAttribute("percursos", Percurso.obterPercursos());
            request.setAttribute("lotes", Lote.obterLotes());
            int idCorrida = Integer.parseInt(request.getParameter("codCorrida"));
            Corrida corrida = Corrida.obterCorrida(idCorrida);
            request.setAttribute("corrida", corrida);
            RequestDispatcher view = request.getRequestDispatcher("/manterInscricao.jsp");
            view.forward(request, response);
        } catch (ServletException e) {
        }
    }

    private void prepararInformacoes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        try {
            request.setAttribute("operacao", "Informacoes");
            request.setAttribute("percursos", Percurso.obterPercursos());
            request.setAttribute("lotes", Lote.obterLotes());
            int idCorrida = Integer.parseInt(request.getParameter("codCorrida"));
            Corrida corrida = Corrida.obterCorrida(idCorrida);
            request.setAttribute("corrida", corrida);
            RequestDispatcher view = request.getRequestDispatcher("/manterInscricao.jsp");
            view.forward(request, response);
        } catch (ServletException e) {
        }
    }
    
    public void confirmarInscricao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        String camisa = request.getParameter("optCamisa");
        String metodo = request.getParameter("optMetodo");
        int lote_id = Integer.parseInt(request.getParameter("optLote"));
        int percurso_id = Integer.parseInt(request.getParameter("optPercurso"));
        
        Percurso percurso = null;
        if (percurso_id != 0) {
            percurso = Percurso.obterPercurso(percurso_id);
        }
        
        Lote lote = null;
        if (lote_id != 0) {
            lote = Lote.obterLote(lote_id);
        }
        
        Chip chip = new Chip (0, 0, percurso);
        Kit kit = new Kit (0, chip, camisa);
        Ingresso ingresso = new Ingresso(lote, 1, kit, null);
        Pagamento pagamento = new Pagamento(0, metodo, lote.getPreco(), ingresso);
        //chip.gravar();
        //kit.gravar();
        ingresso.gravar();
        pagamento.gravar();
        RequestDispatcher view = request.getRequestDispatcher("PesquisaCorridaController");
        view.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ManterInscricaoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterInscricaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ManterInscricaoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterInscricaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
