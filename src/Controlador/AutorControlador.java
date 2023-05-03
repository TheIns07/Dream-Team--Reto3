/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Autor;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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

    public void crearAutor(String query, int start, int num) throws MalformedURLException, IOException, JSONException {

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
            JSONObject jsonObject = new JSONObject(informationString.toString());

            JSONObject author = (JSONObject) jsonObject.get("author");
            JSONArray interestsArray = author.getJSONArray("interests");
            String name = author.getString("name");

            String[] interestsArrayConverted = new String[interestsArray.length()];

            for (int i = 0; i < interestsArray.length(); i++) {
                interestsArrayConverted[i] = interestsArray.getString(i);
            }
            String affiliations = author.getString("affiliations");

            Autor autor = new Autor(name, affiliations, interestsArrayConverted);
            System.out.println("Nombre: " + autor.getName());
            System.out.println("Intereses: ");
             for (int i = 0; i < interestsArray.length(); i++) {
                 System.out.println(interestsArrayConverted[i]);
               
            }
            System.out.println("Afiliaciones: " + autor.getAffiliations());
        }

    }
}
