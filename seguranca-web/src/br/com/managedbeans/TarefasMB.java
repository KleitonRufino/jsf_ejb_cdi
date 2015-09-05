package br.com.managedbeans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import br.com.sessionbeans.TarefasBean;

@ManagedBean
public class TarefasMB {

	@EJB
	private TarefasBean tarefasBean;
	private String tarefa;

	public void adiciona() {
		this.tarefasBean.adiciona(tarefa);
	}

	public void remove(String tarefa) {
		this.tarefasBean.remove(tarefa);
	}

	public List<String> getTarefas() {
		return this.tarefasBean.listaTerefas();
	}

	public String getTarefa() {
		return tarefa;
	}

	public void setTarefa(String tarefa) {
		this.tarefa = tarefa;
	}

	public TarefasBean getTarefasBean() {
		return tarefasBean;
	}

	public void setTarefasBean(TarefasBean tarefasBean) {
		this.tarefasBean = tarefasBean;
	}

}
