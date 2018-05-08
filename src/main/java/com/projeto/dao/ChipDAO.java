/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Categoria;
import modelo.Chip;

/**
 *
 * @author Nery
 */
public class ChipDAO {

    public static List<Chip> obterChips() throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        List<Chip> chips = new ArrayList<Chip>();
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Chip");
            while (rs.next()) {
                Chip chip = new Chip(rs.getInt("numero"),
                        rs.getInt("tempoCorrida"),
                        null);
                chip.setPercurso_id(rs.getInt("Percurso_id"));
                chips.add(chip);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return chips;
    }

    public static void gravar(Chip chip) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "insert into chip (numero, tempoCorrida, percurso_id) "
                    + "values(?,?,?)";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, chip.getNumero());
            comando.setInt(2, chip.getTempoCorrida());
            comando.setInt(4, chip.getPercurso_id());
            comando.execute(sql);
            comando.close();
            conexao.close();
        } catch (SQLException e) {
        }
    }
    
    public static void alterar(Chip chip) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "update curso set numero = ?, tempoCorrida = ?, Percurso_id = ?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, chip.getNumero());
            comando.setInt(2, chip.getTempoCorrida());
            comando.setInt(3, chip.getPercurso_id());           
            comando.execute(sql);
            comando.close();
            conexao.close();
        }
        catch (SQLException e) {
        }
    }

    public static void excluir(Chip chip) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            stringSQL = "delete from curso where numero = " + chip.getNumero();
            comando.execute(stringSQL);
        } catch (SQLException e) {
            throw e;

        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static Chip obterChip(int numero) throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        Chip chip = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Chip where numero = " + numero);
            rs.first();
            chip = new Chip(rs.getInt("numero"),
                    rs.getInt("tempoCorrida"), null);
            chip.setPercurso_id(rs.getInt("Percurso_id"));
                    
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
       return chip;  
    }

    public static void fecharConexao(Connection conexao, Statement comando) {
        try {
            if (comando != null) {
                comando.close();
            }
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException e) {
        }
    }
}
