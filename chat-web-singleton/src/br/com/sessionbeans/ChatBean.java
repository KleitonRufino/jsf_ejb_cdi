package br.com.sessionbeans;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
// cria a instancia logo apos iniciar a aplicacao
@Startup
public class ChatBean {

	private Set<String> salas = new HashSet<String>();

	@Lock(LockType.WRITE)
	// Chamadas a metodos associados ao Read Lock podem ser executadas
	// simultaneamente. Por outro lado,
	// chamadas a metodos associados ao Write Lock sao executadas uma de cada
	// vez.
	// Write Lock eh associado aos metodos de negocio por padrao.
	public void criaSala(String sala) {
		this.salas.add(sala);
	}

	public List<String> listaSalas() {
		return new ArrayList<String>(this.salas);
	}

	@PostConstruct
	public void postConstruct() {
		System.out.println("Criando o ChatBean...");
	}

	@PreDestroy
	public void preDestroy() {
		System.out.println("Destruindo o ChatBean...");
	}
}
