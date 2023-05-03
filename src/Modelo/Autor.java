/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import org.json.JSONArray;

/**
 *
 * @author Artur
 */
public class Autor {
    public String name;
    public String affiliations;
    public String [] interest;

    public Autor(String name, String affiliations, String[] interest) {
        this.name = name;
        this.affiliations = affiliations;
        this.interest = interest;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAffiliations() {
        return affiliations;
    }

    public void setAffiliations(String affiliations) {
        this.affiliations = affiliations;
    }

    public String[] getInterest() {
        return interest;
    }

    public void setInterest(String[] interest) {
        this.interest = interest;
    }
    
}
