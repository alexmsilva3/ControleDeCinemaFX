package controller;

import classes.Sessao;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ListaSessoesController implements Initializable {

    @FXML
    private AnchorPane AnchorPanelListaSessao;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXButton btnAddSessao;

    @FXML
    private JFXButton btnEditarSessao;

    @FXML
    private TableView<Sessao> tableViewFilmes;

    @FXML
    private TableColumn<Sessao, String> tblColIdSessao;

    @FXML
    private TableColumn<Sessao, String> tblColFime;

    @FXML
    private TableColumn<Sessao, String> tblColSala;

    @FXML
    private TableColumn<Sessao, String> tblColData;

    @FXML
    private TableColumn<Sessao, String> tblColHora;

    @FXML
    private TableColumn<Sessao, String> tblColIngresso;

    @FXML
    private JFXButton btnVoltar;

    @FXML
    private JFXButton btnRemoverSessao;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    void novaSessao() {

    }

    @FXML
    void editaSessao( ) {

    }

    

    @FXML
    void removeSessao() {

    }
    
    @FXML
    void VoltarMenuPrincipal() {

    }
}
