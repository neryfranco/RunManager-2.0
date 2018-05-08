/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projeto.modelo;

import dao.ChipDAO;
import java.util.List;

/**
 *
 * @author Nery
 */
public class Chip {
    
    private int numero;
    private int tempoCorrida;
    private Percurso percurso;
    
    private int percurso_id;
    private int kit_id;

    public Chip(int numero, int tempoCorrida, Percurso percurso) {
        this.numero = numero;
        this.tempoCorrida = tempoCorrida;
        this.percurso = percurso;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }


    public int getTempoCorrida() {
        return tempoCorrida;
    }

    public void setTempoCorrida(int tempoCorrida) {
        this.tempoCorrida = tempoCorrida;
    }

    public Percurso getPercurso() {
        return percurso;
    }

    public void setPercurso(Percurso percurso) {
        this.percurso = percurso;
    }

    public int getPercurso_id() {
        return percurso_id;
    }

    public void setPercurso_id(int percurso_id) {
        this.percurso_id = percurso_id;
    }

    public int getKit_id() {
        return kit_id;
    }

    public void setKit_id(int kit_id) {
        this.kit_id = kit_id;
    }
    
    public static List<Chip> obterChips() throws ClassNotFoundException{
        return ChipDAO.obterChips();
    }
    
    public static Chip obterChip(int chip_num) throws ClassNotFoundException{
        return ChipDAO.obterChip(chip_num);
    }
}
