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
public class TableModel extends AbstractTableModel{
    private ArrayList<Autor> autores;

    public TableModel(ArrayList<Autor> autores) {
        this.autores = autores;
    }

    @Override
    public int getRowCount() {
        return autores.size();
    }

    @Override
    public int getColumnCount() {
        return 3; // Nombre, Email y Afiliaciones
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Autor autor = autores.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return autor.getName();
            case 1:
                return autor.getEmail();
            case 2:
                return autor.getAffiliations();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Nombre";
            case 1:
                return "Email";
            case 2:
                return "Afiliaciones";
            default:
                return null;
        }
    }

}
