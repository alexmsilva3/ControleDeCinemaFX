package servicos;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;

public class ServicosGerais {
     
     public String formataData(String data){
        data = data.replaceAll("-", "/");
        String[] s = data.split("/");
        String novaData = s[2]+"/"+s[1]+"/"+s[0];
        
        return novaData;
     }
    
    public boolean Mensagem(AlertType tipo, String titulo, String head, String msg, AnchorPane anchor){
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(head);
        alert.setContentText(msg);
        Optional <ButtonType> action = alert.showAndWait();
        
        if (action.get() == ButtonType.OK) {
            if (anchor != null) {
                anchor.setVisible(false);
                return true;
            }
        }
        else if (action.get() == ButtonType.CANCEL || action.get() == ButtonType.CLOSE){
            alert.close();
            return false;
        }
        return true;
    }
    
    public void MensagemErro(String head, String msg){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro!");
        alert.setHeaderText(head);
        alert.setContentText(msg);
        Optional <ButtonType> action = alert.showAndWait();
        
        if (action.get() == ButtonType.OK) {
            alert.close();
        }
        else if (action.get() == ButtonType.CANCEL || action.get() == ButtonType.CLOSE){
            alert.close();
        }
    }
    
    public void gravaLog(String msg){
        String arquivo = "log.txt";
        LocalDate data = LocalDate.now();
        LocalTime hora = LocalTime.now();
        
        
        try {
            BufferedWriter saida = new BufferedWriter(new FileWriter(arquivo,true));
            saida.write(formataData(data.toString())+" "+hora+ " : "+ msg);
            saida.newLine();
            saida.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(ServicosGerais.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
