package br.com.cadaluno.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.cadaluno.DAO.AlunoDAO;
import br.com.cadaluno.DAO.BoletimDAO;
import br.com.cadaluno.DI.ServiceLocator;
import br.com.cadaluno.model.AlunoModel;
import br.com.cadaluno.model.NotasFaltasModel;

public class BoletimController {
	List<AlunoModel> alunos = new ArrayList();
	ServiceLocator instance;
	BoletimDAO dao = null;


	
	public List<AlunoModel> listarAlunoController() throws Exception {
			instance = ServiceLocator.getInstance();
			dao = (BoletimDAO) instance.uniqueInstance("BoletimDAO");
			alunos = dao.listarAlunoDAO();
			
			if(alunos != null) {
				return alunos;
			} else {
				JOptionPane.showMessageDialog(null, "LISTA DE ALUNOS VAZIA");
				return null;
			}
	}
	
	public List<AlunoModel> buscarAlunoController(String RGM) throws Exception {
		instance = ServiceLocator.getInstance();
		dao = (BoletimDAO) instance.uniqueInstance("BoletimDAO");
		alunos = dao.buscarAlunoDAO(RGM);
		
		if(alunos != null) {
			return alunos;
		} else {
			JOptionPane.showMessageDialog(null, "RGM NÃO EXISTENTE");
			return null;
		}
}
	public List<NotasFaltasModel> detalhesAlunoController(String RGM) throws Exception {
		instance = ServiceLocator.getInstance();
		dao = (BoletimDAO) instance.uniqueInstance("BoletimDAO");
		List<NotasFaltasModel> boletinsAluno = null;
		boletinsAluno = dao.detalheAlunoDAO(RGM);
		
		if(boletinsAluno != null) {
			return boletinsAluno;
		} else {
			JOptionPane.showMessageDialog(null, "ALUNO SEM NOTAS LANÇADAS");
			return null;
		}
}
}
