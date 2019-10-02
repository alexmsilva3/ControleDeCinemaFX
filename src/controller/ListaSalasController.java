package controller;

import classes.Sala;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import servicos.ServicoSala;
import servicos.ServicosGerais;

public class ListaSalasController implements Initializable {
    
    ServicoSala servicoSala = new ServicoSala();
    ServicosGerais servicos = new ServicosGerais();

    @FXML
    private AnchorPane AnchorPanelListaSala;
    
    @FXML
    private TableView<Sala> tableViewSalas;

    @FXML
    private TableColumn<Sala, String> tblColIdSala;

    @FXML
    private TableColumn<Sala, String> tblColCapacidade;
    
    ObservableList observableListSala = FXCollections.observableArrayList(servicoSala.listaSala());
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblColIdSala.setCellValueFactory(new PropertyValueFactory<>("idSala"));
        tblColCapacidade.setCellValueFactory(new PropertyValueFactory<>("capacidade"));
        
        tableViewSalas.setItems(observableListSala);
    }
    
    @FXML
    void novaSala() {
        try{
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/NovaSala.fxml"));
            AnchorPanelListaSala.getChildren().setAll(a);
        }catch (Exception e) {
            AnchorPanelListaSala.setVisible(false);
        }
    }
    
    @FXML
    void editaSala() {
        try{
            //precisa buscar o item selecionado para verificar no if se tem ou nao algo selecionado
            Sala sl = tableViewSalas.getSelectionModel().getSelectedItem();

            if (sl != null) {
                Sala sala = servicoSala.buscaSala(sl.getIdSala());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditaSala.fxml"));
                Parent root = (Parent) loader.load();
                EditaSalaController controller = loader.getController();
                controller.redebeSala(sala);
                AnchorPanelListaSala.getChildren().setAll(root);
            }
            else{
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setContentText("Por favor selecione uma sala na lista");
                alerta.show();
            }
        }catch (Exception e) {
            servicos.gravaLog(e.toString());
            servicos.Mensagem(Alert.AlertType.ERROR,"Cadastro Não Realizado", "Os dados não foram gravados", "Verifique o arquivo de log e tente novamente", null);
            AnchorPanelListaSala.setVisible(false);
        }
    }

    @FXML
    void removeSala() {
        try{
            Sala sala = tableViewSalas.getSelectionModel().getSelectedItem();
            if (sala != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Tem certeza?");
                alert.setHeaderText("Os dados serão apagados");
                alert.setContentText("Tem certeza que deseja cancelar essa operação?");
                Optional <ButtonType> action = alert.showAndWait();

                if (action.get() == ButtonType.OK) {
                    servicoSala.removeSala(sala.getIdSala());
                    //refresh na pagina
                    AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/ListaSalas.fxml"));
                    AnchorPanelListaSala.getChildren().setAll(a);
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
            AnchorPanelListaSala.setVisible(false);
        }
    }
    
    @FXML
    void VoltarMenuPrincipal() {
        try{
            AnchorPanelListaSala.setVisible(false);
        }catch (Exception e) {
            AnchorPanelListaSala.setVisible(false);
        }
    }
}
