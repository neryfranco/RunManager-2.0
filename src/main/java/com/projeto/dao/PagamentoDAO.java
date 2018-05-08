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
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import modelo.Pagamento;

/**
 *
 * @author Nery
 */
public class PagamentoDAO {
    
    public static List<Pagamento> obterPagamentos() throws ClassNotFoundException{
        Connection conexao = null;
        Statement comando = null;
        List<Pagamento> pagamentos = new ArrayList<Pagamento>();
        try{
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Pagamento");
            while(rs.next()){
                Pagamento pagamento = new Pagamento (rs.getInt("id"), rs.getString("metodoPag"),rs.getDouble("preco"), null);
                pagamentos.add(pagamento);
            }
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            fecharConexao(conexao,comando);
        }
        return pagamentos;
    }
    
    
    public static Pagamento obterPagamento(int id) throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        Pagamento pagamento = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Pagamento where id = " + id);
            rs.first();
            pagamento = new Pagamento (rs.getInt("id"), rs.getString("metodoPag"),rs.getDouble("preco"), null);

            pagamento.setIngresso_id(rs.getInt("Ingresso_num_inscricao"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return pagamento;
    }
    
    public static void gravar(Pagamento pagamento) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "insert into pagamento (id, preco, metodoPag, Ingresso_num_inscricao) values ("
                    + pagamento.getId()
                    + ", " + pagamento.getValor()
                    + ", '" + pagamento.getMetodoPagamento()
                    + "', ";
            if (pagamento.getIngresso() == null) {
                sql = sql + Types.NULL;
            } else {
                sql = sql + pagamento.getIngresso().getNumInscricao();
            }
            sql = sql + ")";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.execute(sql);
            comando.close();
            conexao.close();
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public static void alterar(Pagamento pagamento) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "update Pagamento set "
                    + pagamento.getId()
                    + ", " + pagamento.getValor()
                    + ", '" + pagamento.getMetodoPagamento()
                    + "', ";
            if (pagamento.getIngresso() == null) {
                sql = sql + Types.NULL;
            } else {
                sql = sql + pagamento.getIngresso().getNumInscricao();
            }
            sql = sql + " where id = " + pagamento.getId();
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.execute(sql);
            comando.close();
            conexao.close();
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public static void excluir(Pagamento pagamento) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            stringSQL = "delete from Pagamento where id = " + pagamento.getId();
            comando.execute(stringSQL);
        } catch (SQLException e) {
            throw e;
        } finally {
            fecharConexao(conexao, comando);
        }
    }
    
    public static void fecharConexao(Connection conexao ,Statement comando){
        try{
            if (comando != null)
                comando.close();
            if (conexao != null)
                conexao.close();
        }
        catch (SQLException e){}
    }
}
