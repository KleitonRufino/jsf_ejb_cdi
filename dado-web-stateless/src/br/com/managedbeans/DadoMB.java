package br.com.managedbeans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import br.com.sessionbeans.LancadorDeDadoBean;

@ManagedBean
public class DadoMB {
	@EJB
	// no caso de acesso remoto
	// private LancadorDeDado lancadorDeDadoBean;
	private LancadorDeDadoBean lancadorDeDadoBean;
	private int resultado;

	public void lancaDado() {
		this.resultado = this.lancadorDeDadoBean.lanca();
	}

	public int getResultado() {
		return resultado;
	}

	public void setResultado(int resultado) {
		this.resultado = resultado;
	}

}
