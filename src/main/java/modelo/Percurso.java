/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import dao.PercursoDAO;
import java.sql.SQLException;
import java.util.List;
import modelo.Tapete;

/**
 *
 * @author Nery
 */
public class Percurso {
    
    private int id;
    private String itinerario;
    private int distancia;
    private List<Tapete> tapetes;
    
    private int categoria_id;
    private List<Integer> tapetes_id;

    public Percurso(int id, String itinerario, int distancia, List<Tapete> tapetes) {
        this.id = id;
        this.itinerario = itinerario;
        this.distancia = distancia;
        this.tapetes = tapetes;
        this.categoria_id = 0;
    }
    
    public Percurso(int id, String itinerario, int distancia) {
        this.id = id;
        this.itinerario = itinerario;
        this.distancia = distancia;
        this.categoria_id = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItinerario() {
        return itinerario;
    }

    public void setItinerario(String itinerario) {
        this.itinerario = itinerario;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public List<Tapete> getTapetes() {
        return tapetes;
    }

    public void setTapetes(List<Tapete> tapetes) {
        this.tapetes = tapetes;
    }
    
    public void insertTapete(Tapete tapete) {
        this.tapetes.add(tapete);
    }

    public List<Integer> getTapetes_id() {
        return tapetes_id;
    }

    public void setTapetes_id(List<Integer> tapetes_id) {
        this.tapetes_id = tapetes_id;
    }
    
    public void addTapeteID(int id){
        this.tapetes_id.add(id);
    }
    
    public int getCategoria_id() {
        return categoria_id;
    }
    
    public static Percurso obterPercurso(int id) throws ClassNotFoundException, SQLException{
        return PercursoDAO.obterPercurso(id);
    }
    
    public static List<Percurso> obterPercursos() throws ClassNotFoundException{
        return PercursoDAO.obterPercursos();
    }

    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }
     
    public void gravar() throws SQLException, ClassNotFoundException{
        PercursoDAO.gravar(this);
    }
    
    public void alterar() throws SQLException, ClassNotFoundException{
        PercursoDAO.alterar(this);
    }
    
    public void excluir() throws SQLException, ClassNotFoundException{
        PercursoDAO.excluir(this);
    }
    
}
