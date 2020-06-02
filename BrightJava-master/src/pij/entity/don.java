/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.entity;

import java.sql.Date;

/**
 *
 * @author HP
 */
public class don {
     int reference ;
    String libelle;
    int quantite;
    Date date;
    int Stock_id;
    
      public don(int reference, String libelle, int quantite, Date date, int Stock_id) {
        this.reference = reference;
        this.libelle = libelle;
        this.quantite = quantite;
        this.date = date;
        this.Stock_id = Stock_id;
    }

  public don( String libelle, int quantite, Date date, int Stock_id) {
        this.libelle = libelle;
        this.quantite = quantite;
        this.date = date;
        this.Stock_id = Stock_id;
    }

    public don() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int refrence) {
        this.reference = reference;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStock_id() {
        return Stock_id;
    }

    public void setStock_id(int Stock_id) {
        this.Stock_id = Stock_id;
    }

   
    
}
