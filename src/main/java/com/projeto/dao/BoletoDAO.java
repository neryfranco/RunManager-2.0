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
import modelo.Boleto;
import modelo.Pagamento;

/**
 *
 * @author Nery
 */
public class BoletoDAO {

    public static List<Boleto> obterBoletos() throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        List<Boleto> boletos = new ArrayList<Boleto>();
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Boleto");
            while (rs.next()) {
                Boleto boleto = new Boleto(rs.getString("nomeCliente"),
                        rs.getString("cpf"),
                        null);
                boleto.setPagamento_id(rs.getInt("Pagamento_id"));
                boletos.add(boleto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return boletos;
    }

    public static void gravar(Boleto boleto) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "insert into boleto (nome, cpf, pagamento, pagamento_id)"
                    + "values(?,?,?)";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, boleto.getNome());
            comando.setString(2, boleto.getCpf());
            comando.setObject(3, boleto.getPagamento());
            comando.setInt(4, boleto.getPagamento_id());
            comando.execute(sql);
            comando.close();
            conexao.close();
        } catch (SQLException e) {
        }
    }
public static void alterar(Boleto boleto) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "update curso set cpf = ?, nome = ?, pagamento = ?, pagamento_id = ?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, boleto.getNome());
            comando.setString(2, boleto.getCpf());
            comando.setObject(3, boleto.getPagamento()); 
            comando.setInt(4, boleto.getPagamento_id());
            comando.execute(sql);
            comando.close();
            conexao.close();
        }
        catch (SQLException e) {
        }
    }

    public static void excluir(Boleto boleto) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            stringSQL = "delete from curso where email = " + boleto.getCpf();
            comando.execute(stringSQL);
        } catch (SQLException e) {
            throw e;

        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static Boleto obterBoleto(String cpf) throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        Boleto boleto = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Boleto where cpf = " + cpf);
            rs.first();
            boleto = new Boleto(rs.getString("cpf"),
                    rs.getString("nome"), (Pagamento) rs.getObject("pagamento"));
                   
                    
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
       return boleto;  
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
