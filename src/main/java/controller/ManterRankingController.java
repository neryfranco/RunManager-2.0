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
import modelo.Categoria;
import modelo.Corrida;
import modelo.Ranking;

/**
 *
 * @author Nery
 */
public class ManterRankingController extends HttpServlet {

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
        request.setAttribute("operacao", "Incluir");
        request.setAttribute("corridas", Corrida.obterCorridas());
        request.setAttribute("categorias", Categoria.obterCategorias());
        RequestDispatcher view=
                request.getRequestDispatcher("/manterRanking.jsp");
        view.forward(request, response);
    }
    
    private void confirmarIncluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        int corrida_id = Integer.parseInt(request.getParameter("optCorrida"));
        int categoria_id = Integer.parseInt(request.getParameter("optCategoria"));
        Categoria categoria = null;
        if (categoria_id != 0) {
            categoria = Categoria.obterCategoria(categoria_id);
        }
        Corrida corrida = null;
        if (corrida_id != 0) {
            corrida = Corrida.obterCorrida(corrida_id);
        }
        
        Ranking ranking = new Ranking(0, corrida, categoria);
        ranking.gravar();
        RequestDispatcher view = request.getRequestDispatcher("PesquisaRankingController");
        view.forward(request, response);
    }

    private void prepararExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        try {
            request.setAttribute("operacao", "Excluir");
            request.setAttribute("corridas", Corrida.obterCorridas());
            request.setAttribute("categorias", Categoria.obterCategorias());
            int idRanking = Integer.parseInt(request.getParameter("idRanking"));
            Ranking ranking = Ranking.obterRanking(idRanking);
            request.setAttribute("ranking", ranking);
            RequestDispatcher view = request.getRequestDispatcher("/manterRanking.jsp");
            view.forward(request, response);
        } catch (ServletException e) {
        }
    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("txtID"));
        Ranking ranking = new Ranking(id,null,null);
        ranking.excluir();
        RequestDispatcher view = request.getRequestDispatcher("PesquisaRankingController");
        view.forward(request, response); 
    }

    private void prepararEditar(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException, SQLException {
        try {
            request.setAttribute("operacao", "Editar");
            request.setAttribute("corridas", Corrida.obterCorridas());
            request.setAttribute("categorias", Categoria.obterCategorias());
            int idRanking = Integer.parseInt(request.getParameter("idRanking"));
            Ranking ranking = Ranking.obterRanking(idRanking);
            request.setAttribute("ranking", ranking);
            RequestDispatcher view = request.getRequestDispatcher("/manterRanking.jsp");
            view.forward(request, response);
        } catch (ServletException e) {
        }
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        int id = Integer.parseInt(request.getParameter("txtID"));
        int corrida_id = Integer.parseInt(request.getParameter("optCorrida"));
        int categoria_id = Integer.parseInt(request.getParameter("optCategoria"));
        Categoria categoria = null;
        if (categoria_id != 0) {
            categoria = Categoria.obterCategoria(categoria_id);
        }
        Corrida corrida = null;
        if (corrida_id != 0) {
            corrida = Corrida.obterCorrida(corrida_id);
        }
        
        Ranking ranking = new Ranking(id, corrida, categoria);
        ranking.alterar();
        RequestDispatcher view = request.getRequestDispatcher("PesquisaRankingController");
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterRankingController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
             Logger.getLogger(ManterRankingController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ManterRankingController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
             Logger.getLogger(ManterRankingController.class.getName()).log(Level.SEVERE, null, ex);
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
