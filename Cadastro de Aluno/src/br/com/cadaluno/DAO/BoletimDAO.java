package br.com.cadaluno.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.cadaluno.model.AlunoModel;
import br.com.cadaluno.model.NotasFaltasModel;
import br.com.cadaluno.util.ConnectionFactory;

public class BoletimDAO {
	
	public List<AlunoModel> listarAlunoDAO() throws Exception {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet res = null;
		
		List<AlunoModel> alunos = new ArrayList();
		
		try {
			stmt = con.prepareStatement("SELECT ALUNO.RGM, ALUNO.NOME, ALUNO.CURSO FROM ALUNO");
			res = stmt.executeQuery();
			
			while(res.next()) {
				AlunoModel aluno = new AlunoModel();
				
				aluno.setRGM(res.getString("RGM"));
				aluno.setNome(res.getString("NOME"));
				aluno.setCurso(res.getString("CURSO"));
				
				alunos.add(aluno);
			}
		} catch (SQLException ex) {
			Logger.getLogger(BoletimDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return alunos;
	}
	
	public List<AlunoModel> buscarAlunoDAO(String RGM) throws Exception {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet res = null;
		
		List<AlunoModel> alunos = new ArrayList();
		
		try {
			stmt = con.prepareStatement("SELECT ALUNO.RGM, ALUNO.NOME, ALUNO.CURSO FROM ALUNO WHERE RGM LIKE ?");
			stmt.setString(1, "%"+RGM+"%");
			res = stmt.executeQuery();
			
			while(res.next()) {
				AlunoModel aluno = new AlunoModel();
				
				aluno.setRGM(res.getString("RGM"));
				aluno.setNome(res.getString("NOME"));
				aluno.setCurso(res.getString("CURSO"));
				
				alunos.add(aluno);
			}
		} catch (SQLException ex) {
			Logger.getLogger(BoletimDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return alunos;
	}
	
	public List<NotasFaltasModel> detalheAlunoDAO(String RGM) throws Exception {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet res = null;
		
		List<NotasFaltasModel> boletins = new ArrayList();
		
		try {
			stmt = con.prepareStatement("SELECT ALUNO.NOME, ALUNO.CURSO, NOTAS_FALTAS.DISCIPLINA, NOTAS_FALTAS.NOTA, NOTAS_FALTAS.FALTAS FROM ALUNO INNER JOIN NOTAS_FALTAS ON ALUNO.RGM = NOTAS_FALTAS.RGM WHERE ALUNO.RGM = ?");
			stmt.setString(1, RGM);
			res = stmt.executeQuery();
			
			while(res.next()) {
				NotasFaltasModel aluno = new NotasFaltasModel();
				
				aluno.setNome(res.getString("NOME"));
				aluno.setCurso(res.getString("CURSO"));
				aluno.setDisciplina(res.getString("DISCIPLINA"));
				aluno.setNota(res.getString("NOTA"));
				aluno.setFaltas(res.getString("FALTAS"));
				
				boletins.add(aluno);
			}
		} catch (SQLException ex) {
			Logger.getLogger(BoletimDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return boletins;
	}
	
}
