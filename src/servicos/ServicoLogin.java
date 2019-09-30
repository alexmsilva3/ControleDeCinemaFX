package servicos;

import classes.Login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
    
    public void insereFilme(Login login){
        try{
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getUrl(), banco.getUsuario(), banco.getSenha());
            
            String query = "INSERT INTO login " +
            "(nome, usuario, senha) " +
            "VALUES ('"+login.getNome()+"', '"+login.getUsuario()+"', '"+login.getSenha()+"');";
            
            PreparedStatement ex = conn.prepareStatement(query);
            ex.execute();
            conn.close();
        }
        catch (Exception e) {
            //servico.gravaLog("Erro: Não foi possível adicionar o usuario. Motivo: "+ e);
        }
    }
    
    public ArrayList<Login> listaFilme(){
        ArrayList<Login> listaLogin = new ArrayList<>();
        
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getUrl(), banco.getUsuario(), banco.getSenha());

            String query = "SELECT * FROM Login ";

            PreparedStatement ex = conn.prepareStatement(query);
            ResultSet rs = ex.executeQuery(query);

            while (rs.next()) {
                Login login = new Login();
                
                login.setIdLogin(rs.getInt("idlogin"));
                login.setNome(rs.getString("nome"));
                login.setUsuario(rs.getString("usuario"));
                login.setSenha(rs.getString("senha"));

                listaLogin.add(login);
            }

            conn.close();
        } catch (Exception e) {
            //servico.gravaLog("Não foi possível buscar o usuario. Motivo:" + e);
        }
        return listaLogin;
    }
    
    public Login buscaLogin(int idLogin){
        Login login = new Login();
        
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getUrl(), banco.getUsuario(), banco.getSenha());

            String query = "SELECT * FROM login WHERE idlogin = '"+idLogin+"' ";

            PreparedStatement ex = conn.prepareStatement(query);
            ResultSet rs = ex.executeQuery(query);

            while (rs.next()) {
                login.setIdLogin(rs.getInt("idlogin"));
                login.setNome(rs.getString("nome"));
                login.setUsuario(rs.getString("usuario"));
                login.setSenha(rs.getString("senha"));
            }

            conn.close();
        } catch (Exception e) {
            System.err.println("Erro! " + e);
            //servico.gravaLog("Não foi possível buscar o usuario. Motivo:" + e);
        }
        
        return login;
    }
    
    public boolean editaLogin(Login login){
        try{
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getUrl(), banco.getUsuario(), banco.getSenha());
            
            String query = "UPDATE login " +
            "SET Nome = '"+login.getNome()+"',"
                    + "Usuario = '"+login.getUsuario()+"',"
                    + "Senha = '"+login.getSenha()+"' " +
            "WHERE idfilme = '"+login.getIdLogin()+"' ";
            
            PreparedStatement ex = conn.prepareStatement(query);
            ex.execute();
            
            conn.close();
            //servico.gravaLog("Usuario id: "+login.getIdLogin()+" |Nome: "+login.getNome()+" .Editado com sucesso");
            
            return true;
            
        }
        catch (Exception e) {
            //servico.gravaLog("Erro: Não foi possível editar o usuario. Motivo: "+ e);
            return false;
        }
    }
    
    public void removeLogin(int idLogin){
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getUrl(), banco.getUsuario(), banco.getSenha());
            
            String query = "DELETE FROM Login WHERE IdLogin = '"+idLogin+"' ";
            PreparedStatement ex = conn.prepareStatement(query);
            ResultSet rs = ex.executeQuery(query);
            //servico.gravaLog(query);
            conn.close();
            
        } catch (Exception e) {
            System.err.println("Erro! " + e);
        }
    }
}
