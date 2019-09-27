package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import servicos.ServicoLogin;
import servicos.ServicosGerais;

/**
 * FXML Controller class
 *
 * @author alex.silva
 */
public class LoginController implements Initializable {

    ServicosGerais servicos = new ServicosGerais();
    ServicoLogin servicoLogin = new ServicoLogin();
    
    @FXML
    private AnchorPane AnchorPaneFundo;
    
    @FXML
    private JFXTextField txtUsuario;

    @FXML
    private JFXPasswordField txtSenha;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private JFXButton btnEntrar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void validarLogin() throws IOException{
        try{
            String usuario = txtUsuario.getText();
            String senha = txtSenha.getText();
            
            if (txtUsuario.getText() == null || "".equals(txtUsuario.getText()) || txtSenha.getText() == null || "".equals(txtSenha.getText()) ) {
                txtUsuario.setStyle("-fx-border-color: red;");txtSenha.setStyle("-fx-border-color: red;");
                servicos.MensagemErro("Houve um erro em seu formulário", "Verifique os campos em vermelho.");
            }
            if(servicoLogin.verificaLogin(usuario, senha)){
                AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
                AnchorPaneFundo.getChildren().setAll(a);
            }
            else{
                servicos.MensagemErro("Usuário ou Senha incorreto!", "Usuario ou senha inválido.");
            }
        }catch (Exception e){System.out.println(e);}
    }
    
    @FXML
    private void Sair(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Encerrando aplicativo");
        alert.setHeaderText("Tem certeza?");
        alert.setContentText("Todos os dados não salvos serão perdidos!");
        Optional <ButtonType> action = alert.showAndWait();
        
        if (action.get() == ButtonType.OK) {
            System.exit(0);
        }
        else if (action.get() == ButtonType.CANCEL || action.get() == ButtonType.CLOSE){
            alert.close();
        }
    }
}
