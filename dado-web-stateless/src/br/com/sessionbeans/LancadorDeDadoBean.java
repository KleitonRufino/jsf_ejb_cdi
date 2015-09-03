package br.com.sessionbeans;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

//Na versão 3.1, quando o acesso a um SLSB eh local, nao eh mais 
//necessario deﬁnir uma interface java nem utilizar a anotacao @Local. 
//Entao, bastaria implementar uma classe java com a anotação @Stateless.
@Stateless
// @Remote(LancadorDeDado.class)
// no caso de acesso Remote implementar interface LancadorDedDado
public class LancadorDeDadoBean {

	private Random gerador = new Random();
	private static int contador;

	// executar cada instancia imediatamente antes dela ser criada
	@PostConstruct
	public void inicializando() {
		synchronized (LancadorDeDadoBean.class) {

			LancadorDeDadoBean.contador++;
			System.out.println("criando um lancador de dados...");
			System.out.println("Total: " + LancadorDeDadoBean.contador);
		}
	}

	// executar cada instancia imediatamente antes dela ser destruida
	@PreDestroy
	public void destruindo() {
		synchronized (LancadorDeDadoBean.class) {

			LancadorDeDadoBean.contador++;
			System.out.println("destruindo um lancador de dados...");
			System.out.println("Total: " + LancadorDeDadoBean.contador);
		}
	}

	@Asynchronous
	public Future<Map<Integer, Integer>> calculaFrequencia() {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(1, 0);
		map.put(2, 0);
		map.put(3, 0);
		map.put(4, 0);
		map.put(5, 0);
		map.put(6, 0);

		for (int i = 0; i < this.gerador.nextInt(6); i++) {
			int v = this.gerador.nextInt(6) + 1;
			map.put(v, map.get(v) + 1);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
			System.out.println(i);
		}

		return new AsyncResult<Map<Integer, Integer>>(map);
	}

	public int lanca() {
		return this.gerador.nextInt(6) + 1;
	}
}
