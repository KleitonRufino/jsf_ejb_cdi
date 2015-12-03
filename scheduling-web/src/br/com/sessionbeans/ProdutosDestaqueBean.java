package br.com.sessionbeans;

import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

import br.com.entidades.Produto;

@Singleton
public class ProdutosDestaqueBean {

	@EJB
	private ProdutoRepositorio produtoRepositorio;
	private Produto produtoDestaque;

	@Schedule(second = "*/5", minute = "*", hour = "*", persistent = false)
	public void trocaProdutoDestaque() {
		Random gerador = new Random();
		List<Produto> produtos = this.produtoRepositorio.getProdutos();
		int i = gerador.nextInt(produtos.size());
		this.produtoDestaque = produtos.get(i);
	}

	public Produto getProdutoDestaque() {
		return produtoDestaque;
	}

	public void setProdutoDestaque(Produto produtoDestaque) {
		this.produtoDestaque = produtoDestaque;
	}

}
