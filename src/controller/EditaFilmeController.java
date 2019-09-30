package controller;

import classes.Filme;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import servicos.ServicoFilme;
import servicos.ServicosGerais;


public class EditaFilmeController implements Initializable {

    ServicosGerais servicos = new ServicosGerais();
    ServicoFilme servicoFilme = new ServicoFilme();
    
    @FXML
    private AnchorPane AnchorPaneNovoFilme;
    
    @FXML
    private Label idFilme;
    
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
    
    public void redebeFilme(Filme filme){
        idFilme.setText(filme.getIdFilme().toString());
        txtTitulo.setText(filme.getTitulo());
        txtDiretor.setText(filme.getDiretor());
        txtGenero.setText(filme.getGenero());
        txtIdioma.setText(filme.getIdioma());
        txtDuracao.setText(filme.getDuracao().toString());
    }
    
    @FXML
    public void SalvarFilme() {
        try{
            Filme filme = new Filme();
             int validador = 5;

            //valida campos
            if (txtTitulo.getText() == null || txtTitulo.getText().isEmpty()) {validador = validador-1; txtTitulo.setStyle("-fx-border-color: red;");}
            if (txtDiretor.getText() == null || txtDiretor.getText().isEmpty()) {validador = validador-1;txtDiretor.setStyle("-fx-border-color: red;");}
            if (txtGenero.getText() == null || txtGenero.getText().isEmpty()) {validador = validador-1;txtGenero.setStyle("-fx-border-color: red;");}
            if (txtIdioma.getText() == null || txtIdioma.getText() == null) { validador = validador - 1;txtIdioma.setStyle("-fx-border-color: red;");}
            if (txtDuracao.getText() == null || txtDuracao.getText().isEmpty()) {validador = validador-1;txtDuracao.setStyle("-fx-border-color: red;");}

            if (validador == 5) {
                filme.setIdFilme(Integer.parseInt(idFilme.getText()));
                
                filme.setTitulo(txtTitulo.getText());
                filme.setDiretor(txtDiretor.getText());
                filme.setGenero(txtGenero.getText());
                filme.setIdioma(txtIdioma.getText());
                filme.setDuracao(Integer.parseInt(txtDuracao.getText()));

                if (servicoFilme.editaFilme(filme)) {
                    if(servicos.Mensagem(Alert.AlertType.CONFIRMATION,"Cadastro Realizado", "Dados gravados com sucesso", null, null)){
                        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/listaFilme.fxml"));
                        AnchorPaneNovoFilme.getChildren().setAll(a);
                    }
                }
                else
                {
                    servicos.Mensagem(Alert.AlertType.ERROR,"Cadastro Não Realizado", "Os dados não foram gravados", "Verifique os dados novamente", AnchorPaneNovoFilme);
                }
            }
            else{
                servicos.MensagemErro("Houve um erro em seu formulário", "Verifique os campos em vermelho");
            } 
        }catch (Exception e) {
            servicos.gravaLog(e.toString());
            servicos.Mensagem(Alert.AlertType.ERROR,"Cadastro Não Realizado", "Os dados não foram gravados", "Verifique o arquivo de log e tente novamente", null);
            AnchorPaneNovoFilme.setVisible(false);
        }
    }

    @FXML
    public void VoltarMenuPrincipal() {
        try{
            if (servicos.Mensagem(Alert.AlertType.CONFIRMATION,"Tem certeza?", "Os dados serão perdidos", "Tem certeza que deseja cancelar essa operação?", null)) {
                AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/ListaFilmes.fxml"));
                AnchorPaneNovoFilme.getChildren().setAll(a);
            }
        }catch (Exception e) {
            //servicos.gravaLog(e.toString());
            AnchorPaneNovoFilme.setVisible(false);
        }
    }

}
