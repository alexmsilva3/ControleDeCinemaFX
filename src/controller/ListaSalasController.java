package controller;

import classes.Sala;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ListaSalasController implements Initializable {

    @FXML
    private AnchorPane AnchorPanelListaSala;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXButton btnAddSala;
    
    @FXML
    private JFXButton btnVoltar;

    @FXML
    private JFXButton btnRemoverSala;

    @FXML
    private JFXButton btnEditarSala;

     @FXML
    private TableView<Sala> tableViewFilmes;

    @FXML
    private TableColumn<Sala, String> tblColIdSala;

    @FXML
    private TableColumn<Sala, String> tblColCapacidade;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    void editaSala() {

    }

    @FXML
    void novaSala() {

    }

    @FXML
    void removeSala() {

    }
    
    @FXML
    void VoltarMenuPrincipal() {
    }
        
}
