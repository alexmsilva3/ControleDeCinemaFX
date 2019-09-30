package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class NovaSalaController implements Initializable {

     @FXML
    private AnchorPane AnchorPaneNovaSala;

    @FXML
    private JFXTextField txtCapacidade;

    @FXML
    private JFXButton btnVoltar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    void SalvarSala() {

    }

    @FXML
    void VoltarMenuPrincipal() {

    }
}
