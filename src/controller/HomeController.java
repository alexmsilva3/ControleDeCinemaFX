package controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;


public class HomeController implements Initializable {

    @FXML
    private AnchorPane AnchorPaneBg;

    @FXML
    private JFXButton btnDashboard;

    @FXML
    private JFXButton btnFilmes;

    @FXML
    private JFXButton btnSalas;

    @FXML
    private JFXButton btnSessoes;

    @FXML
    private JFXButton btnVendaIngresso;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXButton btnSair;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void dashboard(ActionEvent event) {

    }
    
    @FXML
    public void vendaIngresso(ActionEvent event) {
        try {
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/vendaIngresso.fxml"));
            AnchorPaneBg.getChildren().setAll(a);
        }catch (Exception e){}
    }

    @FXML
    public void listaFilmes() throws IOException {
        try {
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/listaFilmes.fxml"));
            AnchorPaneBg.getChildren().setAll(a);
        }catch (Exception e){}
    }

    @FXML
    public void listaSalas(ActionEvent event) {
        try {
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/listaSalas.fxml"));
            AnchorPaneBg.getChildren().setAll(a);
        }catch (Exception e){}
    }
    
    @FXML
    public void listaSessoes(ActionEvent event) {
        try {
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/listaSessoes.fxml"));
            AnchorPaneBg.getChildren().setAll(a);
        }catch (Exception e){}
    }
    
    @FXML
    public void listaLogin(ActionEvent event) {
        try {
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/listaLogin.fxml"));
            AnchorPaneBg.getChildren().setAll(a);
        }catch (Exception e){}
    }
    
    @FXML
    private void sair(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Encerrando aplicativo");
        alert.setHeaderText("Tem certeza?");
        alert.setContentText("Todos os dados não salvos serão perdidos!");
        Optional <ButtonType> action = alert.showAndWait();
        
        if (action.get() == ButtonType.OK) {
            System.exit(0);
        }
        else if (action.get() == ButtonType.CANCEL || action.get() == ButtonType.CLOSE){
            alert.close();
        }

    }
}
