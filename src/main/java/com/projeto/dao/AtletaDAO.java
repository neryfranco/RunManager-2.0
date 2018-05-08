/*
 * To change this license header, choose License HeadeselectAt in Project Properties.
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
import modelo.Atleta;

/**
 *
 * @author Nery
 */
public class AtletaDAO {

    public static List<Atleta> obterAtletas() throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        List<Atleta> atletas = new ArrayList<Atleta>();
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Atleta, Usuario where Atleta.Usuario_cpf = Usuario.cpf");
            while (rs.next()) {
                Atleta atleta = new Atleta(rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getString("dataNasc"),
                        rs.getString("sexo"),
                        rs.getString("tel_cel"),
                        rs.getString("tel_res"),
                        rs.getString("cep"),
                        rs.getString("rua"),
                        rs.getString("uf"),
                        rs.getString("cidade"),
                        rs.getString("apelido"));
                atletas.add(atleta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return atletas;
    }

    public static void gravar(Atleta atleta) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "insert into usuario (cpf, nome, dataNasc, sexo, tel_cel, tel_res, cep, rua, uf, cidade) values ('"
                    + atleta.getCpf()+ "', '"
                    + atleta.getNome() + "', '"
                    + atleta.getDataNascimento() + "', '"
                    + atleta.getSexo() + "', '"
                    + atleta.getTelCel() + "', '"
                    + atleta.getTelRes() + "', '"
                    + atleta.getCep() + "', '"
                    + atleta.getRua() + "', '"
                    + atleta.getUf()+ "', '"
                    + atleta.getCidade() + "')";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.execute(sql);
            
            sql = "insert into atleta (email, senha, Usuario_cpf, apelido) values('"
                    + atleta.getEmail() + "', '"
                    + atleta.getSenha() + "', '"
                    + atleta.getCpf() + "', '"
                    + atleta.getApelido() + "') ";
            comando = conexao.prepareStatement(sql);
            comando.execute(sql);
            comando.close();
            comando.close();
        }
        catch (SQLException e) {
        }

    }

    public static void alterar(Atleta atleta) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "update Atleta set"
                    + " email = '" + atleta.getEmail() + "'"
                    + ", senha = '" + atleta.getSenha() + "'"
                    + ", apelido = '" + atleta.getApelido() + "'"
                    + " where Usuario_cpf = '" + atleta.getCpf() + "'";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.execute(sql);
            
            sql = "update Usuario set "
                    + " nome = '" + atleta.getNome() + "'"
                    + ", dataNasc = '" + atleta.getDataNascimento()+ "'"
                    + ", sexo = '" + atleta.getSexo() + "'"
                    + ", tel_cel = '" + atleta.getTelCel() + "'"
                    + ", tel_res = '" + atleta.getTelRes() + "'"
                    + ", cep = '" + atleta.getCep() + "'"
                    + ", rua = '" + atleta.getRua() + "'"
                    + ", cidade = '" + atleta.getCidade() + "'";
            sql = sql + " where cpf = '" + atleta.getCpf() + "'";
            comando = conexao.prepareStatement(sql);
            comando.execute(sql);
            comando.close();
            conexao.close();
        }
        catch (SQLException e) {
        }
    }

    public static void excluir(Atleta atleta) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            stringSQL = "delete from Atleta where Usuario_cpf = '" + atleta.getCpf() + "'";
            comando.execute(stringSQL);
        } catch (SQLException e) {
            throw e;

        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static Atleta obterAtleta(String cpf) throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        Atleta a = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            
            ResultSet rs = comando.executeQuery("select * from Atleta where Usuario_cpf = '" + cpf + "'");
            rs.first();
            a = new Atleta(rs.getString("email"), 
                    rs.getString("senha"), 
                    rs.getString("Usuario_cpf"), 
                    rs.getString("apelido"));

            rs = comando.executeQuery("select * from Usuario where cpf = '" + a.getCpf() + "'");
            rs.first();
            a.setNome(rs.getString("nome"));
            a.setDataNascimento(rs.getString("dataNasc"));
            a.setSexo(rs.getString("sexo"));
            a.setTelCel(rs.getString("tel_cel"));
            a.setTelRes(rs.getString("tel_res"));
            a.setCep(rs.getString("cep"));
            a.setRua(rs.getString("rua"));
            a.setUf(rs.getString("uf"));
            a.setCidade(rs.getString("cidade"));
                    
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
       return a;  
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
