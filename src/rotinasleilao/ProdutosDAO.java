package rotinasleilao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {
    
    public static boolean cadastrarProduto (ProdutosDTO produto) throws SQLException{
        
        try{
            conectaDAO conexao = new conectaDAO();
            conexao.conectar();

            String sql = "insert into produtos(nome, valor, status) values (?,?,?);";
            PreparedStatement consulta = conexao.getConexao().prepareStatement(sql);

            consulta.setString(1, produto.getNome());
            consulta.setInt(2, produto.getValor());
            consulta.setString(3, produto.getStatus());

            consulta.execute();

            conexao.desconectar();
            return true;
            
        }catch(SQLException sqle){
            System.out.println("Falha ao cadastrar registro no Banco de Dados.");
            return false;
        }
    }
    
    public static ArrayList<ProdutosDTO> listarProdutos(){
        
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        
        try{
            conectaDAO conexao = new conectaDAO();
            conexao.conectar();
            
            String sql = "SELECT * FROM produtos;";
            PreparedStatement consulta = conexao.getConexao().prepareStatement(sql);
            
            ResultSet resposta = consulta.executeQuery();
            
            while(resposta.next()){
                ProdutosDTO pdto = new ProdutosDTO();
                
                pdto.setId(resposta.getInt("id"));
                pdto.setNome(resposta.getString("nome"));
                pdto.setValor(resposta.getInt("valor"));
                pdto.setStatus(resposta.getString("status"));
                
                listagem.add(pdto);
            }
            
            conexao.desconectar();
            
        }catch (SQLException sqle){
            System.out.println("Falha ao listar os produtos cadastrados!");
        }
        
        return listagem;
    }
    
    public static boolean venderProduto(ProdutosDTO produto) throws SQLException{
        
        try{
            conectaDAO conexao = new conectaDAO();
            conexao.conectar();
            
            String sql = "UPDATE produtos SET status= 'Vendido' WHERE id=?";
            PreparedStatement consulta = conexao.getConexao().prepareStatement(sql);
            
            consulta.setInt(0, produto.getId());
            
            consulta.execute();
        
            conexao.desconectar();
            return true;
            
        } catch (SQLException sqle){
            System.out.println("Falha ao buscar o registro no banco de daddos!");
            return false;
        }
    }
    
    public static ArrayList<ProdutosDTO> listarProdutosVendidos() throws SQLException{
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        
        try{
            conectaDAO conexao = new conectaDAO();
            conexao.conectar();
            
            String sql = "SELECT * FROM produtos WHERE status= 'Vendido';";
            PreparedStatement consulta = conexao.getConexao().prepareStatement(sql);
            
            ResultSet resposta = consulta.executeQuery();
            
            while(resposta.next()){
                ProdutosDTO pdto = new ProdutosDTO();
                
                pdto.setId(resposta.getInt("id"));
                pdto.setNome(resposta.getString("nome"));
                pdto.setValor(resposta.getInt("valor"));
                pdto.setStatus(resposta.getString("status"));
                
                listagem.add(pdto);
            }
            
            conexao.desconectar();
            
        }catch (SQLException sqle){
            System.out.println("Falha ao listar os produtos vendidos!");
        }
        
        return listagem;
    }
}
