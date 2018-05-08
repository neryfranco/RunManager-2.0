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
import modelo.Organizador;
import modelo.Usuario;

/**
 *
 * @author Nery
 */
public class OrganizadorDAO {

    public static List<Organizador> obterOrganizadores() throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        List<Organizador> organizadores = new ArrayList<Organizador>();
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Organizador, Usuario where Organizador.Usuario_cpf = Usuario.cpf");
            while (rs.next()) {
                Organizador organizador = new Organizador(rs.getString("email"),
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
                        rs.getString("cidade"));
                organizadores.add(organizador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally{
            fecharConexao(conexao,comando);
        }
        return organizadores;
    }

    public static void gravar(Organizador organizador) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();            
            String sql = "insert into usuario (cpf, nome, dataNasc, sexo, tel_cel, tel_res, cep, rua, uf, cidade) values('"
                    + organizador.getCpf()+ "', '"
                    + organizador.getNome() + "', '"
                    + organizador.getDataNascimento() + "', '"
                    + organizador.getSexo() + "', '"
                    + organizador.getTelCel() + "', '"
                    + organizador.getTelRes() + "', '"
                    + organizador.getCep() + "', '"
                    + organizador.getRua() + "', '"
                    + organizador.getUf()+ "', '"
                    + organizador.getCidade() + "') ";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.execute(sql);
            
            sql = "insert into organizador (Usuario_cpf, email, senha) values('"
                    + organizador.getCpf()+ "', '"
                    + organizador.getEmail() + "', '"
                    + organizador.getSenha() +  "') ";
            comando = conexao.prepareStatement(sql);
            comando.execute(sql);
            comando.close();
            comando.close();
        } catch (SQLException e) {
        }
    }
    public static void alterar(Organizador organizador) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "update Organizador set "
                    + " email = '" + organizador.getEmail() + "',"
                    + " senha = '" + organizador.getSenha() + "'"
                    + " where Usuario_cpf = '" + organizador.getCpf() + "'";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.execute(sql);
            
            sql = "update Usuario set "
                    + " nome = '" + organizador.getNome() + "'"
                    + ", dataNasc = '" + organizador.getDataNascimento()+ "'"
                    + ", sexo = '" + organizador.getSexo() + "'"
                    + ", tel_cel = '" + organizador.getTelCel() + "'"
                    + ", tel_res = '" + organizador.getTelRes() + "'"
                    + ", cep = '" + organizador.getCep() + "'"
                    + ", rua = '" + organizador.getRua() + "'"
                    + ", cidade = '" + organizador.getCidade() + "'"
                    + " where cpf = '" + organizador.getCpf() + "'";
            comando = conexao.prepareStatement(sql);
            
            comando.execute(sql);
            comando.close();
            conexao.close();
        } catch (SQLException e) {
        }
    }

    public static void excluir(Organizador organizador) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            stringSQL = "delete from Organizador where Usuario_cpf = " + organizador.getCpf();
            comando.execute(stringSQL);
        } catch (SQLException e) {
            throw e;

        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static Organizador obterOrganizador(String cpf) throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        Organizador organizador = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Organizador, Usuario where Organizador.Usuario_cpf = Usuario.cpf");
            rs.first();
            organizador = new Organizador(rs.getString("email"),
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
                    rs.getString("cidade"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return organizador;
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
