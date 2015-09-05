package br.com.managedbeans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import br.com.entidades.Livro;
import br.com.sessionbeans.LivroRepositorio;

@ManagedBean
public class LivroMB {
	@EJB
	private LivroRepositorio repositorio;
	private Livro livro = new Livro();
	private List<Livro> livrosCache;

	public void adiciona() {
		this.repositorio.adiciona(this.livro);
		this.livro = new Livro();
		this.livrosCache = null;
	}

	public List<Livro> getLivros() {
		if (this.livrosCache == null) {
			this.livrosCache = this.repositorio.getLivros();
		}
		return this.livrosCache;
	}

	public Livro getLivro() {
		return this.livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
}
