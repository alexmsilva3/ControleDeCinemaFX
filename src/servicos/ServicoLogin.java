package servicos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ServicoLogin {
    mysql banco = new mysql();
    
    public boolean verificaLogin(String usuario, String senha){
        try{
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getUrl(),banco.getUsuario(),banco.getSenha());
            
            String query = "SELECT * FROM login WHERE usuario = '"+usuario+"' AND senha = '"+senha+"' ";
            
            PreparedStatement ex = conn.prepareStatement(query);
            ResultSet rs = ex.executeQuery(query);
            
            if(rs.next()){
                return true;
            }
            else{
                return false;
            }
        }
        catch (Exception e)
        {

        }
        return false;
    }
}
