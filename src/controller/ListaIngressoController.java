package controller;

import classes.Sessao;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import servicos.ServicoSessao;
import servicos.ServicosGerais;

public class ListaIngressoController implements Initializable {

    ServicoSessao servicoSessao = new ServicoSessao();
    ServicosGerais servicos = new ServicosGerais();
    
    @FXML
    private AnchorPane AnchorPanelVendaIngresso;

    @FXML
    private JFXTextField txtSearch;


    @FXML
    private TableView<Sessao> tableViewSessoes;

    @FXML
    private TableColumn<Sessao, String> tblColFilme;

    @FXML
    private TableColumn<Sessao, String> tblColSala;

    @FXML
    private TableColumn<Sessao, String> tblColData;

    @FXML
    private TableColumn<Sessao, String> tblColHora;

    @FXML
    private TableColumn<Sessao, String> tblColPreco;

    @FXML
    private TableColumn<Sessao, String> tblColIngresso;

    ObservableList observableListSessao = FXCollections.observableArrayList(servicoSessao.listaSessao());
    
    FilteredList filtro = new FilteredList(observableListSessao, e ->true);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblColFilme.setCellValueFactory(cellFilme -> new SimpleStringProperty(cellFilme.getValue().getFilme().getTitulo()));
        tblColSala.setCellValueFactory(cellSala -> new SimpleStringProperty(cellSala.getValue().getSala().getIdSala().toString()));
        tblColData.setCellValueFactory(cellData -> new SimpleStringProperty(servicos.formataData(cellData.getValue().getData().toString())));
        tblColHora.setCellValueFactory(cellHora -> new SimpleStringProperty(cellHora.getValue().getHorario().toString()));
        tblColPreco.setCellValueFactory(cellPreco -> new SimpleStringProperty(cellPreco.getValue().getValorIngresso().toString()));
        tblColIngresso.setCellValueFactory(cellIngresso -> new SimpleStringProperty(cellIngresso.getValue().getIngressosDisponiveis().toString()));
        
        tableViewSessoes.setItems(observableListSessao);
    }

    @FXML
    public void vendeIngresso() {
        try{
            //precisa buscar o item selecionado para verificar no if se tem ou nao algo selecionado
            Sessao ses = tableViewSessoes.getSelectionModel().getSelectedItem();

            if (ses != null) {
                Sessao sessao = servicoSessao.buscaSessao(ses.getIdSessao());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/VendaIngresso.fxml"));
                Parent root = (Parent) loader.load();
                VendaIngressoController controller = loader.getController();
                controller.redebeSessao(sessao);
                AnchorPanelVendaIngresso.getChildren().setAll(root);
            }
            else{
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setContentText("Por favor selecione uma sessão na lista");
                alerta.show();
            }
        }catch (Exception e) {
            servicos.gravaLog(e.toString());
            servicos.Mensagem(Alert.AlertType.ERROR,"Cadastro Não Realizado", "Os dados não foram gravados", "Verifique o arquivo de log e tente novamente", null);
            AnchorPanelVendaIngresso.setVisible(false);
        }
    }

    @FXML
    public void VoltarMenuPrincipal() {
        try{
            AnchorPanelVendaIngresso.setVisible(false);
        }catch (Exception e) {
            AnchorPanelVendaIngresso.setVisible(false);
        }
    }
    
    @FXML
    public void search(KeyEvent event) {
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
        
        filtro.setPredicate((Predicate<? super Sessao>) (Sessao ses)->{
            if(newValue.isEmpty() || newValue == null){
                return true;
            }
            else if (ses.getFilme().getTitulo().toLowerCase().contains(newValue)){
                return true;
            }
            else if (ses.getFilme().getTitulo().toUpperCase().contains(newValue)){
                return true;
            }
            else if (ses.getFilme().getTitulo().contains(newValue)){
                return true;
            }
            return false;
            
            });
        });
        SortedList sort = new SortedList(filtro);
        sort.comparatorProperty().bind(tableViewSessoes.comparatorProperty());
        tableViewSessoes.setItems(sort);
    }
    
        
    
}
