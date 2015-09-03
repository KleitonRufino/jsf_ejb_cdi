package br.com.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.sessionbeans.CarrinhoBean;

@ManagedBean
@SessionScoped
public class CarrinhoMB {

	@EJB
	private CarrinhoBean carrinhoBean;
	private String produto;
	
	public List<String> getProdutos() {
		return new ArrayList<String>(this.carrinhoBean.getProdutos());
	}

	public void adiciona() {
		this.carrinhoBean.adiciona(produto);
	}

	public void remove(String produto) {
		this.carrinhoBean.remove(produto);
	}

	public CarrinhoBean getCarrinhoBean() {
		return carrinhoBean;
	}

	public void setCarrinhoBean(CarrinhoBean carrinhoBean) {
		this.carrinhoBean = carrinhoBean;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}
	
	
}
