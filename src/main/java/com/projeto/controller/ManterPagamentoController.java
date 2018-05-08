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
import modelo.Ingresso;
import modelo.Pagamento;

/**
 *
 * @author Nery
 */
public class ManterPagamentoController extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        String acao = request.getParameter("acao");
        if (acao.equals("prepararIncluir")) {
            prepararIncluir(request, response);
        } else if (acao.equals("confirmarIncluir")) {
            confirmarIncluir(request, response);
        } else if (acao.equals("prepararExcluir")) {
            prepararExcluir(request, response);
        } else if (acao.equals("confirmarExcluir")) {
            confirmarExcluir(request, response);
        } else if (acao.equals("prepararEditar")) {
            prepararEditar(request, response);
        } else if (acao.equals("confirmarEditar")) {
            confirmarEditar(request, response);
        }
    }

    public void prepararIncluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        request.setAttribute("ingressos", Ingresso.obterIngressos());
        request.setAttribute("operacao", "Incluir");
        RequestDispatcher view
                = request.getRequestDispatcher("/manterPagamento.jsp");
        view.forward(request, response);
    }

    private void confirmarIncluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        int ingresso_id = Integer.parseInt(request.getParameter("optIngresso"));
        String metodo = request.getParameter("optMetodo");
        double preco = 0;
        Ingresso ingresso = null;
        if (ingresso_id != 0) {
            ingresso = Ingresso.obterIngresso(ingresso_id);
            preco = ingresso.getLote().getPreco();
        }
        Pagamento pagamento = new Pagamento(0, metodo, 0, ingresso);
        pagamento.gravar();
        RequestDispatcher view = request.getRequestDispatcher("PesquisaPagamentoController");
        view.forward(request, response);
    }

    private void prepararExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        try {
            request.setAttribute("operacao", "Excluir");
            request.setAttribute("ingressos", Ingresso.obterIngressos());
            int id = Integer.parseInt(request.getParameter("codPagamento"));
            Pagamento pagamento = Pagamento.obterPagamento(id);
            request.setAttribute("pagamento", pagamento);
            RequestDispatcher view = request.getRequestDispatcher("/manterPagamento.jsp");
            view.forward(request, response);
        } catch (ServletException e) {
        }

    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("txtID"));
            Pagamento pagamento = new Pagamento(id,null, 0, null);
            pagamento.excluir();
            RequestDispatcher view = request.getRequestDispatcher("PesquisaPagamentoController");
            view.forward(request, response);
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
        }
    }

    private void prepararEditar(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
        try {
            request.setAttribute("operacao", "Editar");
            request.setAttribute("ingressos", Ingresso.obterIngressos());
            int id = Integer.parseInt(request.getParameter("codPagamento"));
            Pagamento pagamento = Pagamento.obterPagamento(id);
            request.setAttribute("pagamento", pagamento);
            RequestDispatcher view = request.getRequestDispatcher("/manterPagamento.jsp");
            view.forward(request, response);
        } catch (ServletException e) {
        }
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("txtID"));
        int ingresso_id = Integer.parseInt(request.getParameter("optIngresso"));
        String metodo = request.getParameter("optMetodo");
        double preco = 0;
        Ingresso ingresso = null;
        if (ingresso_id != 0) {
            ingresso = Ingresso.obterIngresso(ingresso_id);
            preco = ingresso.getLote().getPreco();
        }
        Pagamento pagamento = new Pagamento(id, metodo, 0, ingresso);
        pagamento.alterar();
        RequestDispatcher view = request.getRequestDispatcher("PesquisaPagamentoController");
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
            Logger.getLogger(ManterPagamentoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterPagamentoController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ManterPagamentoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterPagamentoController.class.getName()).log(Level.SEVERE, null, ex);
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
