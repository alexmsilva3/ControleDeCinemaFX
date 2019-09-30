package servicos;

import classes.Sala;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ServicoSala {
    mysql banco = new mysql();
    
    public void insereSala(Sala sala){
        try{
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getUrl(), banco.getUsuario(), banco.getSenha());
            
            String query = "INSERT INTO sala " +
            "(capacidade) " +
            "VALUES ('"+sala.getCapacidade()+"');";
            
            PreparedStatement ex = conn.prepareStatement(query);
            ex.execute();
            conn.close();
        }
        catch (Exception e) {
            //servico.gravaLog("Erro: Não foi possível adicionar o usuario. Motivo: "+ e);
        }
    }
    
    public ArrayList<Sala> listaSala(){
        ArrayList<Sala> listaSala = new ArrayList<>();
        
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getUrl(), banco.getUsuario(), banco.getSenha());

            String query = "SELECT * FROM Sala ";

            PreparedStatement ex = conn.prepareStatement(query);
            ResultSet rs = ex.executeQuery(query);

            while (rs.next()) {
                Sala sala = new Sala();
                
                sala.setIdSala(rs.getInt("idSala"));
                sala.setCapacidade(rs.getInt("capacidade"));

                listaSala.add(sala);
            }

            conn.close();
        } catch (Exception e) {
            //servico.gravaLog("Não foi possível buscar a sala. Motivo:" + e);
        }
        return listaSala;
    }
    
    public Sala buscaSala(int idSala){
        Sala sala = new Sala();
        
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getUrl(), banco.getUsuario(), banco.getSenha());

            String query = "SELECT * FROM sala WHERE idsala = '"+idSala+"' ";

            PreparedStatement ex = conn.prepareStatement(query);
            ResultSet rs = ex.executeQuery(query);

            while (rs.next()) {
                sala.setIdSala(rs.getInt("idSala"));
                sala.setCapacidade(rs.getInt("capacidade"));
            }

            conn.close();
        } catch (Exception e) {
            System.err.println("Erro! " + e);
            //servico.gravaLog("Não foi possível buscar a sala. Motivo:" + e);
        }
        
        return sala;
    }
    
    public boolean editaSala(Sala sala){
        try{
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getUrl(), banco.getUsuario(), banco.getSenha());
            
            String query = "UPDATE sala " +
            "SET capaciade = '"+sala.getCapacidade()+"' " +
            "WHERE idfilme = '"+sala.getIdSala()+"' ";
            
            PreparedStatement ex = conn.prepareStatement(query);
            ex.execute();
            
            conn.close();
            return true;
            
        }
        catch (Exception e) {
            //servico.gravaLog("Erro: Não foi possível editar a sala. Motivo: "+ e);
            return false;
        }
    }
    
    public void removeSala(int idSala){
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getUrl(), banco.getUsuario(), banco.getSenha());
            
            String query = "DELETE FROM sala WHERE IdLogin = '"+idSala+"' ";
            PreparedStatement ex = conn.prepareStatement(query);
            ResultSet rs = ex.executeQuery(query);
            //servico.gravaLog(query);
            
            //Remover Sessão
            //query = "DELETE FROM Sessao WHERE idsala = '"+idSala+"' ";
            //ex.execute(query);
            
            conn.close();
            
        } catch (Exception e) {
            System.err.println("Erro! " + e);
        }
    }
}
