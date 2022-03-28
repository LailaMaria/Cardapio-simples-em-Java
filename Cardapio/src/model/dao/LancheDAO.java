package model.dao;

import connection.ConnectionFactory;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Lanche1;


public class LancheDAO {
    
    public void create(Lanche1 p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
         
        try {
            stmt = con.prepareStatement("INSERT INTO lanche (nome, valor)VALUES(?,?)");
            stmt.setString(1,p.getNome());
            stmt.setFloat(2,p.getValor());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Item cadastrado!");


            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao salvar" + ex);

        }finally{

            ConnectionFactory.closeConnection(con, stmt);
    }
    
    }
    
    public List<Lanche1> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Lanche1> a = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM lanche");
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Lanche1 atrib = new Lanche1();
                atrib.setId(rs.getInt("id"));
                atrib.setNome(rs.getString("nome"));
                atrib.setValor(rs.getFloat("valor"));
                a.add(atrib); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CafeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return a;
    }
            
    public void update(Lanche1 p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
         
        try {
            stmt = con.prepareStatement("UPDATE lanche SET nome = ?, valor = ? WHERE nome=?");
            stmt.setString(1, p.getNome());
            stmt.setFloat(2, p.getValor());
            stmt.setString(3, p.getNome());
            //stmt.setInt(3, p.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Item atualizado!");


            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao atualizar" + ex);

        }finally{

            ConnectionFactory.closeConnection(con, stmt);
    }
    
    }
    
    public void delete(Lanche1 p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
         
        try {
            stmt = con.prepareStatement("DELETE FROM lanche WHERE nome=?");
            
            stmt.setString(1, p.getNome());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Item excluído!");


            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao excluir" + ex);

        }finally{

            ConnectionFactory.closeConnection(con, stmt);
    }
    
    }
    
    public List<Lanche1> readForDesc(String desc){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Lanche1> a = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM lanche WHERE nome LIKE ?");
            stmt.setString(1, desc+"%");
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Lanche1 atrib = new Lanche1();
                atrib.setId(rs.getInt("id"));
                atrib.setNome(rs.getString("nome"));
                atrib.setValor(rs.getFloat("valor"));
                a.add(atrib); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CafeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return a;
    }
}

