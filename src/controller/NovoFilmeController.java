package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;


public class NovoFilmeController implements Initializable {

    @FXML
    private AnchorPane AnchorPaneNovoFilme;
    
    @FXML
    private JFXTextField txtTitulo;

    @FXML
    private JFXTextField txtDiretor;

    @FXML
    private JFXTextField txtGenero;

    @FXML
    private JFXTextField txtIdioma;

    @FXML
    private JFXTextField txtDuracao;

    @FXML
    private JFXButton btnVoltar;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    void SalvarFilme() {

    }

    @FXML
    void VoltarMenuPrincipal() {

    }

}
