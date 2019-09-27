package servicos;

import java.sql.*;

public class mysql {
    private String url = "jdbc:mysql://mysql22.redehost.com.br:41890/cinema";//?useSSL=false&useTimezone=true&serverTimezone=America/Sao_Paulo";
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String usuario = "cinema";
    private String senha = "PB(1&@NaW8";
    private Connection conexao;
    
    public void conectar() throws ClassNotFoundException, SQLException{
        if (conexao == null) {
            Class.forName(getDriver());
            conexao = DriverManager.getConnection(getUrl(), getUsuario(), getSenha());
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
