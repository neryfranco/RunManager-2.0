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
import modelo.Administrador;

/**
 *
 * @author Nery
 */
public class ManterAdministradorController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
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
        RequestDispatcher view
                = request.getRequestDispatcher("/manterAdministrador.jsp");
        view.forward(request, response);
    }

    public void confirmarIncluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("txtEmail");
        String senha = request.getParameter("txtSenha");
        String cpf = request.getParameter("txtCpf");
        String nome = request.getParameter("txtNome");
        String dataNascimento = request.getParameter("txtDataNascimento");
        String sexo = request.getParameter("txtSexo");
        String telCel = request.getParameter("txtTelCel");
        String telRes = request.getParameter("txtTelRes");
        String cep = request.getParameter("txtCep");
        String rua = request.getParameter("txtRua");
        String uf = request.getParameter("txtUf");
        String cidade = request.getParameter("txtCidade");
        try {
            Administrador administrador = new Administrador(email, senha, cpf, nome, dataNascimento, sexo, telCel, telRes, cep, rua, uf, cidade);
            administrador.gravar();
            RequestDispatcher view = request.getRequestDispatcher("PesquisaAdministradorController");
            view.forward(request, response);
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
        }
    }

    public void prepararExcluir(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException {
        try {
            request.setAttribute("operacao", "Excluir");
            String cpf = request.getParameter("cpfAdministrador");
            Administrador admin = Administrador.obterAdministrador(cpf);
            request.setAttribute("administrador", admin);
            RequestDispatcher view = request.getRequestDispatcher("manterAdministrador.jsp");
            view.forward(request, response);
        } catch (ServletException ex) {

        }
    }

    public void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String cpf = request.getParameter("txtEmail");

            Administrador administrador = new Administrador(null, null, cpf, null, null, null, null, null, null, null, null, null);
            administrador.excluir();
            RequestDispatcher view = request.getRequestDispatcher("PesquisaAdministradorController");
            view.forward(request, response);
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
        }
    }

    public void prepararEditar(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException {
        try {
            request.setAttribute("operacao", "Editar");
            String cpf = request.getParameter("cpfAdministrador");
            Administrador admin = Administrador.obterAdministrador(cpf);
            request.setAttribute("administrador", admin);
            RequestDispatcher view = request.getRequestDispatcher("manterAdministrador.jsp");
            view.forward(request, response);
        } catch (ServletException ex) {

        }

    }

    public void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("txtEmail");
        String senha = request.getParameter("txtSenha");
        String cpf = request.getParameter("txtCpf");
        String nome = request.getParameter("txtNome");
        String dataNascimento = request.getParameter("txtDataNascimento");
        String sexo = request.getParameter("txtSexo");
        String telCel = request.getParameter("txtTelCel");
        String telRes = request.getParameter("txtTelRes");
        String cep = request.getParameter("txtCep");
        String rua = request.getParameter("txtRua");
        String uf = request.getParameter("txtUf");
        String cidade = request.getParameter("txtCidade");
        try {
            Administrador administrador = new Administrador(email, senha, cpf, nome, dataNascimento, sexo, telCel, telRes, cep, rua, uf, cidade);
            administrador.alterar();
            RequestDispatcher view = request.getRequestDispatcher("PesquisaAdministradorController");
            view.forward(request, response);
        } catch (SQLException ex) {
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
            Logger.getLogger(ManterAdministradorController.class.getName()).log(Level.SEVERE, null, ex);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterAdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
