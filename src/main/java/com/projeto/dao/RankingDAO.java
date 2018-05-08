/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.RankingDAO.fecharConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import modelo.Ranking;
import modelo.Ranking;

/**
 *
 * @author Nery
 */
public class RankingDAO {
    
    public static List<Ranking> obterRankings() throws ClassNotFoundException{
        Connection conexao = null;
        Statement comando = null;
        List<Ranking> rankings = new ArrayList<Ranking>();
        try{
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Ranking");
            while(rs.next()){
                Ranking ranking = new Ranking (rs.getInt("id"),null,null);
                
                ranking.setCorrida_id(rs.getInt("Corrida_id"));
                ranking.setCategoria_id(rs.getInt("Categoria_id"));
                rankings.add(ranking);
            }
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            fecharConexao(conexao,comando);
        }
        return rankings;
    }
    
    public static Ranking obterRanking(int idRanking) throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        Ranking ranking = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Ranking where id = " + idRanking);
            rs.first();
            ranking = new Ranking(rs.getInt("id"),
                    null,
                    null);
            
            ranking.setCategoria_id(rs.getInt("Categoria_id"));
            ranking.setCorrida_id(rs.getInt("Corrida_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return ranking;
    }
    
    public static void gravar(Ranking ranking) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "insert into ranking (id, Corrida_id, Categoria_id) values ("
                    + ranking.getId() + ", ";
                    
            if (ranking.getCorrida()== null) {
                sql = sql + Types.NULL;
            } else {
                sql = sql + ranking.getCorrida().getId();
            }
            sql = sql + ", ";
            if (ranking.getCategoria() == null) {
                sql = sql + Types.NULL;
            } else {
                sql = sql + ranking.getCategoria().getId();
            }
            sql = sql + ")";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.execute(sql);
            comando.close();
            conexao.close();
        } catch (SQLException e) {
        }
    }
    
    public static void alterar(Ranking ranking) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "update Ranking set Corrida_id = ";
            if (ranking.getCorrida()== null) {
                sql = sql + Types.NULL;
            } else {
                sql = sql + ranking.getCorrida().getId();
            }
            sql = sql + ", Categoria_id = ";
            if (ranking.getCategoria() == null) {
                sql = sql + Types.NULL;
            } else {
                sql = sql + ranking.getCategoria().getId();
            }
            sql = sql + " where id = " + ranking.getId();
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.execute(sql);
            comando.close();
            conexao.close();
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public static void excluir(Ranking ranking) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            stringSQL = "delete from Ranking where id = " + ranking.getId();
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
