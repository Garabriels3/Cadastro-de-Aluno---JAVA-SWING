package br.com.cadaluno.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	public static Connection getConnection() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String login = "root";
			String senha = "71463771";
			String url = "jdbc:mysql://localhost:3306/aluno";
			return DriverManager.getConnection(url, login, senha);
		}
		catch(Exception e) {
			System.out.println("Erro Ao conectar");
			e.printStackTrace();
		}
		return null;
	}

}
