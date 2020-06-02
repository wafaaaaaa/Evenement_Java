/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pij.entity.Categorie;
import pij.utils.connectionDB;


/**
 *
 * @author YURI
 */
public class CategorieCrud {
   Connection cn2;
    Statement st;

    public CategorieCrud() {
        cn2 = connectionDB.getInstance().getCnx();
    }
    
    public void ajouter(Categorie a) {
        try {
            String requete = "INSERT INTO categorie (id,type) VALUES (?,?)";
            PreparedStatement pst = cn2.prepareStatement(requete);
            pst.setInt(1, a.getId());
            pst.setString(2, a.getType());
          
            pst.executeUpdate();
            System.out.println("good");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
        public ObservableList<Categorie> afficher(Categorie A) throws SQLException {

        ObservableList<Categorie> arr = FXCollections.observableArrayList();
        st = cn2.createStatement();
        ResultSet rs = st.executeQuery("select * from Categorie");
        while (rs.next()) {
            arr.add(new Categorie(rs.getInt("id"),rs.getString("type")));

        }
        return arr;

    }
    
        
        
        
        
        public void modifier(Categorie A, int id) throws SQLException {

        try {
            if ((A.getType() != "")) {
                String query = "update categorie set type='" + A.getType() + "' where categorie.id=" + id;

                st = cn2.createStatement();
                st.executeUpdate(query);

                System.out.println("bien modifiée");

            } else {
                System.out.println("tu dois inserer tous les elements");
            }
        } catch (SQLException ex) {

        }

    }

        
        
          public void supprimer(int id) throws SQLException {

        st = cn2.createStatement();
        String q = "delete from categorie where id= " + id;
        PreparedStatement pre = cn2.prepareStatement(q);
        st.executeUpdate(q);
        System.out.println("tu as bien supprimé");

    }
        
        
        
}
