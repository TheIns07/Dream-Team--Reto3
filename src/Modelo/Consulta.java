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

/**
 *
 * @author Artur
 */
public class Consulta extends Conexion {

    public boolean registrar(Autor autor) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO author (name, affiliations, email) VALUES (?,?,?,?)";

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
            
        } finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.err.print(e);
            }
            
        }
        
    }
    
    public boolean listar(Autor autor) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;
        String sql = "SELECT * FROM author WHERE name =?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, autor.getName());
            rs = ps.executeQuery();
            ps.execute();
            if(rs.next()){
                autor.setAffiliations(rs.getString("name"));
                autor.setAffiliations(rs.getString("email"));
                autor.setAffiliations(rs.getString("affiliations"));
                return true;
            }
            return false;
        } catch (Exception e) {
                System.err.print(e);
                return false;
            
        } finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.err.print(e);
            }
            
        }
        
    }

}
