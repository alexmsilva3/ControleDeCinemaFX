//
package servicos;

import java.sql.*;

/**
 *
 * @author alex.silva
 */
public class ServicoFilme {
    mysql banco = new mysql();
    
    public void insereFilme(int id){
        try{
            Class.forName(banco.getDriver());
            Connection conn = DriverManager.getConnection(banco.getUrl(), banco.getUsuario(), banco.getSenha());
            
            String query = "INSERT INTO filmes ";// +
            //"(tipo,logradouro,numero,bairro,complemento,cep,cidade,uf) " +
            //"VALUES ('"+filme.getId().getTipo()+"','"+paciente.getEndereco().getLogradouro()+"','"+paciente.getEndereco().getNumero()+"','"+paciente.getEndereco().getBairro()+"','"+paciente.getEndereco().getComplemento()+"','"+paciente.getEndereco().getCep()+"','"+paciente.getEndereco().getCidade()+"','"+paciente.getEndereco().getUf()+"');";
            
            PreparedStatement ex = conn.prepareStatement(query);
            ex.execute();
            
            conn.close();
            //
        }
        catch (Exception e) {
        }
    }
}
