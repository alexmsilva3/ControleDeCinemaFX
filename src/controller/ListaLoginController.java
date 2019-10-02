package controller;

import classes.Login;
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
import servicos.ServicoLogin;
import servicos.ServicosGerais;

public class ListaLoginController implements Initializable {

    ServicosGerais servicos = new ServicosGerais();
    ServicoLogin servicoLogin = new ServicoLogin();
    
    @FXML
    private AnchorPane AnchorPanelListaLogin;

    @FXML
    private TableView<Login> tableViewUsuarios;
    
    @FXML
    private TableColumn<Login, String> tblColN;

    @FXML
    private TableColumn<Login, String> tblColNome;

    @FXML
    private TableColumn<Login, String> tblColUsuario;
    
    ObservableList observableListLogin = FXCollections.observableArrayList(servicoLogin.listaLogin());

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblColN.setCellValueFactory(new PropertyValueFactory<>("idlogin"));
        tblColNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tblColUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        
        tableViewUsuarios.setItems(observableListLogin);
    }    
    
    @FXML
    public void VoltarMenuPrincipal() {
        try{
            AnchorPanelListaLogin.setVisible(false);
        }catch (Exception e) {
            AnchorPanelListaLogin.setVisible(false);
        }
    }

    @FXML
    public void editaLogin() {
        try{
            //precisa buscar o item selecionado para verificar no if se tem ou nao algo selecionado
            Login log = tableViewUsuarios.getSelectionModel().getSelectedItem();

            if (log != null) {
                Login login = servicoLogin.buscaLogin(log.getIdLogin());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditaLogin.fxml"));
                Parent root = (Parent) loader.load();
                EditaLoginController controller = loader.getController();
                controller.redebeLogin(login);
                AnchorPanelListaLogin.getChildren().setAll(root);
            }
            else{
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setContentText("Por favor selecione um paciente na lista");
                alerta.show();
            }
        }catch (Exception e) {
            servicos.gravaLog(e.toString());
            servicos.Mensagem(Alert.AlertType.ERROR,"Cadastro Não Realizado", "Os dados não foram gravados", "Verifique o arquivo de log e tente novamente", null);
            AnchorPanelListaLogin.setVisible(false);
        }
    }

    @FXML
    public void novoLogin() {
        try{
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/NovoLogin.fxml"));
            AnchorPanelListaLogin.getChildren().setAll(a);
        }catch (Exception e) {
            AnchorPanelListaLogin.setVisible(false);
        }
    }

    @FXML
    public void removeLogin() {
        try{
            Login login = tableViewUsuarios.getSelectionModel().getSelectedItem();
            if (login != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Tem certeza?");
                alert.setHeaderText("Os dados serão apagados");
                alert.setContentText("Tem certeza que deseja cancelar essa operação?");
                Optional <ButtonType> action = alert.showAndWait();

                if (action.get() == ButtonType.OK) {
                    servicoLogin.removeLogin(login.getIdLogin());
                    //refresh na pagina
                    AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/ListaLogin.fxml"));
                    AnchorPanelListaLogin.getChildren().setAll(a);
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
            AnchorPanelListaLogin.setVisible(false);
        }
    }
}
