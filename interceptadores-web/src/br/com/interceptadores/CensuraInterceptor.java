package br.com.interceptadores;

import java.util.ArrayList;
import java.util.List;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import br.com.entidades.Livro;

public class CensuraInterceptor {

	private List<String> palavrasProibidas = new ArrayList<String>();

	public CensuraInterceptor() {
		this.palavrasProibidas.add("coca-cola");
		this.palavrasProibidas.add("fiat");
		this.palavrasProibidas.add("sony");
	}

	@AroundInvoke
	public Object interceptador(InvocationContext ic) throws Exception {
		Object[] parameters = ic.getParameters();
		Livro livro = (Livro) parameters[0];
		for (String palavraProibida : this.palavrasProibidas) {

			String textoOriginal = livro.getNome();
			String textoCensurado = textoOriginal.replaceAll(palavraProibida, "!CENSURADO!");
			livro.setNome(textoCensurado);
		}
		return ic.proceed();
	}

}
