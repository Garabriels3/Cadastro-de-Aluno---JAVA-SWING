package br.com.cadaluno.DAO;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import br.com.cadaluno.model.AlunoModel;
import br.com.cadaluno.model.RemoteDataModel;
import br.com.cadaluno.service.RemoteData;
import br.com.cadaluno.util.ConnectionFactory;


public class AlunoDAO extends AbstractDAO<AlunoModel> {
	
	private Gson gson = new GsonBuilder().create(); // Gson é uma library, que converte Json para Objeto manipulavel, ou de Obejto para Json (fromJson, toJson)
    private RemoteDataModel data; // Variavel do tipo da Model, para manipular a api através de objetos Java
	private String json;
	private Type type;
	private RemoteData instance;
	
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	private boolean check;
	
	@Override
	public boolean cadastrarDados(AlunoModel aluno) {
		String sql = "INSERT INTO ALUNO (RGM, NOME, DATA_NASCIMENTO, CPF, EMAIL, ENDERECO, MUNICIPIO, UF, CELULAR, CURSO, CAMPUS, PERIODO) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
			stmt = con.prepareStatement(sql);
			stmt.setString(1, (aluno.getRGM()));
			stmt.setString(2, aluno.getNome());
			stmt.setString(3, aluno.getData_nascimento());
			stmt.setString(4, aluno.getCPF());
			stmt.setString(5, aluno.getEmail());
			stmt.setString(6, aluno.getEndereco());
			stmt.setString(7, aluno.getMunicipio());
			stmt.setString(8, aluno.getUf());
			stmt.setString(9, aluno.getCelular());
			stmt.setString(10, aluno.getCurso());
			stmt.setString(11, aluno.getCampos());
			stmt.setString(12, aluno.getPeriodo());
			stmt.executeUpdate();
			
		}catch (SQLException ex) {
			System.out.println(ex.getSQLState().toString());
		}
		return check;
	}
	
	@Override
	 public AlunoModel consultaDados(String RGM) throws Exception {
	        String sql = "SELECT * FROM aluno WHERE RGM = ?";
	        AlunoModel aluno = null;
	        
	    	con = ConnectionFactory.getConnection();
			stmt = null;
			rs = null;
			
	        try{
	        	stmt = con.prepareStatement(sql);
	            stmt.setString(1, RGM);
	            rs = stmt.executeQuery();
	            
	            while(rs.next()){
	                aluno = new AlunoModel(rs.getString("RGM"), rs.getString("NOME"), rs.getString("DATA_NASCIMENTO"), 
	                        rs.getString("CPF"), rs.getString("EMAIL"), rs.getString("ENDERECO"), rs.getString("MUNICIPIO"), rs.getString("UF"), rs.getString("CELULAR"),
	                        rs.getString("CURSO"), rs.getString("CAMPUS"), rs.getString("PERIODO"));
	            }
	            rs.close();
	            stmt.close();
	        }catch(SQLException e){
	            System.out.println("Error: " +e);
	        }
	        
	        return aluno;
	    }
	
	@Override
	public boolean delete(String id) throws Exception {
		// TODO Auto-generated method stub
		 String sql = "DELETE FROM aluno WHERE RGM = ?";
		 con = ConnectionFactory.getConnection();
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
	        }catch(SQLException e){
	            System.out.println("Error: DAO " +e);
	        }
	        return check;
	}
	
	@Override
	public boolean atualizarDados(AlunoModel aluno) {
		String sql = "UPDATE ALUNO SET NOME = ?, DATA_NASCIMENTO = ?, CPF = ?, EMAIL = ?, ENDERECO = ?, MUNICIPIO = ?, UF = ?, CELULAR = ?, CURSO = ?, CAMPUS = ?, PERIODO = ? WHERE RGM = ?";
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
			stmt = con.prepareStatement(sql);
			stmt.setString(1, aluno.getNome());
			stmt.setString(2, aluno.getData_nascimento());
			stmt.setString(3, aluno.getCPF());
			stmt.setString(4, aluno.getEmail());
			stmt.setString(5, aluno.getEndereco());
			stmt.setString(6, aluno.getMunicipio());
			stmt.setString(7, aluno.getUf());
			stmt.setString(8, aluno.getCelular());
			stmt.setString(9, aluno.getCurso());
			stmt.setString(10, aluno.getCampos());
			stmt.setString(11, aluno.getPeriodo());
			stmt.setString(12, aluno.getRGM());
			stmt.executeUpdate();
			
		}catch (SQLException ex) {
			System.out.println(ex);
		}
		return check;
	}
	
	
	
	public RemoteDataModel convertApi(String path) {
		instance = RemoteData.getInstance(); // Usamos a instancia do Singleton, para instanciar a classe, de forma Unica
		json = instance.doRequest(path); // Aqui atribuimos a variavel json, o retorno de doRequest, que retornará o JSON puro sem conversão
		type = new TypeToken<RemoteDataModel>() {}.getType(); // Aqui fazemos um TypeToken, do tipo Generico CepModel
		
			data = gson.fromJson(json, type); // Inicializamos a var data com os dados vindos da API de forma Convertida, para Objeto, para que possamos acessar
		
		return data; // retorna os dados da API, agora já convertidos objetos manipulaveis dentro da linguagem Java
	}

	

}
