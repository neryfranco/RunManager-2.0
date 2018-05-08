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
import modelo.Administrador;
import modelo.Organizador;

/**
 *
 * @author Nery
 */
public class AdministradorDAO {

    public static List<Administrador> obterAdministradores() throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        List<Administrador> administradores = new ArrayList<Administrador>();
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Administrador, Usuario where Administrador.Usuario_cpf = Usuario.cpf");
            while (rs.next()) {
                Administrador administrador = new Administrador(rs.getString("email"),
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
                administradores.add(administrador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return administradores;
    }

    public static void gravar(Administrador administrador) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();

            String sql = "insert into usuario (cpf, nome, dataNasc, sexo, tel_cel, tel_res, cep, rua, uf, cidade) values('"
                    + administrador.getCpf() + "', '"
                    + administrador.getNome() + "', '"
                    + administrador.getDataNascimento() + "', '"
                    + administrador.getSexo() + "', '"
                    + administrador.getTelCel() + "', '"
                    + administrador.getTelRes() + "', '"
                    + administrador.getCep() + "', '"
                    + administrador.getRua() + "', '"
                    + administrador.getUf() + "', '"
                    + administrador.getCidade() + "') ";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.execute(sql);

            sql = "insert into administrador (Usuario_cpf, email, senha) values('"
                    + administrador.getCpf() + "', '"
                    + administrador.getEmail() + "', '"
                    + administrador.getSenha() + "') ";
            comando = conexao.prepareStatement(sql);
            comando.execute(sql);
            comando.close();
            comando.close();
        } catch (SQLException e) {
        }
    }

    public static void alterar(Administrador administrador) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "update Administrador set "
                    + " email = '" + administrador.getEmail() + "'"
                    + ", senha = '" + administrador.getSenha() + "'"
                    + " where Usuario_cpf = '" + administrador.getCpf() + "'";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.execute(sql);
            
            sql = "update Usuario set "
                    + " nome = '" + administrador.getNome() + "'"
                    + ", dataNasc = '" + administrador.getDataNascimento()+ "'"
                    + ", sexo = '" + administrador.getSexo() + "'"
                    + ", tel_cel = '" + administrador.getTelCel() + "'"
                    + ", tel_res = '" + administrador.getTelRes() + "'"
                    + ", cep = '" + administrador.getCep() + "'"
                    + ", rua = '" + administrador.getRua() + "'"
                    + ", cidade = '" + administrador.getCidade() + "'"
                    + " where cpf = '" + administrador.getCpf() + "'";
            comando = conexao.prepareStatement(sql);
            
            comando.execute(sql);
            comando.close();
            conexao.close();
        } catch (SQLException e) {
        }
    }

    public static void excluir(Administrador administrador) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            stringSQL = "delete from Administrador where email = '" + administrador.getCpf() + "'"; 
            comando.execute(stringSQL);
        } catch (SQLException e) {
            throw e;

        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static Administrador obterAdministrador(String cpf) throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        Administrador a = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            
            ResultSet rs = comando.executeQuery("select * from Administrador where Usuario_cpf = '" + cpf + "'");
            rs.first();
            a = new Administrador(rs.getString("email"), 
                    rs.getString("senha"), 
                    rs.getString("Usuario_cpf"));

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
