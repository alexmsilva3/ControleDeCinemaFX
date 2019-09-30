package controller;

import classes.Filme;
import classes.Sala;
import classes.Sessao;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import servicos.*;

public class EditaSessaoController implements Initializable {

    ServicosGerais servicos = new ServicosGerais();
    ServicoSessao servicoSessao = new ServicoSessao();
    ServicoFilme servicoFilme = new ServicoFilme();
    ServicoSala servicoSala = new ServicoSala();
    
    @FXML
    private AnchorPane AnchorPaneNovaSessao;

    @FXML
    private Label idSessao;
    
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
    
    public void redebeSessao(Sessao sessao){
        Filme filme = new Filme();
        Sala sala = new Sala();
        //sessao.getData()
        
        idSessao.setText(sessao.getIdSessao().toString());
        txtFilme.setText(servicoFilme.buscaFilme(sessao.getFilme().getIdFilme()).toString());
        txtSala.setText(servicoSala.buscaSala(sessao.getSala().getIdSala()).toString());
        txtValor.setText(sessao.getValorIngresso().toString());
        
        
        //string to date/time
        //Data.setValue();
        //Hora.setText(filme.getDuracao().toString());
        
        txtLotacao.setText(sessao.getIngressosDisponiveis().toString());
    }
    
    @FXML
    void SalvarSessao() {
        try{
            Sessao sessao = new Sessao();
             int validador = 6;

            //valida campos
            if (txtFilme.getText() == null || txtFilme.getText().isEmpty()) {validador = validador-1; txtFilme.setStyle("-fx-border-color: red;");}
            if (txtSala.getText() == null || txtSala.getText().isEmpty()) {validador = validador-1;txtSala.setStyle("-fx-border-color: red;");}
            if (txtValor.getText() == null || txtValor.getText().isEmpty()) {validador = validador-1;txtValor.setStyle("-fx-border-color: red;");}
            if (Data.getValue() == null || Data.getValue().toString().isEmpty()) {validador = validador-1;Data.setStyle("-fx-border-color: red;");}
            if (Hora.getValue() == null || Hora.getValue().toString().isEmpty()) {validador = validador-1;Hora.setStyle("-fx-border-color: red;");}
            if (txtLotacao.getText() == null || txtLotacao.getText().isEmpty()) {validador = validador-1;txtLotacao.setStyle("-fx-border-color: red;");}

            if (validador == 6) {
                sessao.setIdSessao(Integer.parseInt(idSessao.getText()));
                
                sessao.setFilme(servicoFilme.buscaFilme(sessao.getFilme().getIdFilme()));
                sessao.setSala(servicoSala.buscaSala(sessao.getSala().getIdSala()));
                sessao.setValorIngresso(Integer.parseInt(txtValor.getText()));
                sessao.setIngressosDisponiveis(Integer.parseInt(txtLotacao.getText()));
                
                LocalDate data = Data.getValue();
                LocalTime hora = Hora.getValue();
                
                sessao.setData(Date.valueOf(data));
                sessao.setHorario(Time.valueOf(hora));
                

                if (servicoSessao.editaSessao(sessao)) {
                    if(servicos.Mensagem(Alert.AlertType.CONFIRMATION,"Cadastro Realizado", "Dados gravados com sucesso", null, null)){
                        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/ListaSessoes.fxml"));
                        AnchorPaneNovaSessao.getChildren().setAll(a);
                    }
                }
                else
                {
                    servicos.Mensagem(Alert.AlertType.ERROR,"Cadastro Não Realizado", "Os dados não foram gravados", "Verifique os dados novamente", AnchorPaneNovaSessao);
                }
            }
            else{
                servicos.MensagemErro("Houve um erro em seu formulário", "Verifique os campos em vermelho");
            } 
        }catch (Exception e) {
            servicos.gravaLog(e.toString());
            servicos.Mensagem(Alert.AlertType.ERROR,"Cadastro Não Realizado", "Os dados não foram gravados", "Verifique o arquivo de log e tente novamente", null);
            AnchorPaneNovaSessao.setVisible(false);
        }
    }

    @FXML
    void VoltarMenuPrincipal() {
        try{
            if (servicos.Mensagem(Alert.AlertType.CONFIRMATION,"Tem certeza?", "Os dados serão perdidos", "Tem certeza que deseja cancelar essa operação?", null)) {
                AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/ListaSessoes.fxml"));
                AnchorPaneNovaSessao.getChildren().setAll(a);
            }
        }catch (Exception e) {
            //servicos.gravaLog(e.toString());
            AnchorPaneNovaSessao.setVisible(false);
        }
    }
    
}
