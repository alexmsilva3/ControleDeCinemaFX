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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void dashboard(ActionEvent event) {
        try {
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
            AnchorPaneBg.getChildren().setAll(a);
        }catch (Exception e){}
    }
    
    @FXML
    public void vendaIngresso(ActionEvent event) {
        try {
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/ListaIngresso.fxml"));
            AnchorPaneBg.getChildren().setAll(a);
        }catch (Exception e){}
    }

    @FXML
    public void listaFilmes() throws IOException {
        try {
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/ListaFilmes.fxml"));
            AnchorPaneBg.getChildren().setAll(a);
        }catch (Exception e){}
    }

    @FXML
    public void listaSalas(ActionEvent event) {
        try {
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/ListaSalas.fxml"));
            AnchorPaneBg.getChildren().setAll(a);
        }catch (Exception e){}
    }
    
    @FXML
    public void listaSessoes(ActionEvent event) {
        try {
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/ListaSessoes.fxml"));
            AnchorPaneBg.getChildren().setAll(a);
        }catch (Exception e){}
    }
    
    @FXML
    public void listaLogin(ActionEvent event) {
        try {
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/ListaLogin.fxml"));
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
