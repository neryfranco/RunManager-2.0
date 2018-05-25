/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Categoria;

/**
 *
 * @author Nery
 */
public class ManterCategoriaController extends HttpServlet {

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
    
    public void prepararIncluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("operacao", "Incluir");
        RequestDispatcher view=
                request.getRequestDispatcher("/manterCategoria.jsp");
        view.forward(request, response);
    }
    
    private void confirmarIncluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        String Sexo = request.getParameter("txtSexo");
        String idadeIni = request.getParameter("txtIdadeInicial");
        String idadeFinal = request.getParameter("txtIdadeFinal");
        String descricao = request.getParameter("txtDescricao");
        Categoria categoria = new Categoria(0, Sexo, idadeIni, idadeFinal, descricao);
        categoria.gravar();
        RequestDispatcher view = request.getRequestDispatcher("PesquisaCategoriaController");
        view.forward(request, response);
    }
    
    private void prepararExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        try {
            request.setAttribute("operacao", "Excluir");
            int idCategoria = Integer.parseInt(request.getParameter("codCategoria"));
            Categoria categoria = Categoria.obterCategoria(idCategoria);
            request.setAttribute("categoria", categoria);
            RequestDispatcher view = request.getRequestDispatcher("/manterCategoria.jsp");
            view.forward(request, response);
        } catch (ServletException e) {
        }

    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("txtID"));
        Categoria categoria = new Categoria(id,null , null, null, null);
        categoria.excluir();
        RequestDispatcher view = request.getRequestDispatcher("PesquisaCategoriaController");
        view.forward(request, response);
    }

    private void prepararEditar(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
        try {
            request.setAttribute("operacao", "Editar");
            int idCategoria = Integer.parseInt(request.getParameter("codCategoria"));
            Categoria categoria = Categoria.obterCategoria(idCategoria);
            request.setAttribute("categoria", categoria);
            RequestDispatcher view = request.getRequestDispatcher("/manterCategoria.jsp");
            view.forward(request, response);
        } catch (ServletException e) {
        }
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("txtID"));
        String sexo = request.getParameter("txtSexo");
        String idadeIni = request.getParameter("txtIdadeInicial");
        String idadeFim = request.getParameter("txtIdadeFinal");
        String descricao = request.getParameter("txtDescricao");
        Categoria categoria = new Categoria(id, sexo, idadeIni, idadeFim, descricao);
        categoria.alterar();
        RequestDispatcher view = request.getRequestDispatcher("PesquisaCategoriaController");
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
            Logger.getLogger(ManterCategoriaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterCategoriaController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ManterCategoriaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterCategoriaController.class.getName()).log(Level.SEVERE, null, ex);
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
