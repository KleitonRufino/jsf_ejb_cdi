package br.com.sessionbeans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.entidades.Usuario;

@Stateless
public class UsuarioRepositorio {

	@PersistenceContext
	private EntityManager manager;

	public void adiciona(Usuario usuario) {
		this.manager.persist(usuario);
	}

	public List<Usuario> buscaTodos() {
		TypedQuery<Usuario> query = this.manager.createQuery("select x from Usuario x", Usuario.class);
		return query.getResultList();
	}
}
