package br.com.cadaluno.service;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class RemoteData {
	// Varivavel do Tipo da prórpria classe para fazer Singleton
		static RemoteData instance;
		
		private CloseableHttpClient clientHTTP;// Variavel, responsavel por fazer a execução da busca da API
		private String BASEURL;
		private String responseBody;
		private ResponseHandler<String> responseHandler;
		private HttpEntity entity;
		private HttpGet httpGet;
		
		// Construtor privado, pois a classe em si não é instanciada, e sim o Singleton
		private RemoteData() {
			this.clientHTTP = HttpClients.createDefault(); // Inicialização do clientHTTP
		}
		
		// Função Publica que faz o Singleton, que é o conceito de instancia unica da classe
		public static RemoteData getInstance() {
			if (instance == null) {
				instance = new RemoteData(); 
			}
			return instance; // A Func retorna uma instancia da classe, que pode ser acessado por outras classes
		}
		
		// Função que faz a chamada GET da API
		public String doRequest(String path) {
			BASEURL = "http://viacep.com.br/ws/"+path+"/json/"; // URL com EndPoint "path" dinamico, que muda conforme passado no campo CEP pelo usuario
			responseBody = null; //variavel que vai conter o corpo da api, no formato Json
			
			try {

				httpGet = new HttpGet(BASEURL); // init do HTTPGET, passando a URL para que ele possa fazer o GET

				responseHandler = new ResponseHandler<String>() { // Expressão Lambda, que faz um override de uma func da Interface ResponseHanler

					@Override
					public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException { // response é a resposta do servidor onde a API fica, com os dados
						int status = response.getStatusLine().getStatusCode(); // Status serve para pegar o codigo de retorno da requisição  API REST
						if (status >= 200 && status < 300) { // Aqui testamos para verificar se o código está entre 200 e 300, que são códigos de sucesso de retorno da API
							entity = response.getEntity(); // caso der true, inicializamos o response, pegando a Entity
							
							return entity != null ? EntityUtils.toString(entity) : null; //Sendo a requisição bem sucedida ou tendo retornado algum erro, normalmente iremos querer obter o conteúdo retornado e para isso utilizaremos o objeto HttpEntity
						}else {
							throw new ClientProtocolException("Unexpected response status: " + status); // Aqui ele retornara o código acima de 299, caso de algum erro(URL ERRADA, API OFFLINE, ETC..)
						}
					}
				};
				responseBody = this.clientHTTP.execute(httpGet, responseHandler);// Através do clientHTTP.execute, executamos a busca usando o httpGet, que pega o retorno do servidor, e o usando o responseHandler nos parametros
			}catch(IOException e) {
				System.out.println("Erro: " +e); // Caso a Api esteja vindo null(Vazia) o erro de Exception irá ocorrer aqui
			}
			return responseBody; // Retorno final dos dados, em formato Json puro, sem conversão
		}
}
