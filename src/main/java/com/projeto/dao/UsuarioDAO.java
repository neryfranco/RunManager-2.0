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
import modelo.Usuario;

/**
 *
 * @author Nery
 */
public class UsuarioDAO {

    public static List<Usuario> obterUsuarios() throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        List<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Usuario");
            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getString("dataNasc"),
                        rs.getString("sexo"),
                        rs.getString("tel_cel"),
                        rs.getString("tel_res"),
                        rs.getString("cep"),
                        rs.getString("rua"),
                        rs.getString("uf"),
                        rs.getString("cidade"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return usuarios;
    }

    public static void gravar(Usuario usuario) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "insert into usuario (cpf, nome, dataNascimento, sexo, telCel, telRes, cep, rua, uf, cidade) "
                    + "values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, usuario.getCpf());
            comando.setString(2, usuario.getNome());
            comando.setString(3, usuario.getDataNascimento());
            comando.setString(4, usuario.getSexo());
            comando.setString(5, usuario.getTelCel());
            comando.setString(6, usuario.getTelRes());
            comando.setString(7, usuario.getCep());
            comando.setString(8, usuario.getRua());
            comando.setString(9, usuario.getRua());
            comando.setString(10, usuario.getCidade());
            comando.execute(sql);
            comando.close();
            conexao.close();
        } catch (SQLException e) {
        }
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
