package servicos;

import java.sql.*;
//Início da classe de conexão//
 
public class ConexaoMySQL {
    
    public static String status = "Não conectou...";
    
    //Método Construtor da Classe
    public ConexaoMySQL() {
    
    }
 
    //Método de Conexão//
 
    public static java.sql.Connection getConexaoMySQL() {
        Connection connection = null;
        
        try {
            // Carregando o JDBC Driver padrão
            String driverName = "com.mysql.cj.jdbc.Driver";
            Class.forName(driverName);

            // Configurando a nossa conexão com um banco de dados//
            String url = "jdbc:mysql://mysql22.redehost.com.br:41890/cinema?useSSL=false&useTimezone=true&serverTimezone=America/Sao_Paulo";
            //String url = "jdbc:mysql://localhost:3306/cinema?useSSL=false&useTimezone=true&serverTimezone=America/Sao_Paulo";
            String username = "cinema";        //nome de um usuário de seu BD      
            String password = "PB(1&@NaW8";      //sua senha de acesso
            connection = DriverManager.getConnection(url, username, password);
            //Testa sua conexão//  

            if (connection != null) {
                status = ("STATUS--->Conectado com sucesso!");
            } else {
                status = ("STATUS--->Não foi possivel realizar conexão");
            }
            return connection;
        } catch (ClassNotFoundException e) {  //Driver não encontrado
                System.out.println("O driver expecificado nao foi encontrado.");
                return null;
        } catch (SQLException e){
            //Não conseguindo se conectar ao banco
            System.out.println("Nao foi possivel conectar ao Banco de Dados."+ e);
            return null;
        }
    }
 
    //Método que retorna o status da sua conexão//
    public static String statusConection() {
        return status;
    }
   
 
    //Método que fecha sua conexão//
     public static boolean FecharConexao() {
         try {
             ConexaoMySQL.getConexaoMySQL().close();
             return true;
         } catch (SQLException e) {
             return false;
         }
     }
    
    //Método que reinicia sua conexão
    public static java.sql.Connection ReiniciarConexao() {
        FecharConexao();
        return ConexaoMySQL.getConexaoMySQL();
        }
}