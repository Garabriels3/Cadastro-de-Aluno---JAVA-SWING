package br.com.cadaluno.model;

public class AlunoModel {
	private String RGM;
	private String nome;
	private String data_nascimento;
	private String CPF;
	private String email;
	private String endereco;
	private String municipio;
	private String uf;
	private String celular;
	private String curso;
	private String campos;
	private String periodo;
	
	public AlunoModel(String rGM, String nome, String data_nascimento, String cPF, String email, String endereco,
			String municipio, String uf, String celular, String curso, String campos, String periodo) {
		this.RGM = rGM;
		this.nome = nome;
		this.data_nascimento = data_nascimento;
		this.CPF = cPF;
		this.email = email;
		this.endereco = endereco;
		this.municipio = municipio;
		this.uf = uf;
		this.celular = celular;
		this.curso = curso;
		this.campos = campos;
		this.periodo = periodo;
	}
	
	public AlunoModel() {
		// TODO Auto-generated constructor stub
	}

	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getCampos() {
		return campos;
	}
	public void setCampos(String campos) {
		this.campos = campos;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getRGM() {
		return RGM;
	}
	public void setRGM(String rGM) {
		RGM = rGM;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getData_nascimento() {
		return data_nascimento;
	}
	public void setData_nascimento(String data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
}
