/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import Modelo.Autor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Artur
 */
public class Consulta extends Conexion {

    public boolean registrar(Autor autor) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO author (name, affiliations, email) VALUES (?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, autor.getName());
            ps.setString(2, autor.getEmail());
            ps.setString(3, autor.getAffiliations());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.err.print(e);
            return false;

        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.print(e);
            }

        }

    }

    public ArrayList<Autor> listar() {
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;
        String sql = "SELECT * FROM author";
        ArrayList<Autor> autores = new ArrayList<>();

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Autor autor = new Autor(rs.getString("name"),rs.getString("email"),rs.getString("affiliations"));
                autores.add(autor);
            }
        } catch (Exception e) {
            System.err.print(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.print(e);
            }
        }
        return autores;
    }

}
