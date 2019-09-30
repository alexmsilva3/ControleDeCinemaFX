package servicos;

import classes.Filme;
import java.sql.*;
import java.util.ArrayList;

public class ServicoFilme {
    mysql banco = new mysql();
    
    public void insereFilme(Filme filme){
        try{
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getUrl(), banco.getUsuario(), banco.getSenha());
            
            String query = "INSERT INTO filmes " +
            "(titulo, diretor, genero, idioma, duracao) " +
            "VALUES ('"+filme.getTitulo()+"', '"+filme.getDiretor()+"', '"+filme.getGenero()+"', '"+filme.getIdioma()+"', '"+filme.getDuracao()+"');";
            
            PreparedStatement ex = conn.prepareStatement(query);
            ex.execute();
            conn.close();
        }
        catch (Exception e) {
            //servico.gravaLog("Erro: Não foi possível adicionar o filme. Motivo: "+ e);
        }
    }
    
    public ArrayList<Filme> listaFilme(){
        ArrayList<Filme> listaFilme = new ArrayList<>();
        
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getUrl(), banco.getUsuario(), banco.getSenha());

            String query = "SELECT * FROM Filme ";

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
            //servico.gravaLog("Não foi possível buscar o filme. Motivo:" + e);
        }
        return listaFilme;
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
            //servico.gravaLog("Não foi possível buscar o filme. Motivo:" + e);
        }
        
        return filme;
    }
    
    public int buscaNomeFilme(String NomeFilme){
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getUrl(), banco.getUsuario(), banco.getSenha());

            String query = "SELECT * FROM Filme WHERE Titulo = '"+NomeFilme+"' ";

            PreparedStatement ex = conn.prepareStatement(query);
            ResultSet rs = ex.executeQuery(query);

            while (rs.next()) {
                return rs.getInt("IdFilme");
            }

            conn.close();
        } catch (Exception e) {
            System.err.println("Erro! " + e);
            //servico.gravaLog("Não foi possível buscar o filme. Motivo:" + e);
        }
        
        return 0;
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
            //servico.gravaLog("Filme id: "+filme.getIdFilme()+" |Titulo: "+filme.getTitulo()+" .Editado com sucesso");
            
            return true;
            
        }
        catch (Exception e) {
            //servico.gravaLog("Erro: Não foi possível editar o filme. Motivo: "+ e);
            return false;
        }
    }
    
    public void removeFilme(int idFilme){
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getUrl(), banco.getUsuario(), banco.getSenha());
            
            String query = "DELETE FROM Filme WHERE idFilme = '"+idFilme+"' ";
            PreparedStatement ex = conn.prepareStatement(query);
            ResultSet rs = ex.executeQuery(query);
            //servico.gravaLog(query);
            
            //Remover Sessão
            //query = "DELETE FROM Sessao WHERE idfilme = '"+idFilme+"' ";
            //ex.execute(query);
            
            conn.close();
            
        } catch (Exception e) {
            System.err.println("Erro! " + e);
        }
    }
}
