/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.controller;

import pij.entity.Categorie;
import pij.service.CategorieCrud;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author YURI
 */
public class CategorieController implements Initializable {

    @FXML
    private TextField tnbr;
    @FXML
    private Button bAjouter;
    @FXML
    private TableView<Categorie> table;
    @FXML
    private TableColumn<Categorie, Integer> col_iduser;
    @FXML
    private TableColumn<Categorie, Integer> col_titre;
    @FXML
    private TableColumn<Categorie, String> col_ville;
    @FXML
    private TextField recherche1;
    public ObservableList<Categorie> tables = FXCollections.observableArrayList();
    @FXML
    private Button retour1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        
        String titre = tnbr.getText();
        
        CategorieCrud ac = new CategorieCrud();
        Categorie a = new Categorie(titre);
        ac.ajouter(a);
        
        
        
    }

    @FXML
    private void SelectItemes(MouseEvent event) { 
         ObservableList<Categorie> oblist;
        oblist = table.getSelectionModel().getSelectedItems();
        if (oblist != null) {
            tnbr.setText(oblist.get(0).getType());

           

           int max = oblist.get(0).getId();

            

        }
    }

    @FXML
    private void modifier(ActionEvent event) {
     Categorie A = new Categorie();
        A.setType(tnbr.getText());
       

        ObservableList<Categorie> oblist;
        oblist = table.getSelectionModel().getSelectedItems();
        int max = oblist.get(0).getId();

        CategorieCrud act = new CategorieCrud();
        try {
            act.modifier(A, max);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void supp(ActionEvent event) {
      
                ObservableList<Categorie> oblist;
                oblist = table.getSelectionModel().getSelectedItems();
                int max = oblist.get(0).getId();

                Categorie A = new Categorie();
                CategorieCrud act = new CategorieCrud();
                try {
                    act.supprimer(max);
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
               
            
        
    }

    @FXML
    private void afficher(ActionEvent event) {
        
        
        col_titre.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_ville.setCellValueFactory(new PropertyValueFactory<>("type"));

        try {
            CategorieCrud act = new CategorieCrud();
            Categorie An = new Categorie();
            tables = act.afficher(An);
        } catch (SQLException ex) {

        }
        table.setItems((ObservableList<Categorie>) tables);
    }


    
    @FXML
    private void retour1(ActionEvent event) throws IOException {
      Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        
        stage.close();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/pij/views/dashboard/Evenement.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    
   
}
