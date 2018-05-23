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
import modelo.Corrida;
import modelo.Lote;

/**
 *
 * @author Nery
 */
public class ManterLoteController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        String acao = request.getParameter("acao");
        if (acao.equals("prepararIncluir")) {
            prepararIncluir(request, response);
        } else if (acao.equals("confirmarIncluir")) {
            confirmarIncluir(request, response);
        } else if (acao.equals("prepararExcluir")) {
            prepararExcluir(request, response);
        } else if (acao.equals("confirmarExcluir")) {
            confirmarExcluir(request, response);
        }
        else if (acao.equals("prepararEditar")){
            prepararEditar(request,response);
        }
        else if (acao.equals("confirmarEditar")){
            confirmarEditar(request, response);
        }
    }

    public void prepararIncluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        request.setAttribute("operacao", "Incluir");
        request.setAttribute("corridas", Corrida.obterCorridas());
        RequestDispatcher view
                = request.getRequestDispatcher("/manterLote.jsp");
        view.forward(request, response);
    }

    public void confirmarIncluir(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int corrida_id = Integer.parseInt(request.getParameter("optCorrida"));
        double preco = Double.parseDouble(request.getParameter("txtPreco"));
        String dataLim = request.getParameter("txtDataLimite");
        try {
            Corrida corrida = null;
            if (corrida_id != 0) {
                corrida = Corrida.obterCorrida(corrida_id);
            }

            Lote lote = new Lote(0, preco, corrida, dataLim);
            lote.gravar();
            RequestDispatcher view = request.getRequestDispatcher("PesquisaLoteController");
            view.forward(request, response);

        } catch (ClassNotFoundException ex) {
        }
    }

    public void prepararExcluir(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("operacao", "Excluir");
            request.setAttribute("corridas", Corrida.obterCorridas());
            int id = Integer.parseInt(request.getParameter("idLote"));
            request.setAttribute("idLote", id);
            request.setAttribute("lote", Lote.obterLote(id));
            RequestDispatcher view = request.getRequestDispatcher("/manterLote.jsp");
            view.forward(request, response);
        } catch (ServletException ex) {

        } catch (IOException ex) {

        } catch (ClassNotFoundException ex) {

        }
    }

    public void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {       
        try {
            int corrida = Integer.parseInt(request.getParameter("optCorrida"));
            int id = Integer.parseInt(request.getParameter("txtID"));
            Lote lote = new Lote(id, 0, null, null);
            lote.setCorrida_id(corrida);
            lote.excluir();
            RequestDispatcher view = request.getRequestDispatcher("PesquisaLoteController");
            view.forward(request, response);
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
        }
    
    }
    
    public void prepararEditar(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setAttribute("operacao", "Editar");
            request.setAttribute("corridas", Corrida.obterCorridas());
            int id = Integer.parseInt(request.getParameter("idLote"));
            request.setAttribute("idLote", id);
            request.setAttribute("lote", Lote.obterLote(id));
            RequestDispatcher view = request.getRequestDispatcher("/manterLote.jsp");
            view.forward(request, response);
        } catch (ServletException ex) {

        } catch (IOException ex) {

        } catch (ClassNotFoundException ex) {

        }
        
    }
    
    public void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
        int corrida_id = Integer.parseInt(request.getParameter("optCorrida"));
        int id = Integer.parseInt(request.getParameter("txtID"));
        double preco = Double.parseDouble(request.getParameter("txtPreco"));
        String dataLim = request.getParameter("txtDataLimite");
        try {
            Corrida corrida = null;
            if (corrida_id != 0) {
                corrida = Corrida.obterCorrida(corrida_id);
            }

            Lote lote = new Lote(id, preco, corrida, dataLim);
            lote.alterar();
            RequestDispatcher view = request.getRequestDispatcher("PesquisaLoteController");
            view.forward(request, response);

        } catch (ClassNotFoundException ex) {
        }
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterLoteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManterLoteController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterLoteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManterLoteController.class.getName()).log(Level.SEVERE, null, ex);
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
