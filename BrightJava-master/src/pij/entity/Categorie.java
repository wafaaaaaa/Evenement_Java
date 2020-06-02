/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.entity;

/**
 *
 * @author Dell
 */
public class Categorie {
    
    private int id ;
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", type=" + type + '}';
    }

    public Categorie(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public Categorie() {
    }

    public Categorie(String type) {
        this.type = type;
    }
    
}
