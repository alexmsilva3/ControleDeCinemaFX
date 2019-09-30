package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class NovoLoginController implements Initializable {

    @FXML
    private AnchorPane AnchorPaneNovoLogin;

    @FXML
    private JFXTextField txtUsuario;

    @FXML
    private JFXTextField txtNome;

    @FXML
    private JFXPasswordField txtSenha;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    void VoltarMenuPrincipal() {
    }

    @FXML
    void btnSalvarLogin() {
    }
}
