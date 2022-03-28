package model.dao;

import connection.ConnectionFactory;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Pedido1;


public class TCafeDAO {
    
    public void create(Pedido1 p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
         
        try {
            stmt = con.prepareStatement("INSERT INTO tcafe (nome, valor, qtd, total)VALUES(?,?,?,?)");
            stmt.setString(1,p.getNome());
            stmt.setFloat(2,p.getValor());
            stmt.setInt(3,p.getQtd());
            stmt.setFloat(4,p.getTotal());
            
            stmt.executeUpdate();
            
            //JOptionPane.showMessageDialog(null,"Salvo com sucesso!");


            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao salvar" + ex);

        }finally{

            ConnectionFactory.closeConnection(con, stmt);
    }
    
    }
    
    public void update(Pedido1 p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
         
        try {
            stmt = con.prepareStatement("UPDATE tcafe SET qtd = qtd + ?, total = total + ? WHERE nome=?");            
            stmt.setInt(1, p.getQtd());
            stmt.setFloat(2, p.getTotal());
            stmt.setString(3, p.getNome());
            //stmt.setFloat(4, p.getValor());
            //stmt.setInt(5, p.getId());
            
            stmt.executeUpdate();
            
            //JOptionPane.showMessageDialog(null,"Atualizado com sucesso!");


            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao atualizar" + ex);

        }finally{

            ConnectionFactory.closeConnection(con, stmt);
    }
    
    }
    
    public void update2(Pedido1 p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
         
        try {
            stmt = con.prepareStatement("UPDATE tcafe SET nome = ?, valor = ? WHERE nome=?");
            stmt.setString(1, p.getNome());
            stmt.setFloat(2, p.getValor());
            stmt.setString(3, p.getNome());
            //stmt.setInt(3, p.getId());
            
            stmt.executeUpdate();
            
            //JOptionPane.showMessageDialog(null,"Item atualizado!");


            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao atualizar" + ex);

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
            stmt = con.prepareStatement("SELECT * FROM tcafe ORDER BY qtd DESC");
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Pedido1 atrib = new Pedido1();
                atrib.setId(rs.getInt("id"));
                atrib.setNome(rs.getString("nome"));
                atrib.setValor(rs.getFloat("valor"));
                atrib.setQtd(rs.getInt("qtd"));
                atrib.setTotal(rs.getFloat("total"));
                a.add(atrib); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return a;
    }
            
    
    
    
    public List<Pedido1> readForDesc(String desc){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Pedido1> a = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM tcafe WHERE nome LIKE ?");
            stmt.setString(1, "%"+desc+"%");
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Pedido1 atrib = new Pedido1();
                atrib.setId(rs.getInt("id"));
                atrib.setNome(rs.getString("nome"));
                atrib.setValor(rs.getFloat("valor"));
                atrib.setQtd(rs.getInt("total"));
                a.add(atrib); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return a;
    }
        
}

