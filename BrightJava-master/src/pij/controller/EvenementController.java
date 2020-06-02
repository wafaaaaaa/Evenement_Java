/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pij.controller;

import static com.itextpdf.text.pdf.BidiOrder.PDF;
import pij.entity.Evenement;
import pij.service.EvenementCrud;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pij.utils.PDF;
import pij.utils.connectionDB;
import javafx.print.PrinterJob;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

/**
 * FXML Controller class
 *
 * @author YURI
 */
public class EvenementController implements Initializable {

    @FXML
    private TextField tritre;
    @FXML
    private TextField tville;
    @FXML
    private TextField tdescription;
    @FXML
    private TextField tdate;
    @FXML
    private TextField tnbr;
    @FXML
    private Button bAjouter;
    @FXML
    private TableView<Evenement> table;
    
    @FXML
    private TableColumn<Evenement, String> col_titre;
   
    @FXML
    private TableColumn<Evenement, String> col_description;
    @FXML
    private TableColumn<Evenement, String> col_date;
    @FXML
    private TableColumn<Evenement, Integer> col_nbr;

    
    @FXML
    private TextField recherche1;
    public ObservableList<Evenement> tables = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> col_iduser;
    
    @FXML
    private TextField cat_id;
    @FXML
    private Label cat;
    @FXML
    private Button email;
   
    @FXML
    private Button imprimer;
    @FXML
    private Button retour;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        
        col_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_nbr.setCellValueFactory(new PropertyValueFactory<>("nbrPart"));

        try {
            EvenementCrud act = new EvenementCrud();
            Evenement An = new Evenement();
            tables = act.afficher(An);
        } catch (SQLException ex) { 

        }
        
        refresh();
        
        table.setItems((ObservableList<Evenement>) tables);
        FilteredList<Evenement> filteredData = new FilteredList<>(tables, b -> true);
        recherche1.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(A -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (A.getTitre().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                else {
                    return false;
                }
            });
        });
        SortedList<Evenement> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
      
    }

    @FXML
    private void ajouter(ActionEvent event) {
        String a = tritre.getText();
        String b = tville.getText();
        String c = tdate.getText();
        String d = tdescription.getText();
        String e = tnbr.getText();
        String f= cat_id.getText();

        int nbrr ,cat_idd;
        nbrr = Integer.parseInt(e);
        cat_idd=Integer.parseInt(f);
        EvenementCrud ac = new EvenementCrud();
        Evenement aaa = new Evenement(nbrr, a, b, c, d,cat_idd);
        ac.ajouter(aaa);
        refresh();

    }

    @FXML
    private void SelectItemes(MouseEvent event) {
         ObservableList<Evenement> oblist;
        oblist = table.getSelectionModel().getSelectedItems();
        if (oblist != null) {
            tritre.setText(oblist.get(0).getTitre());

            int max = oblist.get(0).getRef();
            tdescription.setText(oblist.get(0).getDescription());
            tdate.setText(oblist.get(0).getDate());
            tville.setText(oblist.get(0).getVille());
            tnbr.setText("" + oblist.get(0).getNbPart());
            //cat_id.setText("" + oblist.get(0).getCategorie_id());
            cat.setVisible(false);
            cat_id.setVisible(false);
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
        Evenement A = new Evenement();
        A.setTitre(tritre.getText());
        A.setNbPart(Integer.parseInt(tnbr.getText()));
        A.setDescription(tdescription.getText());
        A.setDate(tdate.getText());
        A.setVille(tville.getText());

        ObservableList<Evenement> oblist;
        oblist = table.getSelectionModel().getSelectedItems();
        int max = oblist.get(0).getRef();

        EvenementCrud act = new EvenementCrud();
        try {          

            act.modifier(A, max);
            System.out.println(max);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        refresh();

        
    }

   
    
    
    
    @FXML
    private void supp(ActionEvent event) {
        ObservableList<Evenement> oblist;
        oblist = table.getSelectionModel().getSelectedItems();
        int max = oblist.get(0).getRef();

        Evenement A = new Evenement();
        EvenementCrud act = new EvenementCrud();
        try {
            act.supprimer(max);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        refresh();

    }

   
    

    @FXML
    private void email(ActionEvent event) throws IOException {
        
        
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/pij/views/dashboard/Email.fxml")));
        stage.setScene(scene);
        stage.show(); 
        
    }

   
    @FXML
    private void imprimer(ActionEvent event) {
        
         System.out.println(" attendez  ...");
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if (printerJob.printPage(table)) {
            printerJob.endJob();
            System.out.println("printed");
        }
        
        
    }

    
    
    public void refresh() {

        
        col_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_nbr.setCellValueFactory(new PropertyValueFactory<>("nbrPart"));
        try {
            EvenementCrud act = new EvenementCrud();
            Evenement Ans = new Evenement();
            tables = act.afficher(Ans);

        } catch (SQLException ex) {

        }
        table.setItems((ObservableList<Evenement>) tables);

    }

    
    
    
    
    
}
