package br.com.sessionbeans;

import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.entidades.Livro;
import br.com.interceptadores.CensuraInterceptor;

@Stateless
public class LivroRepositorio {
	@PersistenceContext
	private EntityManager manager;

	@Interceptors({ CensuraInterceptor.class })
	public void adiciona(Livro livro) {
		this.manager.persist(livro);
	}

	public List<Livro> getLivros() {
		TypedQuery<Livro> query = this.manager.createQuery("select x from Livro x", Livro.class);
		return query.getResultList();
	}
}
