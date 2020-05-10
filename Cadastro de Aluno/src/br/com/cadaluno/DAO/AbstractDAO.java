package br.com.cadaluno.DAO;
public abstract class AbstractDAO<T> {
	
	abstract boolean cadastrarDados(T var);
	abstract boolean delete(java.lang.String id) throws Exception;
	abstract boolean atualizarDados(T aluno);
	abstract T consultaDados(String RGM) throws Exception;
}
