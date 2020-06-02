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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import pij.entity.stock;
import pij.utils.connectionDB;

/**
 *
 * @author HP
 */
public class serviceStock {
     Connection cn2;
    Statement st;

    public serviceStock() {
        cn2 = connectionDB.getInstance().getCnx();
    }
    
     public ObservableList<stock> afficher(stock s) throws SQLException {

        ObservableList<stock> arr = FXCollections.observableArrayList();
        st = cn2.createStatement();
        ResultSet rs = st.executeQuery("select * from stock");
        while (rs.next()) {
            arr.add(new stock(rs.getString("type")));

        }
        return arr;

    }
     
     public void ajouter(stock s) {
        try {
            String requete = "INSERT INTO stock (type) VALUES (?)";
            PreparedStatement pst = cn2.prepareStatement(requete);
            pst.setString(1, s.getType());
          
            pst.executeUpdate();
             Alert alert =new Alert(AlertType.INFORMATION);
            alert.setTitle("Add Stock!");
            alert.setHeaderText("information!");
            alert.setContentText("Stock bien Ajouter!");
            alert.showAndWait();
        } catch (SQLException ex) {
            
            System.out.println(ex.getMessage());
        }
    }
      public void supprimer(int id) throws SQLException {

        st = cn2.createStatement();
        String q = "delete from stock where id= " + id;
        PreparedStatement pre = cn2.prepareStatement(q);
        st.executeUpdate(q);
       Alert alert =new Alert(AlertType.INFORMATION);
            alert.setTitle("supprission de  Stock!");
            alert.setHeaderText("information!");
            alert.setContentText("Stock bien supprimer!");
            alert.showAndWait();

    }
       public void modifier(stock A, int id) throws SQLException {

        try {
            if ((A.getType() != "")) {
                String query = "update stock set type='" + A.getType() + "' where categorie.id=" + id;

                st = cn2.createStatement();
                st.executeUpdate(query);

                System.out.println("bien modifiée");

            } else {
                System.out.println("tu dois inserer tous les elements");
            }
        } catch (SQLException ex) {

        }

    }
        
}
