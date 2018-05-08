/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.modelo;

import dao.KitDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Nery
 */
public class Kit {
    
    private int numPeito;
    private Chip chip;
    private String camisa;
    
    private int chip_num;
    private int camisa_id;

    public Kit(int numPeito, Chip chip, String camisa) {
        this.numPeito = numPeito;
        this.chip = chip;
        this.camisa = camisa;
    }

    public int getNumPeito() {
        return numPeito;
    }

    public void setNumPeito(int numPeito) {
        this.numPeito = numPeito;
    }

    public Chip getChip() {
        return chip;
    }

    public void setChip(Chip chip) {
        this.chip = chip;
    }

    public String getString() {
        return camisa;
    }

    public void setString(String camisa) {
        this.camisa = camisa;
    }

    public int getChip_num() {
        return chip_num;
    }

    public void setChip_num(int chip_num) {
        this.chip_num = chip_num;
    }

    public int getString_id() {
        return camisa_id;
    }

    public void setString_id(int camisa_id) {
        this.camisa_id = camisa_id;
    }
    
    public static List<Kit> obterKits() throws ClassNotFoundException{
        return KitDAO.obterKits();
    }

     public void gravar() throws SQLException,ClassNotFoundException{
        KitDAO.gravar(this);
    }
    
    /*public void alterar() throws SQLException,ClassNotFoundException{
        KitDAO.alterar(this);
    }
    
    public void excluir() throws SQLException,ClassNotFoundException{
        KitDAO.excluir(this);
    }*/
}
