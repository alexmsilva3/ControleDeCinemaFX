package controller;

import classes.Filme;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import servicos.ServicoFilme;
import servicos.ServicosGerais;

public class ListaFilmesController implements Initializable {
    
    ServicoFilme servicoFilme = new ServicoFilme();
    ServicosGerais servicos = new ServicosGerais();

    @FXML
    private AnchorPane AnchorPanelListaFilme;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private TableView<Filme> tableViewFilmes;

    @FXML
    private TableColumn<Filme, String> tblColTitulo;

    @FXML
    private TableColumn<Filme, String> tblColGenero;

    @FXML
    private TableColumn<Filme, String> tblColDuracao;
    
    ObservableList observableListFilme = FXCollections.observableArrayList(servicoFilme.listaFilme());
    
    FilteredList filtro = new FilteredList(observableListFilme, e ->true);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblColTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        tblColGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        tblColDuracao.setCellValueFactory(new PropertyValueFactory<>("duracao"));
        
        tableViewFilmes.setItems(observableListFilme);
    }    

    @FXML
    public void novoFilme() {
        try{
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/NovoFilme.fxml"));
            AnchorPanelListaFilme.getChildren().setAll(a);
        }catch (Exception e) {
            AnchorPanelListaFilme.setVisible(false);
        }
    }
    
    @FXML
    public void editaFilme() {
        try{
            //precisa buscar o item selecionado para verificar no if se tem ou nao algo selecionado
            Filme flm = tableViewFilmes.getSelectionModel().getSelectedItem();

            if (flm != null) {
                Filme filme = servicoFilme.buscaFilme(flm.getIdFilme());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditaFilme.fxml"));
                Parent root = (Parent) loader.load();
                EditaFilmeController controller = loader.getController();
                controller.redebeFilme(filme);
                AnchorPanelListaFilme.getChildren().setAll(root);
            }
            else{
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setContentText("Por favor selecione um filme na lista");
                alerta.show();
            }
        }catch (Exception e) {
            servicos.gravaLog(e.toString());
            servicos.Mensagem(Alert.AlertType.ERROR,"Cadastro Não Realizado", "Os dados não foram gravados", "Verifique o arquivo de log e tente novamente", null);
            AnchorPanelListaFilme.setVisible(false);
        }
    }

    @FXML
    public void removeFilme() {
        try{
            Filme filme = tableViewFilmes.getSelectionModel().getSelectedItem();
            if (filme != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Tem certeza?");
                alert.setHeaderText("Os dados serão apagados");
                alert.setContentText("Tem certeza que deseja cancelar essa operação?");
                Optional <ButtonType> action = alert.showAndWait();

                if (action.get() == ButtonType.OK) {
                    servicoFilme.removeFilme(filme.getIdFilme());
                    //refresh na pagina
                    AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/ListaFilmes.fxml"));
                    AnchorPanelListaFilme.getChildren().setAll(a);
                }
                else if (action.get() == ButtonType.CANCEL || action.get() == ButtonType.CLOSE){
                    alert.close();
                }
            }
            else{
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setContentText("Por favor selecione um login na lista");
                alerta.show();
            }
        }catch (Exception e) {
            servicos.gravaLog(e.toString());
            servicos.Mensagem(Alert.AlertType.ERROR,"Cadastro Não Realizado", "Os dados não foram gravados", "Verifique o arquivo de log e tente novamente", null);
            AnchorPanelListaFilme.setVisible(false);
        }
    }
    
    @FXML
    public void VoltarMenuPrincipal() {
        try{
            AnchorPanelListaFilme.setVisible(false);
        }catch (Exception e) {
            AnchorPanelListaFilme.setVisible(false);
        }
    }
    
    @FXML
    private void search(KeyEvent event){
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
        
        filtro.setPredicate((Predicate<? super Filme>) (Filme flm)->{
            if(newValue.isEmpty() || newValue == null){
                return true;
            }
            else if (flm.getTitulo().toLowerCase().contains(newValue)){
                return true;
            }
            else if (flm.getTitulo().toUpperCase().contains(newValue)){
                return true;
            }
            else if (flm.getTitulo().contains(newValue)){
                return true;
            }
            return false;
            });
        });
        SortedList sort = new SortedList(filtro);
        sort.comparatorProperty().bind(tableViewFilmes.comparatorProperty());
        tableViewFilmes.setItems(sort);
    }
}
