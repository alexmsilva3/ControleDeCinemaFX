package servicos;

import classes.Sessao;
import java.sql.*;
import java.util.ArrayList;

public class ServicoSessao {
    mysql banco = new mysql();
    ServicoFilme servicoFilme = new ServicoFilme();
    ServicoSala servicoSala = new ServicoSala();
    ServicosGerais servico = new ServicosGerais();
    
    public boolean insereSessao(Sessao sessao){
        try{
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getUrl(), banco.getUsuario(), banco.getSenha());
            
            //filme, sala, data, hora, valorIngresso. ingressosDisponiveis
            String query = "INSERT INTO sessao " +
            "(FkidFilme, FkidSala, data, horario, valorIngresso, ingressosDisponiveis) " +
            "VALUES ('"+sessao.getFilme().getIdFilme()+"', '"+sessao.getSala().getIdSala()+"', '"+sessao.getData()+"', '"+sessao.getHorario()+"', '"+sessao.getValorIngresso()+"', '"+sessao.getIngressosDisponiveis()+"');";
            
            PreparedStatement ex = conn.prepareStatement(query);
            ex.execute();
            conn.close();
            return true;
        }
        catch (Exception e) {
            servico.gravaLog("Erro: Não foi possível adicionar a sessao. Motivo: "+ e);
            return false;
        }
    }
    
    public ArrayList<Sessao> listaSessao(){
        ArrayList<Sessao> listaSessao = new ArrayList<>();
        
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getUrl(), banco.getUsuario(), banco.getSenha());

            String query = "SELECT * FROM sessao ";

            PreparedStatement ex = conn.prepareStatement(query);
            ResultSet rs = ex.executeQuery(query);

            while (rs.next()) {
                Sessao sessao = new Sessao();
                
                sessao.setIdSessao(rs.getInt("idSessao"));
                sessao.setFilme(servicoFilme.buscaFilme(rs.getInt("FkidFilme")));
                sessao.setSala(servicoSala.buscaSala(rs.getInt("FkidSala")));
                sessao.setData(rs.getDate("data"));
                sessao.setHorario(rs.getTime("horario"));
                sessao.setValorIngresso(rs.getInt("valorIngresso"));
                sessao.setIngressosDisponiveis(rs.getInt("ingressosDisponiveis"));

                listaSessao.add(sessao);
            }

            conn.close();
        } catch (Exception e) {
            servico.gravaLog("Não foi possível buscar a sessao. Motivo:" + e);
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
                sessao.setIdSessao(rs.getInt("idSessao"));
                sessao.setFilme(servicoFilme.buscaFilme(rs.getInt("Fkidfilme")));
                sessao.setSala(servicoSala.buscaSala(rs.getInt("Fkidsala")));
                sessao.setData(rs.getDate("data"));
                sessao.setHorario(rs.getTime("horario"));
                sessao.setValorIngresso(rs.getInt("valorIngresso"));
                sessao.setIngressosDisponiveis(rs.getInt("ingressosDisponiveis"));
            }

            conn.close();
        } catch (Exception e) {
            servico.gravaLog("Não foi possível buscar a sessao. Motivo:" + e);
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
                    + "horario = '"+sessao.getHorario()+"',"
                    + "ingressosDisponiveis = '"+sessao.getIngressosDisponiveis()+"',"
                    + "valorIngresso = '"+sessao.getValorIngresso()+"' " +
            "WHERE idSessao = '"+sessao.getIdSessao()+"' ";
            
            PreparedStatement ex = conn.prepareStatement(query);
            ex.execute();
            
            conn.close();
            servico.gravaLog("Sessao id: "+sessao.getIdSessao()+" | Filme: "+sessao.getFilme().getTitulo()+" .Editado com sucesso");
            
            return true;
            
        }
        catch (Exception e) {
            servico.gravaLog("Erro: Não foi possível editar a sessao. Motivo: "+ e);
            return false;
        }
    }
    
    public boolean vendeIngresso(int idSessao, int qntd){
        try{
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getUrl(), banco.getUsuario(), banco.getSenha());
            
            String query = "UPDATE sessao " +
            "SET ingressosDisponiveis = ingressosDisponiveis - '"+qntd+"' " +
            "WHERE idSessao = '"+idSessao+"' ";
            
            PreparedStatement ex = conn.prepareStatement(query);
            ex.execute();
            
            conn.close();
            servico.gravaLog("Sessao id: "+idSessao+" |Quantidade: "+qntd+" .Ingresso vendido com sucesso");
            
            return true;
            
        }
        catch (Exception e) {
            servico.gravaLog("Erro: Não foi possível vender o ingresso. Motivo: "+ e);
            return false;
        }
    }
    
    public void removeSessao(int idSessao){
        try {
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getUrl(), banco.getUsuario(), banco.getSenha());
            
            String query = "DELETE FROM sessao WHERE idsessao = '"+idSessao+"' ";
            PreparedStatement ex = conn.prepareStatement(query);
            ex.execute();
            
            conn.close();
            
        } catch (Exception e) {
            servico.gravaLog("Erro: Não foi possível remover a sessão. Motivo: "+ e);
        }
    }
}
