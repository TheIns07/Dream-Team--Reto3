/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Articulo;
import Modelo.Autor;
import Modelo.Consulta;
import java.beans.Statement;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Scanner;
import org.json.JSONArray;

/**
 *
 * @author Artur
 *
 * @throws java.net.MalformedURLException
 * @throws org.json.simple.parser.ParseException
 * @throws org.json.JSONException
 */
public class AutorControlador {

    public Consulta consulta = new Consulta();
    public Autor autor = null;
    public ArrayList<Articulo> articulos = new ArrayList<>();
    
    public StringBuilder APILogic(String query, int start, int num) throws MalformedURLException, ProtocolException, IOException {
        String BASE_URL = "https://serpapi.com/search.json?engine=google_scholar_author&author_id=" + query
                + "&api_key=408a30522a569d88a68d13cb09c0719a3d8b3429b625774f76fad82fa8d191af&start=" + start + "&num=" + num;

        URL url = new URL(BASE_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int responseCode = conn.getResponseCode();

        if (responseCode != 200) {
            throw new RuntimeException("HTTPResponse: " + responseCode);
        } else {
            StringBuilder informationString = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                informationString.append(scanner.nextLine());
            }
            scanner.close();
            System.out.println(url);

            return informationString;
        }

    }

    public Autor crearAutor(String query, int start, int num) throws MalformedURLException, IOException, JSONException {
        JSONObject jsonObject = new JSONObject(APILogic(query, start, num).toString());
        JSONObject author = (JSONObject) jsonObject.get("author");
        String name = author.getString("name");
        String email = author.getString("email");
        String affiliations = author.getString("affiliations");

        autor = new Autor(name, affiliations, email);
        System.out.println("Nombre: " + autor.getName());
        System.out.println("Afiliaciones: " + autor.getAffiliations());
        System.out.println("Email: " + autor.getEmail());
        System.out.println("Exitoso!");
        return autor;
    }

    public ArrayList CrearArticulos(String query, int start, int num) throws JSONException, ProtocolException, IOException {
        JSONObject jsonObject = new JSONObject(APILogic(query, start, num).toString());
        JSONArray articlesArray = (JSONArray) jsonObject.get("articles");
        
        for (int i = 0; i < articlesArray.length(); i++) {
            JSONObject articleObject = (JSONObject) articlesArray.get(i);
            String title = (String) articleObject.get("title");
            String link = (String) articleObject.get("link");
            String authors = (String) articleObject.get("authors");
            String publication = (String) articleObject.get("publication");
            String year = (String) articleObject.get("year");
            articulos.add(new Articulo(title, link, authors, publication, year));
        }
        System.out.println("Exitoso!");
        return articulos;
    }

    public void RegistrarBDDAutor(Autor autor) {
        consulta.registrar(autor);
    }
    
    public void RegistrarBDDArticulo(Articulo articulo) {
        consulta.registrarArticulo(articulo);
    }

    public ArrayList<Autor> listarAutor() {
        return consulta.listar();
    }
    
    public ArrayList<Articulo> listarArticulo() {
        return consulta.listarArticulo();
    }

}
