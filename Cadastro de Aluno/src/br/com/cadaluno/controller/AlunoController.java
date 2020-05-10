package br.com.cadaluno.controller;

import javax.swing.JOptionPane;

import br.com.cadaluno.DAO.AlunoDAO;
import br.com.cadaluno.model.AlunoModel;
import br.com.cadaluno.model.RemoteDataModel;

public class AlunoController {
	
	boolean sucess;
	AlunoModel data = null;
	AlunoModel aluno;
	AlunoDAO dao;
		
	public boolean cadAlunoBD(
	 String RGM,
	 String nome,
	 String data_nascimento,
	 String CPF,
	 String email,
	 String endereco,
	 String municipio,
	 String uf,
	 String celular,
	 String curso,
	 String campus,
	 String periodo ) throws Exception {
		
		if(RGM != null && nome != null && nome.length() > 0 && data_nascimento != null && data_nascimento.length() > 0
				&& CPF != null && CPF.length() > 0 && email != null && email.length() > 0 && endereco != null && endereco.length() > 0
				&& municipio != null && municipio.length() > 0 && uf != null && uf.length() > 0 && celular != null && celular.length() > 0
				&& curso != null && curso.length() > 0 && campus != null && campus.length() > 0 && periodo != null && periodo.length() > 0) {
			
			aluno = new AlunoModel(RGM, nome, data_nascimento, CPF, email, endereco, municipio, uf, celular, curso, campus, periodo);
			dao = new AlunoDAO();
			
			sucess = dao.cadastrarDados(aluno);
			JOptionPane.showMessageDialog(null, "CADASTRADO COM SUCESSO");
			return sucess;
		}else {
			JOptionPane.showMessageDialog(null, "ERRO AO CADASTRAR, VERIFIQUE OS CAMPOS");

			return false;
		}
	}
	
	public boolean alteraBD(
			 String RGM,
			 String nome,
			 String data_nascimento,
			 String CPF,
			 String email,
			 String endereco,
			 String municipio,
			 String uf,
			 String celular,
			 String curso,
			 String campus,
			 String periodo ) throws Exception {
				
				if(RGM != null && nome != null && nome.length() > 0 && data_nascimento != null && data_nascimento.length() > 0
						&& CPF != null && CPF.length() > 0 && email != null && email.length() > 0 && endereco != null && endereco.length() > 0
						&& municipio != null && municipio.length() > 0 && uf != null && uf.length() > 0 && celular != null && celular.length() > 0
						&& curso != null && curso.length() > 0 && campus != null && campus.length() > 0 && periodo != null && periodo.length() > 0) {
					
					aluno = new AlunoModel(RGM, nome, data_nascimento, CPF, email, endereco, municipio, uf, celular, curso, campus, periodo);
					dao = new AlunoDAO();
					
					sucess = dao.atualizarDados(aluno);
					JOptionPane.showMessageDialog(null, "ATUALIZADO COM SUCESSO");
					return sucess;
				}
				JOptionPane.showMessageDialog(null, "ERRO AO ATUALIZAR CADASTRO, VERIFIQUE OS CAMPOS");

				return false;
			}
	
	public AlunoModel consultaDados(String RGM) throws Exception {
		AlunoDAO dao = new AlunoDAO();
		if(RGM != null) {
			data = dao.consultaDados(RGM);
			return data;
		}else {
			JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR, VERIFIQUE O RGM INSERIDO");
			return null;
		}
	}
	
	public void deletarDados(String RGM) throws Exception {
		AlunoDAO dao = new AlunoDAO();
		if(RGM != null && RGM.length() == 8) {
			 dao.delete(RGM);
			 JOptionPane.showMessageDialog(null, "SUCESSO NA EXCLUSÃO");
		}else {
			JOptionPane.showMessageDialog(null, "ERRO AO EXCLUIR, VERIFIQUE O RGM INSERIDO");
		}
	}
	
	public RemoteDataModel getData(String path) {
		AlunoDAO dao = new AlunoDAO();
		RemoteDataModel data;
		if(!path.isEmpty() && path.length() == 9) {
			data = dao.convertApi(path);
			return data;
		}else {
			JOptionPane.showMessageDialog(null, "Erro ao inserir CEP, verifique o campo!");
			return null;
		}
	}
}
