package controller;

import classes.Login;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import servicos.ServicoLogin;
import servicos.ServicosGerais;

public class EditaLoginController implements Initializable {

    ServicosGerais servicos = new ServicosGerais();
    ServicoLogin servicoLogin = new ServicoLogin();
    
    @FXML
    private AnchorPane AnchorPaneNovoLogin;

    @FXML
    private Label idLogin;
    
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
    
    public void redebeLogin(Login login){
        idLogin.setText(login.getIdLogin().toString());
        txtUsuario.setText(login.getUsuario());
        txtNome.setText(login.getNome());
        txtSenha.setText("");
    }
    
    @FXML
    void SalvarLogin() {
        try{
            Login login = new Login();
             int validador = 3;

            //valida campos
            if (txtUsuario.getText() == null || txtUsuario.getText().isEmpty()) {validador = validador-1; txtUsuario.setStyle("-fx-border-color: red;");}
            if (txtNome.getText() == null || txtNome.getText().isEmpty()) {validador = validador-1;txtNome.setStyle("-fx-border-color: red;");}
            if (txtSenha.getText() == null || txtSenha.getText().isEmpty()) {validador = validador-1;txtSenha.setStyle("-fx-border-color: red;");}

            if (validador == 3) {
                login.setIdLogin(Integer.parseInt(idLogin.getText()));
                
                login.setUsuario(txtUsuario.getText());
                login.setNome(txtNome.getText());
                login.setSenha(txtSenha.getText());

                if (servicoLogin.editaLogin(login)) {
                    if(servicos.Mensagem(Alert.AlertType.CONFIRMATION,"Cadastro Realizado", "Dados gravados com sucesso", null, null)){
                        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/ListaLogin.fxml"));
                        AnchorPaneNovoLogin.getChildren().setAll(a);
                    }
                }
                else
                {
                    servicos.Mensagem(Alert.AlertType.ERROR,"Cadastro Não Realizado", "Os dados não foram gravados", "Verifique os dados novamente", AnchorPaneNovoLogin);
                }
            }
            else{
                servicos.MensagemErro("Houve um erro em seu formulário", "Verifique os campos em vermelho");
            } 
        }catch (Exception e) {
            servicos.gravaLog(e.toString());
            servicos.Mensagem(Alert.AlertType.ERROR,"Cadastro Não Realizado", "Os dados não foram gravados", "Verifique o arquivo de log e tente novamente", null);
            AnchorPaneNovoLogin.setVisible(false);
        }
    }
    
    @FXML
    void VoltarMenuPrincipal() {
        try{
            if (servicos.Mensagem(Alert.AlertType.CONFIRMATION,"Tem certeza?", "Os dados serão perdidos", "Tem certeza que deseja cancelar essa operação?", null)) {
                AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/ListaLogin.fxml"));
                AnchorPaneNovoLogin.getChildren().setAll(a);
            }
        }catch (Exception e) {
            servicos.gravaLog(e.toString());
            AnchorPaneNovoLogin.setVisible(false);
        }
    }

    
}
