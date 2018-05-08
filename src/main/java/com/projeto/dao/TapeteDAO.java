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
import modelo.Tapete;

/**
 *
 * @author Nery
 */
public class TapeteDAO {
    
    public static List<Tapete> obterTapetes() throws ClassNotFoundException{
        Connection conexao = null;
        Statement comando = null;
        List<Tapete> tapetes = new ArrayList<Tapete>();
        try{
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Tapete");
            while(rs.next()){
                Tapete tapete = new Tapete 
               (rs.getInt("id"),
                rs.getString("cep"),
                rs.getString("rua"),
                rs.getString("cidade"),
                rs.getString("uf"),
                rs.getString("referencia"));
                            
                tapetes.add(tapete);
            }
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            fecharConexao(conexao,comando);
        }
        return tapetes;
    }
    
    public static Tapete obterTapete(int id) throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        Tapete tapete = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Tapete where id = " + id);
            rs.first();
            tapete = new Tapete(rs.getInt("id"),
                    rs.getString("cep"),
                    rs.getString("rua"),
                    rs.getString("cidade"),
                    rs.getString("uf"),
                    rs.getString("referencia"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return tapete;
    }
    
    public static void gravar(Tapete tapete) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "insert into tapete (cep, rua, cidade, uf, referencia) values ('"
                    + tapete.getCep()
                    + "', '" + tapete.getRua()
                    + "', '" + tapete.getCidade()
                    + "', '" + tapete.getUf()
                    + "', '" + tapete.getReferencia()
                    + "') ";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.execute(sql);
            comando.close();
            conexao.close();
        } catch (SQLException e) {
        }
    }
    
    public static void alterar(Tapete tapete) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "update Tapete set "
                    + "cep = '" + tapete.getCep() + "'"
                    + ", rua = '" + tapete.getRua() + "'"
                    + ", cidade = '" + tapete.getCidade() + "'"
                    + ", uf = '" + tapete.getUf()+ "'"
                    + ", referencia = '" + tapete.getReferencia() + "'";
            sql = sql + " where id = " + tapete.getId();
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.execute(sql);
            comando.close();
            conexao.close();
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public static void excluir(Tapete tapete) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            stringSQL = "delete from Tapete where id = " + tapete.getId();
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
