package br.com.cadaluno.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.cadaluno.model.NotasFaltasModel;
import br.com.cadaluno.util.ConnectionFactory;

public class NotasFaltasDAO extends AbstractDAO<NotasFaltasModel> {
	Connection con;
	PreparedStatement stmt;
	ResultSet rs;
	boolean check;
	
	@Override
	public boolean cadastrarDados(NotasFaltasModel var) {
		
		String sql = "INSERT INTO NOTAS_FALTAS (RGM, DISCIPLINA , SEMESTRE, NOTA, FALTAS) VALUES(?, ?, ?, ?, ?)";
		// TODO Auto-generated method stub
		try {
			con = ConnectionFactory.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("ERRO AO TENTAR CONECTAR AO BD(CADASTRAR)");
			e.printStackTrace();
		}
		stmt = null;
		rs = null;
		check = false;
				
		try {
				check = true;
				stmt = con.prepareStatement(sql);
				stmt.setString(1, var.getRGM());
				stmt.setString(2, var.getDisciplina());
				stmt.setString(3, var.getSemestre());
				stmt.setString(4, var.getNota());
				stmt.setString(5, var.getFaltas());
			
				stmt.executeUpdate();
			
		}catch (SQLException ex) {
			System.out.println(ex);
		}
		return check;
	}

	@Override
	public boolean delete(String id) throws Exception {
		// TODO Auto-generated method stub
		String sql = "DELETE notas_faltas.nota, notas_faltas.faltas FROM notas_faltas WHERE RGM = ?";
		
		 try {
			con = ConnectionFactory.getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println("ERRO AO TENTAR CONECTAR AO BD(DELETE)");
			e1.printStackTrace();
		}
		 
		 stmt = null;
		 rs = null;
		 check = false;

	        try {
	            stmt = con.prepareStatement(sql);
	           if(!id.isEmpty()) {
	        	   stmt.setString(1, id);
	        	   stmt.execute();
	        	   check = true;
	           }
	            stmt.close();
	            System.out.println("Dados deletados com sucesso!(DAO)");
	        }catch(SQLException e){
	            System.out.println("Error: DAO " + e);
	        }
	        return check;
	}

	@Override
	public boolean atualizarDados(NotasFaltasModel aluno) {
		// TODO Auto-generated method stub
		try {
			con = ConnectionFactory.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stmt = null;
		rs = null;
		check = false;
				
		try {
			check = true;
			stmt = con.prepareStatement("UPDATE notas_faltas SET DISCIPLINA = ?, SEMESTRE = ?, NOTA = ?, FALTAS = ? WHERE RGM = ?");
			stmt.setString(1, aluno.getDisciplina());
			stmt.setString(2, aluno.getSemestre());
			stmt.setString(3, aluno.getNota());
			stmt.setString(4, aluno.getFaltas());
			stmt.setString(5, aluno.getRGM());
		
			stmt.executeUpdate();
			
		}catch (SQLException ex) {
			System.out.println(ex);
		}
		return check;
	}

	@Override
	public NotasFaltasModel consultaDados(String RGM) throws Exception {
		// TODO Auto-generated method stub
		String sql = "SELECT nome, curso, disciplina, NOTAS_FALTAS.semestre, NOTAS_FALTAS.nota, NOTAS_FALTAS.faltas FROM ALUNO INNER JOIN NOTAS_FALTAS ON ALUNO.RGM = NOTAS_FALTAS.RGM WHERE ALUNO.RGM = ?";
        NotasFaltasModel notas = null;
        
    	try {
			con = ConnectionFactory.getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		stmt = null;
		rs = null;
		
        try{
        	stmt = con.prepareStatement(sql);
            stmt.setString(1, RGM);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                notas = new NotasFaltasModel(rs.getString("NOME"), rs.getString("CURSO"), rs.getString("DISCIPLINA"), rs.getString("SEMESTRE"), rs.getString("NOTA"), 
                        rs.getString("FALTAS"));
            }
            rs.close();
            stmt.close();
        }catch(SQLException e){
            System.out.println("Error: " +e);
        }
        return notas;
	}
	
}
