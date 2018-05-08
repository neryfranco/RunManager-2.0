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
import modelo.CartaoCredito;
import modelo.Pagamento;

/**
 *
 * @author Nery
 */
public class CartaoCreditoDAO {

    public static List<CartaoCredito> obterCartaoCreditos() throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        List<CartaoCredito> cartoes = new ArrayList<CartaoCredito>();
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from CartaoCredito");
            while (rs.next()) {
                CartaoCredito cartao = new CartaoCredito(rs.getString("numero"),
                        rs.getString("bandeira"),
                        rs.getString("nomeTitular"),
                        rs.getString("cpfTitular"),
                        rs.getString("dataValidade"),
                        rs.getString("codSeguranca"),
                        rs.getInt("numParcelas"),
                        rs.getDouble("valorParcelas"),
                        null);
                cartao.setPagamento_id(rs.getInt("Pagamento_id"));
                cartoes.add(cartao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return cartoes;
    }

    public static void gravar(CartaoCredito cartaoCredito) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "insert into cartaoCredito (numCartao, bandeira, nomeTitular, )"
                    + "(cpf, dataValidade, codSeguranca, numParcelas, valorParcelas, ) "
                    + "(pagamento, pagamento_id)"
                    + "values(?,?,?,?,?)";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, cartaoCredito.getNumCartao());
            comando.setString(2, cartaoCredito.getBandeira());
            comando.setString(3, cartaoCredito.getNomeTitular());
            comando.setString(4, cartaoCredito.getCpf());
            comando.setString(5, cartaoCredito.getDataValidade());
            comando.setString(6, cartaoCredito.getCodSeguranca());
            comando.setInt(7, cartaoCredito.getNumParcelas());
            comando.setDouble(8, cartaoCredito.getValorParcelas());
            comando.setObject(9, cartaoCredito.getPagamento());
            comando.setInt(10, cartaoCredito.getPagamento_id());
            comando.execute(sql);
            comando.close();
            conexao.close();
        } catch (SQLException e) {
        }
    }
    public static void alterar(CartaoCredito cartaoCredito) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "update curso set numCartao = ?, bandeira = ?, nomeTitular = ?, cpf = ?, dataValidade = ?, codSegurança = ?, numParcelas = ?, valorParcelas = ?, pagamento = ?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, cartaoCredito.getNumCartao());
            comando.setString(2, cartaoCredito.getBandeira());
            comando.setString(3, cartaoCredito.getNomeTitular());
            comando.setString(4, cartaoCredito.getCpf());
            comando.setString(5, cartaoCredito.getDataValidade());
            comando.setString(6, cartaoCredito.getCodSeguranca());
            comando.setInt(7, cartaoCredito.getNumParcelas());
            comando.setDouble(8, cartaoCredito.getValorParcelas());
            comando.setObject(9, cartaoCredito.getPagamento());
            comando.execute(sql);
            comando.close();
            conexao.close();
        }
        catch (SQLException e) {
        }
    }

    public static void excluir(CartaoCredito cartaoCredito) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            stringSQL = "delete from curso where codSegurança = " + cartaoCredito.getCodSeguranca();
            comando.execute(stringSQL);
        } catch (SQLException e) {
            throw e;

        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static CartaoCredito obterCartaoCredito(String numCartao) throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        CartaoCredito cartaoCredito = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from CartaoCredito where numCartao = " + numCartao);
            rs.first();
            cartaoCredito = new CartaoCredito(rs.getString("numCartao"),
                    rs.getString("bandeira"),
                    rs.getString("nomeTitular"),
                    rs.getString("cpf"),
                    rs.getString("dataValidade"),
                    rs.getString("codSegurança"),
                    rs.getInt("numParcelas"),
                    rs.getDouble("valorParcelas"), (Pagamento) rs.getObject("pagamento"));
                    
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
       return cartaoCredito;  
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
