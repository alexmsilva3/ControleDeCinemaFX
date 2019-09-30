/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classes.Filme;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ListaFilmesController implements Initializable {

     @FXML
    private AnchorPane AnchorPanelListaFilme;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXButton btnAddFilme;

    @FXML
    private JFXButton btnEditarFilme;

    @FXML
    private TableView<Filme> tableViewFilmes;

    @FXML
    private TableColumn<Filme, String> tblColTitulo;

    @FXML
    private TableColumn<Filme, String> tblColGenero;

    @FXML
    private TableColumn<Filme, String> tblColDuracao;

    @FXML
    private JFXButton btnVoltar;

    @FXML
    private JFXButton btnRemoverFilme;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    public void novoFilme() {

    }
    
    @FXML
    public void editaFilme() {

    }

    @FXML
    public void removeFilme() {

    }
    
    @FXML
    public void VoltarMenuPrincipal() {

    }
}
