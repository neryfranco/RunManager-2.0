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
import modelo.Lote;;

/**
 *
 * @author Nery
 */
public class LoteDAO {
    
    public static List<Lote> obterLotes() throws ClassNotFoundException{
        Connection conexao = null;
        Statement comando = null;
        List<Lote> lotes = new ArrayList<Lote>();
        try{
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Lote");
            while(rs.next()){
               Lote lote = new Lote (rs.getInt("id"), rs.getDouble("preco"),null,rs.getString("dataLimite"));
               lote.setCorrida_id(rs.getInt("Corrida_id"));
               lotes.add(lote);
            }
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            fecharConexao(conexao,comando);
        }
        return lotes;
    }
    
    public static Lote obterLote(int id) throws ClassNotFoundException{
        Connection conexao = null;
        Statement comando = null;
        Lote lote = null;
        try{
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Lote where id = " + id);
            rs.first();
            lote = new Lote (rs.getInt("id"), rs.getDouble("preco"),null,rs.getString("dataLimite"));
            lote.setCorrida_id(rs.getInt("Corrida_id"));
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            fecharConexao(conexao,comando);
        }
        return lote;
    }
    
    public static void gravar(Lote Lote) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "insert into Lote (preco, dataLimite, Corrida_id) values ("
                    + Lote.getPreco()
                    + ", '" + Lote.getDataLimite()
                    + "', ";
            if (Lote.getCorrida()== null) {
                sql = sql + Types.NULL;
            } else {
                sql = sql + Lote.getCorrida().getId();
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
    
    public static void alterar(Lote lote) throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "update Lote set "
                    + " preco = " + lote.getPreco()
                    + ", dataLimite = '" + lote.getDataLimite() + "'"
                    + " where Corrida_id = " + lote.getCorrida().getId() + " and id = " + lote.getId();
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.execute(sql);
            comando.close();
            conexao.close();
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public static void excluir(Lote lote) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            stringSQL = "delete from Lote where corrida_id = " + lote.getCorrida_id() + " and id = " + lote.getId();
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