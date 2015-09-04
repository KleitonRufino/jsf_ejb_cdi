package br.com.sessionbeans;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.entidades.Produto;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ProdutoRepositorio {

	@PersistenceContext
	private EntityManager manager;
	@Resource
	private SessionContext context;

	public void adiciona(Produto produto) {
		this.manager.persist(produto);
		if (produto.getPreco() < 0) {
			// Quando algum erro eh identiﬁcado pela aplicacao,ela pode marcar a
			// transacao corrente para rollback atraves do setRollbackOnly() do
			// SessionContext
			this.context.setRollbackOnly();
		}
	}

	public List<Produto> getProdutos() {
		TypedQuery<Produto> query = this.manager.createQuery("select x from Produto x", Produto.class);
		return query.getResultList();
	}

}
