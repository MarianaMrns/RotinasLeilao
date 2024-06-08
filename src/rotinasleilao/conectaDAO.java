package rotinasleilao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conectaDAO {
    private Connection conn;
    
    public Connection getConexao() {
        return conn;
    }
    
     public void conectar() throws SQLException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/rotinasleilao", "root", "Mariana178@");
            System.out.println("Conexão realizada com sucesso!");
        }catch(ClassNotFoundException e) {
            System.out.println("Falha no carregamento da classe de conexão!");
        }catch (SQLException ex) {
            System.out.println("Falha ao conectar com o banco de dados.");
        }
    }
    
    public void desconectar() {
        try{
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Desconectado com sucesso!");
            }
        }catch(SQLException e) {
            System.out.println("Erro ao desconectar");
        } 
    }
}
