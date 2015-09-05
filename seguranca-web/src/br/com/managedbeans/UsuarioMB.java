package br.com.managedbeans;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import br.com.entidades.Grupo;
import br.com.entidades.Usuario;
import br.com.sessionbeans.GrupoRepositorio;
import br.com.sessionbeans.UsuarioRepositorio;

@ManagedBean
public class UsuarioMB {

	@EJB
	private UsuarioRepositorio usuarioRepositorio;
	@EJB
	private GrupoRepositorio grupoRepositorio;
	private Usuario usuario = new Usuario();
	private List<String> nomesDosGrupos;
	private List<Usuario> usuarios;
	private List<Grupo> grupos;

	public void adiciona() throws NoSuchAlgorithmException {
		for (String nomeDoGrupo : this.nomesDosGrupos) {
			Grupo g = new Grupo();
			g.setNome(nomeDoGrupo);
			this.usuario.getGrupos().add(g);
		}

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(this.usuario.getSenha().getBytes());
		BigInteger hash = new BigInteger(1, md.digest());
		String senhaCriptografia = hash.toString(16);
		while (senhaCriptografia.length() < 32) {
			senhaCriptografia = "0" + senhaCriptografia;
		}
		this.usuario.setSenha(senhaCriptografia);

		this.usuarioRepositorio.adiciona(this.usuario);
		this.usuario = new Usuario();
		this.usuarios = null;
	}

	public List<Grupo> getGrupos() {
		if (this.grupos == null) {
			this.grupos = this.grupoRepositorio.buscaTodos();
		}
		return this.grupos;
	}

	public List<Usuario> getUsuarios() {
		if (this.usuarios == null) {
			this.usuarios = this.usuarioRepositorio.buscaTodos();
		}
		return this.usuarios;
	}

	public List<String> getNomesDosGrupos() {
		return nomesDosGrupos;
	}

	public void setNomesDosGrupos(List<String> nomeDosGrupos) {
		this.nomesDosGrupos = nomeDosGrupos;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
