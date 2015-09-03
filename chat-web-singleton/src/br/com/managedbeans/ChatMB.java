package br.com.managedbeans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import br.com.sessionbeans.ChatBean;

@ManagedBean
public class ChatMB {

	@EJB
	private ChatBean chatBean;
	private String sala;

	public void adicionaSala() {
		this.chatBean.criaSala(sala);
	}

	public List<String> getSalas() {
		return this.chatBean.listaSalas();
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public String getSala() {
		return sala;
	}

}
