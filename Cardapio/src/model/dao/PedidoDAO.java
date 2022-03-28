package model.dao;

import connection.ConnectionFactory;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Pedido1;


public class PedidoDAO {
    
    public void create(Pedido1 p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
         
        try {
            stmt = con.prepareStatement("INSERT INTO pedido (nome, valor, qtd)VALUES(?,?,?)");
            stmt.setString(1,p.getNome());
            stmt.setFloat(2,p.getValor());
            stmt.setInt(3,p.getQtd());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Item adicionado!");


            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao salvar" + ex);

        }finally{

            ConnectionFactory.closeConnection(con, stmt);
    }
    
    }
    
    public List<Pedido1> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Pedido1> a = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM pedido");
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Pedido1 atrib = new Pedido1();
                atrib.setId(rs.getInt("id"));
                atrib.setNome(rs.getString("nome"));
                atrib.setValor(rs.getFloat("valor"));
                atrib.setQtd(rs.getInt("qtd"));
                a.add(atrib); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return a;
    }
            
    public void update(Pedido1 p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
         
        try {
            stmt = con.prepareStatement("UPDATE pedido SET nome = ?, valor = ?, qtd = ? WHERE id=?");
            stmt.setString(1, p.getNome());
            stmt.setFloat(2, p.getValor());
            stmt.setInt(3, p.getQtd());
            stmt.setInt(4, p.getId());
            
            
            stmt.executeUpdate();
            
            //JOptionPane.showMessageDialog(null,"Atualizado com sucesso!");


            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao atualizar" + ex);

        }finally{

            ConnectionFactory.closeConnection(con, stmt);
    }
    
    }
    
    public void delete(Pedido1 p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
         
        try {
            stmt = con.prepareStatement("DELETE FROM pedido WHERE id=?");
            
            stmt.setInt(1, p.getId());
            
            stmt.executeUpdate();
            
            //JOptionPane.showMessageDialog(null,"Excluído com sucesso!");


            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao excluir" + ex);

        }finally{

            ConnectionFactory.closeConnection(con, stmt);
    }
    
    }
    
    public List<Pedido1> readForDesc(String desc){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Pedido1> a = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM pedido WHERE nome LIKE ?");
            stmt.setString(1, "%"+desc+"%");
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Pedido1 atrib = new Pedido1();
                atrib.setId(rs.getInt("id"));
                atrib.setNome(rs.getString("nome"));
                atrib.setValor(rs.getFloat("valor"));
                atrib.setQtd(rs.getInt("qtd"));
                a.add(atrib); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return a;
    }
    
    public void del(Pedido1 p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
         
        try {
            stmt = con.prepareStatement("DELETE FROM pedido");
            
            //stmt.setInt(1, p.getId());
            
            stmt.executeUpdate();
            
            //JOptionPane.showMessageDialog(null,"Excluído com sucesso!");


            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao excluir" + ex);

        }finally{

            ConnectionFactory.closeConnection(con, stmt);
    }
    
    }
}

