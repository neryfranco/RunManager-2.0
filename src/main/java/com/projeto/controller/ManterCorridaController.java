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
import modelo.Percurso;

/**
 *
 * @author Nery
 */
public class ManterCorridaController extends HttpServlet {

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
        request.setAttribute("percursos", Percurso.obterPercursos());
        RequestDispatcher view
                = request.getRequestDispatcher("/manterCorrida.jsp");
        view.forward(request, response);
    }

    private void confirmarIncluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        String nome = request.getParameter("txtNome");
        String localLargada = request.getParameter("txtLocalLargada");
        String localChegada = request.getParameter("txtLocalChegada");
        String horarioLargada = request.getParameter("txtHorarioLargada");
        String dataCorrida = request.getParameter("txtDataCorrida");
        String dataRetiradaKit = request.getParameter("txtDataRetiradaKit");
        String localRetiradaKit = request.getParameter("txtLocalRetiradaKit");
        int duracaoLimite = Integer.parseInt(request.getParameter("txtDuracaoLimite"));
        int numMaxParticipantes = Integer.parseInt(request.getParameter("txtNumMaxParticipantes"));
        int percurso_id = Integer.parseInt(request.getParameter("optPercurso"));
        Percurso percurso = null;
        if (percurso_id != 0) {
            percurso = Percurso.obterPercurso(percurso_id);
        }
        Corrida corrida = new Corrida(0, nome, percurso, localLargada, localChegada, horarioLargada,
                dataCorrida, dataRetiradaKit, localRetiradaKit, duracaoLimite, numMaxParticipantes);
        corrida.gravar();
        RequestDispatcher view = request.getRequestDispatcher("PesquisaCorridaController");
        view.forward(request, response);
    }

    private void prepararExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        try {
            request.setAttribute("operacao", "Excluir");
            int idCorrida = Integer.parseInt(request.getParameter("codCorrida"));
            Corrida corrida = Corrida.obterCorrida(idCorrida);
            request.setAttribute("corrida", corrida);
            RequestDispatcher view = request.getRequestDispatcher("/manterCorrida.jsp");
            view.forward(request, response);
        } catch (ServletException e) {
        }
    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id1 = request.getParameter("txtID");
            int id = Integer.parseInt(id1);
            Corrida corrida = new Corrida(id, null, null, null, null, null, null, null, null, 0, 0);
            corrida.excluir();
            RequestDispatcher view = request.getRequestDispatcher("PesquisaCorridaController");
            view.forward(request, response);
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
        }
    }

    private void prepararEditar(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException, SQLException {
        try {
            request.setAttribute("operacao", "Editar");
            request.setAttribute("percursos", Percurso.obterPercursos());
            int idCorrida = Integer.parseInt(request.getParameter("codCorrida"));
            Corrida corrida = Corrida.obterCorrida(idCorrida);
            request.setAttribute("corrida", corrida);
            RequestDispatcher view = request.getRequestDispatcher("/manterCorrida.jsp");
            view.forward(request, response);
        } catch (ServletException e) {
        }
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("txtID"));
            String nome = request.getParameter("txtNome");
            String localLargada = request.getParameter("txtLocalLargada");
            String localChegada = request.getParameter("txtLocalChegada");
            String horarioLargada = request.getParameter("txtHorarioLargada");
            String dataCorrida = request.getParameter("txtDataCorrida");
            String dataRetiradaKit = request.getParameter("txtDataRetiradaKit");
            String localRetiradaKit = request.getParameter("txtLocalRetiradaKit");
            int duracaoLim = Integer.parseInt(request.getParameter("txtDuracaoLimite"));
            int numMaxParticipantes = Integer.parseInt(request.getParameter("txtNumMaxParticipantes"));
            int percurso_id = Integer.parseInt(request.getParameter("optPercurso"));
            Percurso percurso = null;
            if (percurso_id != 0) {
                percurso = Percurso.obterPercurso(percurso_id);
            }
            Corrida corrida = new Corrida(id, nome, percurso, localLargada, localChegada, horarioLargada, dataCorrida, dataRetiradaKit, localRetiradaKit, duracaoLim, numMaxParticipantes);
            corrida.alterar();
            RequestDispatcher view = request.getRequestDispatcher("PesquisaCorridaController");
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
        } catch (SQLException ex) {
            Logger.getLogger(ManterCorridaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterCorridaController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ManterCorridaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterCorridaController.class.getName()).log(Level.SEVERE, null, ex);
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
