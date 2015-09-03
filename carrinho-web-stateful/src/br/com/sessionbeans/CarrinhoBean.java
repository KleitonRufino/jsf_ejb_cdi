package br.com.sessionbeans;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;

//Na versão 3.1, quando o acesso a um SLSB eh local, nao eh mais 
//necessario deﬁnir uma interface java nem utilizar a anotacao @Local. 
//Entao, bastaria implementar uma classe java com a anotação @Stateful.
@Stateful
public class CarrinhoBean {
	private Set<String> produtos = new HashSet<String>();
	private static int contadorTotal;
	private static int contadorAtivos;
	private int id;

	public void adiciona(String produto) {
		this.produtos.add(produto);
	}

	public void remove(String produto) {
		this.produtos.remove(produto);
	}

	public Set<String> getProdutos() {
		return produtos;
	}

	// logo apos a execucao desse metodo a instancia sera destruida pelo
	// EJBContainer.
	@Remove
	public void finalizaCompra() {
		System.out.println("Finalizando a compra");
	}

	// executa em cada instancia logo apos ela ser criada
	@PostConstruct
	public void postConstruct() {
		synchronized (CarrinhoBean.class) {
			CarrinhoBean.contadorTotal++;
			CarrinhoBean.contadorAtivos++;
			this.id = CarrinhoBean.contadorTotal;

			System.out.println("PostConstruct");
			System.out.println("ID: " + this.id);
			System.out.println("Contador Total: " + CarrinhoBean.contadorTotal);
			System.out.println("Contador Ativos: " + CarrinhoBean.contadorAtivos);
		}
	}

	// executa em cada instancia antes de ela ser passivada
	@PrePassivate
	public void prePassivate() {
		synchronized (CarrinhoBean.class) {
			CarrinhoBean.contadorAtivos--;

			System.out.println("PrePassivate");
			System.out.println("ID: " + this.id);
			System.out.println("Contador Total: " + CarrinhoBean.contadorTotal);
			System.out.println("Contador Ativos: " + CarrinhoBean.contadorAtivos);

		}
	}

	// executa em cada instancia depois de ela estar ativada
	@PostActivate
	public void postActivate() {
		synchronized (CarrinhoBean.class) {
			CarrinhoBean.contadorAtivos++;

			System.out.println("PostActivate");
			System.out.println("ID: " + this.id);
			System.out.println("Contador Total: " + CarrinhoBean.contadorTotal);
			System.out.println("Contador Ativos: " + CarrinhoBean.contadorAtivos);

		}
	}

	// executa em cada instancia antes de ela ser destruida
	@PreDestroy
	public void preDestroy() {
		synchronized (CarrinhoBean.class) {
			CarrinhoBean.contadorAtivos--;

			System.out.println("PostConstruct");
			System.out.println("ID: " + this.id);
			System.out.println("Contador Total: " + CarrinhoBean.contadorTotal);
			System.out.println("Contador Ativos: " + CarrinhoBean.contadorAtivos);

		}
	}
}
