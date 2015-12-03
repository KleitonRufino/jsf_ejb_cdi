package br.com.managedbeans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import br.com.entidades.Produto;
import br.com.sessionbeans.ProdutoRepositorio;
import br.com.sessionbeans.ProdutosDestaqueBean;

@ManagedBean
public class ProdutoMB {

	@EJB
	private ProdutoRepositorio repositorio;
	@EJB
	private ProdutosDestaqueBean produtosDestaqueBean;
	private Produto produto = new Produto();
	private List<Produto> produtosCache;

	public void adiciona() {
		this.repositorio.adiciona(produto);
		this.produto = new Produto();
		this.produtosCache = null;
	}

	public List<Produto> getProdutos() {
		if (this.produtosCache == null)
			this.produtosCache = this.repositorio.getProdutos();
		return this.produtosCache;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Produto getProdutoDestaque() {
		return this.produtosDestaqueBean.getProdutoDestaque();
	}
}
