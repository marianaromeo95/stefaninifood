package com.stefanini.stefaninifood.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.stefanini.stefaninifood.dao.DAO;
import com.stefanini.stefaninifood.modelo.Endereco;
import com.stefanini.stefaninifood.modelo.Estabelecimento;
import com.stefanini.stefaninifood.modelo.UnidadeFederacao;
import com.stefanini.stefaninifood.modelo.Usuario;
import com.stefanini.stefaninifood.modelo.Venda;

@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = -1435181974130694243L;

	private Usuario usuario = new Usuario();
	private Integer enderecoId;
	private Integer estadoId;
	
	
	@PostConstruct
	public void postConstruct() {
	    String usuarioIdParam = FacesContext.getCurrentInstance()
	        .getExternalContext().getRequestParameterMap().get("usuarioId");
	    if (usuarioIdParam != null) {
	        this.usuario = new DAO<>(Usuario.class).buscaPorId(Integer.parseInt(usuarioIdParam));
	    }
	    
	    String deleteId = FacesContext.getCurrentInstance()
		        .getExternalContext().getRequestParameterMap().get("deleteId");
	    
	    if (deleteId != null) {
	    	DAO<Usuario> dao = new DAO<>(Usuario.class);
	    	Usuario usuario = dao.buscaPorId(Integer.valueOf(deleteId));

//	    	for (Venda venda : usuario.getVendas()) {
//		    	DAO<Venda> vendaDao = new DAO<>(Venda.class);
//		    	vendaDao.remove(vendaDao.buscaPorId(venda.getId()));
//			}
	    	
		        dao.remove(usuario);
	    }
	}
	
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Integer getEnderecoId() {
		return enderecoId;
	}
	
	public void setEnderecoId(Integer enderecoId) {
		this.enderecoId = enderecoId;
	}

	public Integer getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}
	
	public List<Usuario> getUsuarios() {
		return new DAO<Usuario>(Usuario.class).listaTodos();
	}
	
	public List<Endereco> getEnderecos() {
		return new DAO<Endereco>(Endereco.class).listaTodos();
	}
	
	public Endereco getEnderecoUsuario() {
		return this.usuario.getEndereco();
	}
	
	public List<UnidadeFederacao> getEstados() {
		List<UnidadeFederacao> unidadeFederacaos = Arrays.asList(UnidadeFederacao.values());
		unidadeFederacaos.sort(new Comparator<UnidadeFederacao>() {

			@Override
			public int compare(UnidadeFederacao o1, UnidadeFederacao o2) {
				return o1.getSigla().compareTo(o2.getSigla());
			}
        });
		return unidadeFederacaos;
	}
	
	public void cadastrarEndereco() {
		Endereco endereco = new DAO<Endereco>(Endereco.class).buscaPorId(this.enderecoId);
		this.usuario.setEndereco(endereco);
	}
	
	public void cadastrar() {
		System.out.println("Cadastrando usuario " + this.usuario.getNome());
		if (usuario.getEndereco() == null) {
			FacesContext.getCurrentInstance().addMessage("endereco", 
					new FacesMessage("Usuario deve ter um endereço cadastrado"));
			return;
			}
		
		if (usuario.getId() == null) {
			new DAO<Usuario>(Usuario.class).adiciona(this.usuario);
		}else {
			new DAO<Usuario>(Usuario.class).atualiza(this.usuario);
		}

		this.usuario = new Usuario();
	}
}
