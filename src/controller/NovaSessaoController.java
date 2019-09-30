
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class NovaSessaoController implements Initializable {

    @FXML
    private AnchorPane AnchorPaneNovaSessao;

    @FXML
    private JFXTextField txtFilme;

    @FXML
    private JFXTextField txtSala;

    @FXML
    private JFXTextField txtValor;

    @FXML
    private JFXButton btnVoltar;

    @FXML
    private JFXDatePicker Data;

    @FXML
    private JFXTimePicker Hora;

    @FXML
    private JFXTextField txtLotacao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    void SalvarSessao() {

    }

    @FXML
    void VoltarMenuPrincipal() {

    }
    
}
