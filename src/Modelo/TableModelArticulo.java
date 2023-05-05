/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Artur
 */
public class TableModelArticulo extends AbstractTableModel {

    private ArrayList<Articulo> articulos;

    public TableModelArticulo(ArrayList<Articulo> articulos) {
        this.articulos = articulos;
    }

    @Override
    public int getRowCount() {
        return articulos.size();
    }

    @Override
    public int getColumnCount() {
        return 5; 
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Articulo articulo = articulos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return articulo.getTitulo();
            case 1:
                return articulo.getLink();
            case 2:
                return articulo.getAutores();
            case 3:
                return articulo.getPublicacion();
            case 4:
                return articulo.getAño();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Titulo";
            case 1:
                return "Link";
            case 2:
                return "Autores";
            case 3:
                return "Publicacion";
            case 4:
                return "Año";
            default:
                return null;
        }
    }
}
