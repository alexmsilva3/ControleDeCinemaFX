package servicos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;

public class ServicosGerais {
    
    public ObservableList listaFormaPag(){
        
        List<String> list = new ArrayList<String>();
        list.add("A vista");
        list.add("Cartão Crédito");
        list.add("Catrão Débito");
        list.add("Boleto");
        list.add("Carnê");
        
        ObservableList obListFormaPag = FXCollections.observableList(list);
        
        return obListFormaPag;
    }
    
    public ObservableList listaUF(){
        
        List<String> list = new ArrayList<String>();
        
        list.add("AC");
        list.add("AL");
        list.add("AM");
        list.add("AP");
        list.add("BA");
        list.add("CE");
        list.add("DF");
        list.add("ES");
        list.add("GO");
        list.add("MA");
        list.add("MG");
        list.add("MS");
        list.add("MT");
        list.add("PA");
        list.add("PB");
        list.add("PE");
        list.add("PI");
        list.add("PR");
        list.add("RJ");
        list.add("RN");
        list.add("RO");
        list.add("RR");
        list.add("RS");
        list.add("SC");
        list.add("SE");
        list.add("SP");
        list.add("TO");
        
        ObservableList obListUF = FXCollections.observableList(list);
        
        return obListUF;
    }
    
     public ObservableList listaEspecialidades(){
        
        List<String> list = new ArrayList<String>();
        list.add("Acupuntura");        
        list.add("Fisioterapia em Acupuntura");
        list.add("Fisioterapia Aquática");
        list.add("Fisioterapia Cardiovascular");
        list.add("Fisioterapia Dermatofuncional");
        list.add("Fisioterapia Esportiva");
        list.add("Fisioterapia em Gerontologia");
        list.add("Fisioterapia do Trabalho");
        list.add("Fisioterapia Neurofuncional");
        list.add("Fisioterapia em Oncologia");
        list.add("Fisioterapia Respiratória");
        list.add("Fisioterapia Traumato-Ortopédica");
        list.add("Fisioterapia em Osteopatia");
        list.add("Fisioterapia em Quiropraxia");
        list.add("Fisioterapia em Saúde da Mulher");
        list.add("Fisioterapia em Terapia Intensiva");
        
        ObservableList obListEspecialidade = FXCollections.observableList(list);
        
        return obListEspecialidade;
    }
     
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
    
    public boolean validaCPF(String CPF) {
        CPF = CPF.replace(".","");
        CPF = CPF.replace("-", "");
        
        if (CPF.equals("00000000000") || CPF.equals("11111111111") ||
            CPF.equals("22222222222") || CPF.equals("33333333333") ||
            CPF.equals("44444444444") || CPF.equals("55555555555") ||
            CPF.equals("66666666666") || CPF.equals("77777777777") ||
            CPF.equals("88888888888") || CPF.equals("99999999999") ||
            (CPF.length() != 11))
            return(false);
          
        char dig10, dig11;
        int sm, i, r, num, peso;
          
        sm = 0;
        peso = 10;
        for (i=0; i<9; i++) {
            num = (int)(CPF.charAt(i) - 48); 
            sm = sm + (num * peso);
            peso = peso - 1;
        }

        r = 11 - (sm % 11);
        if ((r == 10) || (r == 11))
            dig10 = '0';
        else dig10 = (char)(r + 48); // converte para caractere numerico

        sm = 0;
        peso = 11;
        for(i=0; i<10; i++) {
        num = (int)(CPF.charAt(i) - 48);
        sm = sm + (num * peso);
        peso = peso - 1;
        }

        r = 11 - (sm % 11);
        if ((r == 10) || (r == 11))
             dig11 = '0';
        else dig11 = (char)(r + 48);

        if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))){
            return true;
        }
        else{
            return false;
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
