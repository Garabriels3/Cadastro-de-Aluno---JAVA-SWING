package br.com.cadaluno.model;

public class NotasFaltasModel {
	
	private String RGM;
	private String nome;
	private String curso;
	private String disciplina;
	private String semestre;
	private String nota;
	private String faltas;
	
	public NotasFaltasModel(String rGM, String nome, String curso, String disciplina, String semestre, String nota, String faltas) {
		this.RGM = rGM;
		this.nome = nome;
		this.curso = curso;
		this.disciplina = disciplina;
		this.semestre = semestre;
		this.nota = nota;
		this.faltas = faltas;
	}
	
	public NotasFaltasModel(String rGM, String disciplina, String semestre, String nota, String faltas) {
		this.RGM = rGM;
		this.disciplina = disciplina;
		this.semestre = semestre;
		this.nota = nota;
		this.faltas = faltas;
	}
	
	public NotasFaltasModel(String nome, String curso, String disciplina, String semestre, String nota, String faltas) {
		this.nome = nome;
		this.curso = curso;
		this.disciplina = disciplina;
		this.semestre = semestre;
		this.nota = nota;
		this.faltas = faltas;
	}
	
	public NotasFaltasModel() {
		// TODO Auto-generated constructor stub
	}

	public String getRGM() {
		return RGM;
	}
	public void setRGM(String rGM) {
		RGM = rGM;
	}
	public String getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
	public String getSemestre() {
		return semestre;
	}
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public String getFaltas() {
		return faltas;
	}
	public void setFaltas(String faltas) {
		this.faltas = faltas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
}
