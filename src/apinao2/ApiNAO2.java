/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apinao2;

import Controlador.AutorControlador;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;
import org.json.JSONException;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Artur
 */
public class ApiNAO2 {

    /**
     * @param args the command line arguments
     */
        

    public static void main(String[] args) throws MalformedURLException, IOException, ParseException, JSONException {
        AutorControlador autor = new AutorControlador();
        
        Scanner WF = new Scanner(System.in);
        System.out.println("------------------------------------------------");
        System.out.println("Dame el ID del autor que deseas consultar: ");
        String query = WF.next();
        System.out.println("Numero de referencia de inicio: ");
        int start = WF.nextInt();
        System.out.println("Cantidad de articulos: ");
        int num = WF.nextInt();
        System.out.println("------------------------------------------------");
        autor.crearAutor(query, start, num);
        }
    }
