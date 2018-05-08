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
import modelo.Kit;

/**
 *
 * @author Nery
 */
public class KitDAO {

    public static List<Kit> obterKits() throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        List<Kit> kits = new ArrayList<Kit>();
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from Kit");
            while (rs.next()) {
                Kit kit = new Kit(rs.getInt("numPeito"), null, rs.getString("Camisa"));
                kit.setChip_num(rs.getInt("Chip_numero"));
                kits.add(kit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return kits;
    }

    public static void gravar(Kit kit) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "insert into kit (numPeito, chip, camisa, chip_num, camisa_id) "
                    + "values(?,?,?,?,?)";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, kit.getNumPeito());
            comando.setObject(2, kit.getChip());
            //comando.setObject(3, kit.getCamisa());
            comando.setInt(4, kit.getChip_num());
            //comando.setInt(5, kit.getCamisa_id());
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
