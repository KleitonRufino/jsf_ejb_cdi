package br.com.sessionbeans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.entidades.Grupo;

@Stateless
public class GrupoRepositorio {

	@PersistenceContext
	private EntityManager manager;

	public void adiciona(Grupo grupo) {
		this.manager.persist(grupo);
	}

	public List<Grupo> buscaTodos() {
		TypedQuery<Grupo> query = this.manager.createQuery("select x from Grupo x", Grupo.class);
		return query.getResultList();
	}
}
