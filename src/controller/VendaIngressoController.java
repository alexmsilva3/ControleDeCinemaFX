package controller;

import classes.Sessao;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import servicos.ServicoSessao;
import servicos.ServicosGerais;

public class VendaIngressoController implements Initializable {
    
    ServicoSessao servicoSessao = new ServicoSessao();
    ServicosGerais servicos = new ServicosGerais();
    
    @FXML
    private AnchorPane AnchorPaneVendaIngresso;

    @FXML
    private Label idSessao;
    
    @FXML
    private Label ingressosDisponiveis;
    
    @FXML
    private Label labelFilme;

    @FXML
    private Label labelSala;

    @FXML
    private Label labelData;

    @FXML
    private Label labelHora;

    @FXML
    private Label labelPreco;

    @FXML
    private JFXTextField inputIngresso;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void redebeSessao(Sessao sessao){
        try{
            idSessao.setText(sessao.getIdSessao().toString());
            labelFilme.setText(sessao.getFilme().getTitulo());
            labelSala.setText(sessao.getSala().getIdSala().toString());
            labelPreco.setText(sessao.getValorIngresso().toString());

            //converssão de data e hora
            labelData.setText(servicos.formataData(sessao.getData().toString()));
            labelHora.setText(sessao.getHorario().toString());
            ingressosDisponiveis.setText(sessao.getIngressosDisponiveis().toString());
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }

    @FXML
    public void Vender() {
        try{
            int qnt = Integer.parseInt(inputIngresso.getText());
            int id = Integer.parseInt(idSessao.getText());
            int ingDisponivel = Integer.parseInt(ingressosDisponiveis.getText());
            
            if (qnt < ingDisponivel) {
                if (servicoSessao.vendeIngresso(id, qnt)) {
                    if(servicos.Mensagem(Alert.AlertType.CONFIRMATION,"Venda Realizada", "Venda realizada com sucesso", null, null)){
                        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/ListaIngresso.fxml"));
                        AnchorPaneVendaIngresso.getChildren().setAll(a);
                    }
                }
                else{
                    servicos.Mensagem(Alert.AlertType.ERROR,"Venda Não Realizada", "Os ingressos não foram vendidos", "Verifique os dados novamente", AnchorPaneVendaIngresso);
                }
            }
            else{
                    servicos.Mensagem(Alert.AlertType.ERROR,"Erro!", "Não é possível realizar a venda.", "Número de ingresso excede o disponivel", AnchorPaneVendaIngresso);
                }
        }catch (Exception e){
            servicos.gravaLog(e.toString());
        }
    }
    
    @FXML
    public void VoltarMenuPrincipal() {
        try{
            if (servicos.Mensagem(Alert.AlertType.CONFIRMATION,"Tem certeza?", "Os dados serão perdidos", "Tem certeza que deseja cancelar essa operação?", null)) {
                AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/ListaIngresso.fxml"));
                AnchorPaneVendaIngresso.getChildren().setAll(a);
            }
        }catch (Exception e) {
            servicos.gravaLog(e.toString());
            AnchorPaneVendaIngresso.setVisible(false);
        }
    }
    
}
