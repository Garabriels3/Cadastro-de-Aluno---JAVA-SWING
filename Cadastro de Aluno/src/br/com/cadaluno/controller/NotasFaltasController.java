package br.com.cadaluno.controller;

import javax.swing.JOptionPane;

import br.com.cadaluno.DAO.NotasFaltasDAO;
import br.com.cadaluno.DI.ServiceLocator;
import br.com.cadaluno.model.NotasFaltasModel;

public class NotasFaltasController {
	
	boolean sucess;
	NotasFaltasModel data = null;
	NotasFaltasModel notas;
	ServiceLocator instance;
	NotasFaltasDAO dao;
	
	public boolean cadNotasBD(
			 String RGM,
			 String disciplina,
			 String semestre,
			 String nota,
			 String faltas ) throws Exception {
				instance = ServiceLocator.getInstance();
				dao = (NotasFaltasDAO) instance.uniqueInstance("NotasFaltasDAO");
				if(RGM != null && RGM.length() == 8 && disciplina != null && disciplina.length() > 0 && semestre != null && semestre.length() > 0
						&& nota != null && nota.length() > 0 && faltas != null && faltas.length() > 0 ) {
					
					notas = new NotasFaltasModel(RGM, disciplina, semestre, nota, faltas);
					JOptionPane.showMessageDialog(null, "NOTAS E FALTAS CADASTRADAS COM SUCESSO");
					sucess = dao.cadastrarDados(notas);
					return sucess;
				}else {
					JOptionPane.showMessageDialog(null, "ERRO AO CADASTRAR VERIFIQUE OS CAMPOS");
					return false;
				}
			}
	
	public boolean alteraBD(
			 String RGM,
			 String disciplina,
			 String semestre,
			 String nota,
			 String faltas ) throws Exception {
				instance = ServiceLocator.getInstance();
				dao = (NotasFaltasDAO) instance.uniqueInstance("NotasFaltasDAO");
				if(RGM != null && disciplina != null && disciplina.length() > 0 && semestre != null && semestre.length() > 0
						&& nota != null && nota.length() > 0 && faltas != null && nota.length() > 0) {
					
					notas = new NotasFaltasModel(RGM, disciplina, semestre, nota, faltas);
					
					sucess = dao.atualizarDados(notas);
					JOptionPane.showMessageDialog(null, "ATUALIZADO COM SUCESSO");
					return sucess;
				}else {
					JOptionPane.showMessageDialog(null, "ERRO AO ATUALIZAR CADASTRO, VERIFIQUE OS CAMPOS");
					return false;
				}
			}
	
	public NotasFaltasModel consultaDados(String RGM) throws Exception {
		instance = ServiceLocator.getInstance();
		dao = (NotasFaltasDAO) instance.uniqueInstance("NotasFaltasDAO");
		if(RGM != null) {
			data = dao.consultaDados(RGM);
			return data;
		}else {
			JOptionPane.showMessageDialog(null, "ERRO AO ATUALIZAR CADASTRO, VERIFIQUE OS CAMPOS");
			return null;
		}
	}
	
	public void deletarDados(String RGM) throws Exception {
		instance = ServiceLocator.getInstance();
		dao = (NotasFaltasDAO) instance.uniqueInstance("NotasFaltasDAO");
		if(RGM != null && RGM.length() == 8) {
			 dao.delete(RGM);
			 JOptionPane.showMessageDialog(null, "SUCESSO NA EXCLUSÃO");
		}else {
			JOptionPane.showMessageDialog(null, "ERRO AO EXCLUIR, VERIFIQUE O RGM INSERIDO");
		}
	}
}
