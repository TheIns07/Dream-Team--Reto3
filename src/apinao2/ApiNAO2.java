/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apinao2;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.json.simple.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
        Scanner WF = new Scanner(System.in);
        System.out.println("Escribe el ID del autor que deseas ver: ");
        String query = WF.next();
        System.out.println("Numero de referencia de inicio: ");
        int start = WF.nextInt();
        System.out.println("Cantidad de articulos: ");
        int num = WF.nextInt();
        String BASE_URL = "https://serpapi.com/search.json?engine=google_scholar_author&author_id="+query+
                "&api_key=408a30522a569d88a68d13cb09c0719a3d8b3429b625774f76fad82fa8d191af&start="+start+"&num="+num;        
        
        URL url = new URL(BASE_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        
        int responseCode = conn.getResponseCode();
        
        if(responseCode != 200){
            throw new RuntimeException("HTTPResponse: "+ responseCode);
        } else {
            StringBuilder informationString  = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());
            
            while(scanner.hasNext()){
                informationString.append(scanner.nextLine());
            }
             scanner.close();
               // Analizar el JSON
        System.out.println(url);
        JSONObject jsonObject = new JSONObject(informationString.toString());
        
        JSONObject author =  (JSONObject) jsonObject.get("author");

        System.out.println("Autor: "+author);
        
//        // Obtener los valores de las claves del JSON
//        String activity = jsonObject.getString("pagination");
//        int participants = jsonObject.getInt("result_id");
//        String type = jsonObject.getString("type");
//
//        // Imprimir los valores
//        System.out.println("position: " + activity);
//        System.out.println("result_id: " + participants);
//        System.out.println("type: " + type);
        }
    }
    
}
