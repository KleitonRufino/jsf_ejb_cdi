package br.com.sessionbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Singleton;

@Singleton
public class TarefasBean {

	private List<String> tarefas = new ArrayList<String>();

	@RolesAllowed({ "ADMIN", "USERS" })
	public void adiciona(String tarefa) {
		this.tarefas.add(tarefa);
	}

	@RolesAllowed({ "ADMIN", "USERS" })
	public List<String> listaTerefas() {
		return this.tarefas;
	}

	@RolesAllowed({ "ADMIN" })
	public void remove(String tarefa) {
		this.tarefas.remove(tarefa);
	}
}
