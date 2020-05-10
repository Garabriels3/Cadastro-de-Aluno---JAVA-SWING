package br.com.cadaluno.DI;

import br.com.cadaluno.DAO.AlunoDAO;
import br.com.cadaluno.DAO.BoletimDAO;
import br.com.cadaluno.DAO.NotasFaltasDAO;
import br.com.cadaluno.controller.AlunoController;
import br.com.cadaluno.controller.BoletimController;
import br.com.cadaluno.controller.NotasFaltasController;

public class ServiceLocator<T> {
	
	static ServiceLocator instance;
	
	// instancias DAO
	private AlunoDAO alunoDAO = new AlunoDAO();
	private NotasFaltasDAO notasFaltasDAO = new NotasFaltasDAO();
	private BoletimDAO boletimDAO = new BoletimDAO();
	
	// instancias Controller
	private AlunoController alunoController = new AlunoController();
	private NotasFaltasController notasFaltasController = new NotasFaltasController();
	private BoletimController boletimController = new BoletimController();
	
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
		case "BoletimDAO": return boletimDAO;
		case "AlunoController": return alunoController;
		case "NotasFaltasController": return notasFaltasController;
		case "BoletimController": return boletimController;
		default: System.out.println("Classe não encontrada"); return null;	
		}
	}
	
}
