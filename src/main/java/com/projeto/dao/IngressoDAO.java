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
import modelo.Ingresso;

/**
 *
 * @author Nery
 */
public class IngressoDAO {

    public static List<Ingresso> obterIngressos() throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        List<Ingresso> ingressos = new ArrayList<Ingresso>();
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Ingresso");
            while (rs.next()) {
                Ingresso ingresso = new Ingresso(null, rs.getInt("num_inscricao"), null, null);
                ingresso.setAtleta_cpf(rs.getString("Ingresso_Usuario_cpf"));
                ingresso.setKit_numPeito(rs.getInt("Kit_numPeito"));
                ingresso.setLote_id(rs.getInt("Lote_id"));
                ingressos.add(ingresso);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return ingressos;
    }

    public static void gravar(Ingresso ingresso) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "insert into ingresso (Lote_id, Atleta_Usuario_cpf, Kit_id) values(";
            
            if (ingresso.getLote() == null) {
                sql = sql + Types.NULL;
            } else {
                sql = sql + ", " + ingresso.getLote().getId();
            }
            
            if (ingresso.getAtleta() == null) {
                sql = sql + Types.NULL;
            } else {
                sql = sql + ", " + ingresso.getAtleta().getCpf();
            }
            
            if (ingresso.getKit() == null) {
                sql = sql + Types.NULL;
            } else {
                sql = sql + ", " + ingresso.getKit().getNumPeito();
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
public static void alterar(Ingresso ingresso) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "update curso set lote = ?, kit = ?, atleta = ?, pagamento = ?, telCel = ?, telRes = ?, cep = ?, rua = ?, uf = ?, cidade = ?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setObject(1, ingresso.getLote());
            comando.setObject(2, ingresso.getKit());
            comando.setObject(3, ingresso.getAtleta());            
            comando.execute(sql);
            comando.close();
            conexao.close();
        }
        catch (SQLException e) {
        }
    }

    public static void excluir(Ingresso ingresso) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            stringSQL = "delete from curso where numInscricao = " + ingresso.getNumInscricao();
            comando.execute(stringSQL);
        } catch (SQLException e) {
            throw e;

        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static Ingresso obterIngresso(String email) throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        Ingresso ingresso = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Ingresso where email = " + email);
            rs.first();
            ingresso = new Ingresso(null,
                    rs.getLong("numInscricao"),
                    null,
                    null);
            ingresso.setLote_id(rs.getInt("lote"));
            ingresso.setKit_numPeito(rs.getInt("kit"));
            ingresso.setAtleta_cpf(rs.getString("atleta"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
       return ingresso;  
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
