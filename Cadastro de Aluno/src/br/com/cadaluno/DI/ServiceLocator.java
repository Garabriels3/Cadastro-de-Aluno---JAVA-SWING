package br.com.cadaluno.DI;

import br.com.cadaluno.DAO.AlunoDAO;
import br.com.cadaluno.DAO.NotasFaltasDAO;
import br.com.cadaluno.controller.AlunoController;
import br.com.cadaluno.controller.NotasFaltasController;

public class ServiceLocator<T> {
	
	static ServiceLocator instance;
	
	// instancias DAO
	private AlunoDAO alunoDAO = new AlunoDAO();
	private NotasFaltasDAO notasFaltasDAO = new NotasFaltasDAO();
	
	// instancias Controller
	private AlunoController alunoController = new AlunoController();
	private NotasFaltasController notasFaltasController = new NotasFaltasController();
	
	private ServiceLocator() {
		
	}
	
	public static ServiceLocator getInstance() {
		if(instance == null) {
			instance = new ServiceLocator();
		}
		return instance;
	}
	
	public Object uniqueInstance(String instance) {
		switch(instance) {
		case "AlunoDAO": return alunoDAO;
		case "NotasFaltasDAO": return notasFaltasDAO;
		case "AlunoController": return alunoController;
		case "NotasFaltasController": return notasFaltasController;
		default: System.out.println("Classe não encontrada"); return null;	
		}
	}
	
}
