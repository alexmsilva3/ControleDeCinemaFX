package controller;

import classes.Sessao;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    private JFXComboBox<String> dropFilmes;

    @FXML
    private JFXComboBox<String> dropSalas;

    @FXML
    private JFXTextField txtValor;

    @FXML
    private JFXDatePicker Data;

    @FXML
    private JFXTimePicker Hora;

    @FXML
    private Label txtLotacao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dropFilmes.getItems().clear();
        dropFilmes.setItems(servicoFilme.observableListFilme());
        
        dropSalas.getItems().clear();
        dropSalas.setItems(servicoSala.observableListSala());
        
        dropSalas.valueProperty().addListener(new ChangeListener<String>(){
            @Override public void changed(ObservableValue ov, String t, String t1) {
            txtLotacao.setText(servicoSala.buscaSala(Integer.parseInt(t1)).getCapacidade().toString());
            }
        });
    }
    
    public void redebeSessao(Sessao sessao){
        try{
            idSessao.setText(sessao.getIdSessao().toString());
            dropFilmes.setValue(sessao.getFilme().getTitulo());
            dropSalas.setValue(sessao.getSala().getIdSala().toString());
            txtValor.setText(sessao.getValorIngresso().toString());
            //converssão de data e hora
            Data.setValue(LocalDate.parse(sessao.getData().toString()));
            Hora.setValue(sessao.getHorario().toLocalTime());
            txtLotacao.setText(sessao.getIngressosDisponiveis().toString());
        }
        catch(Exception e){
            servicos.gravaLog(e.toString());
        }
        
    }
    
    @FXML
    void SalvarSessao() {
        try{
            Sessao sessao = new Sessao();
            LocalDateTime  hoje = LocalDateTime.now();
             int validador = 6;

            //valida campos
            if (dropFilmes.getValue()== null || dropFilmes.getValue().isEmpty()) {validador = validador-1; dropFilmes.setStyle("-fx-border-color: red;");}
            if (dropSalas.getValue() == null || dropSalas.getValue().isEmpty()) {validador = validador-1;dropSalas.setStyle("-fx-border-color: red;");}
            if (txtValor.getText() == null || txtValor.getText().isEmpty()) {validador = validador-1;txtValor.setStyle("-fx-border-color: red;");}
            if (Data.getValue() == null || Data.getValue().toString().isEmpty() || Data.getValue().isBefore(hoje.toLocalDate())) {validador = validador-1;Data.setStyle("-fx-border-color: red;");}
            if (Hora.getValue() == null || Hora.getValue().toString().isEmpty()) {validador = validador-1;Hora.setStyle("-fx-border-color: red;");}
            if (txtLotacao.getText() == null || txtLotacao.getText().isEmpty()) {validador = validador-1;txtLotacao.setStyle("-fx-border-color: red;");}
            
            if (validador == 6) {
                sessao.setIdSessao(Integer.parseInt(idSessao.getText()));
                
                sessao.setFilme(servicoFilme.buscaNomeFilme(dropFilmes.getValue()));
                sessao.setSala(servicoSala.buscaSala(Integer.parseInt(dropSalas.getValue())));
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
            servicos.gravaLog(e.toString());
            AnchorPaneNovaSessao.setVisible(false);
        }
    }
    
}
