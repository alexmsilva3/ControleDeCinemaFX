package servicos;

import classes.Sessao;
import java.sql.*;
import java.util.ArrayList;

public class ServicoSessao {
    mysql banco = new mysql();
    ServicoFilme servicoFilme = new ServicoFilme();
    ServicoSala servicoSala = new ServicoSala();
    
    public void insereSessao(Sessao sessao){
        try{
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getUrl(), banco.getUsuario(), banco.getSenha());
            
            //filme, sala, data, hora, valorIngresso. ingressosDisponiveis
            String query = "INSERT INTO Sessao " +
            "(filme, sala, data, horario, valorIngresso, ingressosDisponiveis) " +
            "VALUES ('"+sessao.getFilme().getIdFilme()+"', '"+sessao.getSala().getIdSala()+"', '"+sessao.getData()+"', '"+sessao.getHorario()+"', '"+sessao.getValorIngresso()+"', '"+sessao.getIngressosDisponiveis()+"');";
            
            PreparedStatement ex = conn.prepareStatement(query);
            ex.execute();
            conn.close();
        }
        catch (Exception e) {
            //servico.gravaLog("Erro: Não foi possível adicionar a sessao. Motivo: "+ e);
        }
    }
    
    public ArrayList<Sessao> listaSessao(){
        ArrayList<Sessao> listaSessao = new ArrayList<>();
        
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getUrl(), banco.getUsuario(), banco.getSenha());

            String query = "SELECT * FROM Sessao ";

            PreparedStatement ex = conn.prepareStatement(query);
            ResultSet rs = ex.executeQuery(query);

            while (rs.next()) {
                Sessao sessao = new Sessao();
                
                sessao.setFilme(servicoFilme.buscaFilme(rs.getInt("Fkidfilme")));
                sessao.setSala(servicoSala.buscaSala(rs.getInt("Fkidsala")));
                sessao.setData(rs.getDate("data"));
                sessao.setHorario(rs.getTime("horario"));
                sessao.setValorIngresso(rs.getInt("valorIngresso"));
                sessao.setIngressosDisponiveis(rs.getInt("ingressosDisponiveis"));

                listaSessao.add(sessao);
            }

            conn.close();
        } catch (Exception e) {
            //servico.gravaLog("Não foi possível buscar a sessao. Motivo:" + e);
        }
        return listaSessao;
    }
    
    public Sessao buscaSessao(int idSessao){
        Sessao sessao = new Sessao();
        
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getUrl(), banco.getUsuario(), banco.getSenha());

            String query = "SELECT * FROM sessao WHERE idsessao = '"+idSessao+"' ";

            PreparedStatement ex = conn.prepareStatement(query);
            ResultSet rs = ex.executeQuery(query);

            while (rs.next()) {
                sessao.setFilme(servicoFilme.buscaFilme(rs.getInt("Fkidfilme")));
                sessao.setSala(servicoSala.buscaSala(rs.getInt("Fkidsala")));
                sessao.setData(rs.getDate("data"));
                sessao.setHorario(rs.getTime("horario"));
                sessao.setValorIngresso(rs.getInt("valorIngresso"));
                sessao.setIngressosDisponiveis(rs.getInt("ingressosDisponiveis"));
            }

            conn.close();
        } catch (Exception e) {
            System.err.println("Erro! " + e);
            //servico.gravaLog("Não foi possível buscar a sessao. Motivo:" + e);
        }
        
        return sessao;
    }
    
    public boolean editaSessao(Sessao sessao){
        try{
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getUrl(), banco.getUsuario(), banco.getSenha());
            
            String query = "UPDATE sessao " +
            "SET FkidFilme = '"+sessao.getFilme().getIdFilme()+"',"
                    + "FkidSala = '"+sessao.getSala().getIdSala()+"',"
                    + "data = '"+sessao.getData()+"',"
                    + "Horario = '"+sessao.getHorario()+"',"
                    + "ingressosDisponiveis = '"+sessao.getIngressosDisponiveis()+"',"
                    + "valorIngresso = '"+sessao.getValorIngresso()+"' " +
            "WHERE idfilme = '"+sessao.getIdSessao()+"' ";
            
            PreparedStatement ex = conn.prepareStatement(query);
            ex.execute();
            
            conn.close();
            //servico.gravaLog("Sessao id: "+sessao.getIdSessao()+" |Filme: "+sessao.getFilme().getTitulo()+" .Editado com sucesso");
            
            return true;
            
        }
        catch (Exception e) {
            //servico.gravaLog("Erro: Não foi possível editar a sessao. Motivo: "+ e);
            return false;
        }
    }
    
    public void removeSessao(int idSessao){
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getUrl(), banco.getUsuario(), banco.getSenha());
            
            String query = "DELETE FROM Sessao WHERE idsessao = '"+idSessao+"' ";
            PreparedStatement ex = conn.prepareStatement(query);
            ResultSet rs = ex.executeQuery(query);
            //servico.gravaLog(query);
            
            conn.close();
            
        } catch (Exception e) {
            System.err.println("Erro! " + e);
        }
    }
}
