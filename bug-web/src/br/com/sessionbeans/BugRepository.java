package br.com.sessionbeans;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.entidades.Bug;

@Stateless
@RolesAllowed({ "ADMIN", "USERS" })
public class BugRepository {

	@PersistenceContext
	private EntityManager manager;

	public void add(Bug bug) {
		this.manager.persist(bug);
	}

	public void edit(Bug bug) {
		this.manager.merge(bug);
	}

	@RolesAllowed({ "ADMIN" })
	public void removeById(Long id) {
		Bug bug = this.manager.find(Bug.class, id);
		this.manager.remove(bug);
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Bug> findAll() {
		TypedQuery<Bug> query = this.manager.createQuery("select x from Bug x", Bug.class);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Bug findById(Long id) {
		return this.manager.find(Bug.class, id);
	}
}
