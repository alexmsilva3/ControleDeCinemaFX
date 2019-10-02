package servicos;

import classes.Filme;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ServicoFilme {
    mysql banco = new mysql();
    ServicosGerais servico = new ServicosGerais();
    
    public boolean insereFilme(Filme filme){
        try{
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getUrl(), banco.getUsuario(), banco.getSenha());
            
            String query = "INSERT INTO filme " +
            "(titulo, diretor, genero, idioma, duracao) " +
            "VALUES ('"+filme.getTitulo()+"', '"+filme.getDiretor()+"', '"+filme.getGenero()+"', '"+filme.getIdioma()+"', '"+filme.getDuracao()+"');";
            
            PreparedStatement ex = conn.prepareStatement(query);
            ex.execute();
            conn.close();
            return true;
        }
        catch (Exception e) {
            servico.gravaLog("Erro: Não foi possível adicionar o filme. Motivo: "+ e);
            System.out.println("Erro: Não foi possível adicionar o filme. Motivo:"+ e);
            return false;
        }
    }
    
    public ArrayList<Filme> listaFilme(){
        ArrayList<Filme> listaFilme = new ArrayList<>();
        
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getUrl(), banco.getUsuario(), banco.getSenha());

            String query = "SELECT * FROM filme ";

            PreparedStatement ex = conn.prepareStatement(query);
            ResultSet rs = ex.executeQuery(query);

            while (rs.next()) {
                Filme filme = new Filme();
                
                filme.setIdFilme(rs.getInt("idfilme"));
                filme.setTitulo(rs.getString("titulo"));
                filme.setDiretor(rs.getString("diretor"));
                filme.setGenero(rs.getString("genero"));
                filme.setIdioma(rs.getString("idioma"));
                filme.setDuracao(rs.getInt("duracao"));

                listaFilme.add(filme);
            }

            conn.close();
        } catch (Exception e) {
            servico.gravaLog("Não foi possível buscar o filme. Motivo:" + e);
        }
        return listaFilme;
    }
    
    public ObservableList observableListFilme(){
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getUrl(), banco.getUsuario(), banco.getSenha());

            String query = "SELECT * FROM filme ";

            PreparedStatement ex = conn.prepareStatement(query);
            ResultSet rs = ex.executeQuery(query);
            List<String> list = new ArrayList<String>();

            while (rs.next()) {
                list.add(rs.getString("titulo"));
            }
            conn.close();
            
            ObservableList obListFilme = FXCollections.observableList(list);
            
            return obListFilme;
        } catch (Exception e) {
            servico.gravaLog("Não foi possível buscar o filme. Motivo:" + e);
            System.out.println(e);
        }
        return null;
    }
    
    public Filme buscaFilme(int idFilme){
        Filme filme = new Filme();
        
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getUrl(), banco.getUsuario(), banco.getSenha());

            String query = "SELECT * FROM filme WHERE idfilme = '"+idFilme+"' ";

            PreparedStatement ex = conn.prepareStatement(query);
            ResultSet rs = ex.executeQuery(query);

            while (rs.next()) {
                filme.setIdFilme(rs.getInt("idfilme"));
                filme.setTitulo(rs.getString("titulo"));
                filme.setDiretor(rs.getString("diretor"));
                filme.setGenero(rs.getString("genero"));
                filme.setIdioma(rs.getString("idioma"));
                filme.setDuracao(rs.getInt("duracao"));
            }

            conn.close();
        } catch (Exception e) {
            System.err.println("Erro! " + e);
            servico.gravaLog("Não foi possível buscar o filme. Motivo:" + e);
        }
        
        return filme;
    }
    
    public Filme buscaNomeFilme(String NomeFilme){
        Filme filme = new Filme();
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getUrl(), banco.getUsuario(), banco.getSenha());

            String query = "SELECT * FROM filme WHERE Titulo = '"+NomeFilme+"' ";

            PreparedStatement ex = conn.prepareStatement(query);
            ResultSet rs = ex.executeQuery(query);

            while (rs.next()) {
                filme.setIdFilme(rs.getInt("idfilme"));
                filme.setTitulo(rs.getString("titulo"));
                filme.setDiretor(rs.getString("diretor"));
                filme.setGenero(rs.getString("genero"));
                filme.setIdioma(rs.getString("idioma"));
                filme.setDuracao(rs.getInt("duracao"));
            }

            conn.close();
        } catch (Exception e) {
            System.err.println("Erro! " + e);
            servico.gravaLog("Não foi possível buscar o filme. Motivo:" + e);
        }
        
        return filme;
    }
    
    public boolean editaFilme(Filme filme){
        try{
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getUrl(), banco.getUsuario(), banco.getSenha());
            
            String query = "UPDATE filme " +
            "SET Titulo = '"+filme.getTitulo()+"',"
                    + "Diretor = '"+filme.getDiretor()+"',"
                    + "Genero = '"+filme.getGenero()+"',"
                    + "Idioma = '"+filme.getIdioma()+"',"
                    + "Duracao = '"+filme.getDuracao()+"' " +
            "WHERE idfilme = '"+filme.getIdFilme()+"' ";
            
            PreparedStatement ex = conn.prepareStatement(query);
            ex.execute();
            
            conn.close();
            servico.gravaLog("Filme id: "+filme.getIdFilme()+" |Titulo: "+filme.getTitulo()+" .Editado com sucesso");
            
            return true;
            
        }
        catch (Exception e) {
            servico.gravaLog("Erro: Não foi possível editar o filme. Motivo: "+ e);
            return false;
        }
    }
    
    public void removeFilme(int idFilme){
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getUrl(), banco.getUsuario(), banco.getSenha());
            
            String query = "DELETE FROM filme WHERE idFilme = '"+idFilme+"' ";
            PreparedStatement ex = conn.prepareStatement(query);
            ex.execute();
            servico.gravaLog(query);
            
            //Remover Sessão. Não realizado por questões didaticas
            //query = "DELETE FROM Sessao WHERE idfilme = '"+idFilme+"' ";
            //ex.execute(query);
            
            conn.close();
            
        } catch (Exception e) {
            System.err.println("Erro! " + e);
        }
    }
}
