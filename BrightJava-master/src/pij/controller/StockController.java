/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pij.entity.stock;
import pij.service.serviceStock;
import pij.utils.connectionDB;

/**
 * FXML Controller class
 *
 * @author Brahim
 */
public class StockController implements Initializable {
 Connection con=connectionDB.getInstance().getCnx();

    @FXML
    private TextField typea;
    @FXML private TableView <stock> table ;
   
    @FXML private TableColumn<stock,String> type;
     
  ObservableList<stock> tables  = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
     @FXML
    private void afficher (ActionEvent event) {
        
        
        type.setCellValueFactory(new PropertyValueFactory<>("type"));

        try {
            serviceStock act = new serviceStock();
            stock An = new stock();
            tables =act.afficher(An);
        } catch (SQLException ex) {

        }
        table.setItems((ObservableList<stock>) tables);
    }
     @FXML
    private void ajouter(ActionEvent event) {
        
        String titre = typea.getText();
        
        serviceStock ac = new serviceStock();
        stock a = new stock(titre);
        ac.ajouter(a);
        
        
        
    }
     @FXML
    private void SelectItemes(MouseEvent event) { 
         ObservableList<stock> oblist;
        oblist = table.getSelectionModel().getSelectedItems();
        if (oblist != null) {
            typea.setText(oblist.get(0).getType());

           

           int max = oblist.get(0).getId();

            

        }
    }
     @FXML
    private void supp(ActionEvent event) {
      
                ObservableList<stock> oblist;
                oblist = table.getSelectionModel().getSelectedItems();
                int max = oblist.get(0).getId();

                stock A = new stock();
                serviceStock act = new serviceStock();
                try {
                    act.supprimer(max);
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
               
            
        
    }
     @FXML
    private void modifier(ActionEvent event) {
     stock A = new stock();
        A.setType(typea.getText());
       

        ObservableList<stock> oblist;
        oblist = table.getSelectionModel().getSelectedItems();
        int max = oblist.get(0).getId();

        serviceStock act = new serviceStock();
        try {
            act.modifier(A, max);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }


    
}
