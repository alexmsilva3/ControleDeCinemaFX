/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controledecinemafx;

import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author alex.silva
 */
public class ControleDeCinemaFX extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException, SQLException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Controle de Cinema 1.0.1");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
