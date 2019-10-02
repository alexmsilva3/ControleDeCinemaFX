package controller;

import classes.Sala;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import servicos.ServicoSala;
import servicos.ServicosGerais;

public class NovaSalaController implements Initializable {
    
    ServicosGerais servicos = new ServicosGerais();
    ServicoSala servicoSala = new ServicoSala();

     @FXML
    private AnchorPane AnchorPaneNovaSala;

    @FXML
    private JFXTextField txtCapacidade;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    void SalvarSala() {
        try{
            Sala sala = new Sala();
             int validador = 1;

            //valida campos
            if (txtCapacidade.getText() == null || txtCapacidade.getText().isEmpty()) {validador = validador-1; txtCapacidade.setStyle("-fx-border-color: red;");}

            if (validador == 1) {
                sala.setCapacidade(Integer.parseInt(txtCapacidade.getText()));

                if (servicoSala.insereSala(sala)) {
                    if(servicos.Mensagem(Alert.AlertType.CONFIRMATION,"Cadastro Realizado", "Dados gravados com sucesso", null, null)){
                        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/ListaSalas.fxml"));
                        AnchorPaneNovaSala.getChildren().setAll(a);
                    }
                }
                else
                {
                    servicos.Mensagem(Alert.AlertType.ERROR,"Cadastro Não Realizado", "Os dados não foram gravados", "Verifique os dados novamente", AnchorPaneNovaSala);
                }
            }
            else{
                servicos.MensagemErro("Houve um erro em seu formulário", "Verifique os campos em vermelho");
            } 
        }catch (Exception e) {
            servicos.gravaLog(e.toString());
            servicos.Mensagem(Alert.AlertType.ERROR,"Cadastro Não Realizado", "Os dados não foram gravados", "Verifique o arquivo de log e tente novamente", null);
            AnchorPaneNovaSala.setVisible(false);
        }
    }

    @FXML
    void VoltarMenuPrincipal() {
        try{
            if (servicos.Mensagem(Alert.AlertType.CONFIRMATION,"Tem certeza?", "Os dados serão perdidos", "Tem certeza que deseja cancelar essa operação?", null)) {
                AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/ListaSalas.fxml"));
                AnchorPaneNovaSala.getChildren().setAll(a);
            }
        }catch (Exception e) {
            servicos.gravaLog(e.toString());
            AnchorPaneNovaSala.setVisible(false);
        }
    }
}
