package com.stefanini.stefaninifood.bean;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;
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
import com.stefanini.stefaninifood.modelo.Produto;
import com.stefanini.stefaninifood.modelo.UnidadeFederacao;
import com.stefanini.stefaninifood.modelo.Usuario;
import com.stefanini.stefaninifood.modelo.Venda;

@ManagedBean
@ViewScoped
public class VendaBean implements Serializable {

	private static final long serialVersionUID = -1435181974130694243L;

	private Venda venda = new Venda();
	private Integer estabelecimentoId;
	private Integer usuarioId;
	private Integer produtoId;
	
	
	@PostConstruct
	public void postConstruct() {
	    String vendaId = FacesContext.getCurrentInstance()
	        .getExternalContext().getRequestParameterMap().get("vendaId");
	    if (vendaId != null) {
	        this.venda = new DAO<>(Venda.class).buscaPorId(Integer.parseInt(vendaId));
	        this.estabelecimentoId = this.venda.getProduto().getEstabelecimento().getId();
	        this.produtoId = this.venda.getProduto().getId();
	        this.usuarioId = this.venda.getUsuario().getId();
	    }
	    
	    String deleteId = FacesContext.getCurrentInstance()
		        .getExternalContext().getRequestParameterMap().get("deleteId");
	    
	    if (deleteId != null) {
	    	DAO<Venda> dao = new DAO<>(Venda.class);
	    	Venda idVenda = dao.buscaPorId(Integer.valueOf(deleteId));
	        dao.remove(idVenda);
	    }
	}
	
	
	
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	public Integer getEstabelecimentoId() {
		return estabelecimentoId;
	}
	
	
	public void setEstabelecimentoId(Integer estabelecimentoId) {
		this.estabelecimentoId = estabelecimentoId;
	}
	
	public List<Produto> getEstabelecimentoProdutos() {
		return new DAO<>(Estabelecimento.class).buscaPorId(estabelecimentoId).getProdutos();
	}
	
	public List<Venda> getVendas() {
		return new DAO<Venda>(Venda.class).listaTodos();
	}
	
	public List<Usuario> getUsuarios() {
		return new DAO<Usuario>(Usuario.class).listaTodos();
	}
	
	public List<Produto> getProdutos() {
		return new DAO<Produto>(Produto.class).listaTodos();
	}
	
	public List<Estabelecimento> getEstabelecimentos() {
		return new DAO<Estabelecimento>(Estabelecimento.class).listaTodos();
	}
	
	public void cadastrar() {
		venda.setProduto(new DAO<>(Produto.class).buscaPorId(produtoId));
		venda.setUsuario(new DAO<>(Usuario.class).buscaPorId(usuarioId));
		System.out.println("Cadastrando venda " + this.venda.getDataDoPedido());
		if (venda.getProduto() == null) {
			FacesContext.getCurrentInstance().addMessage("produto", 
					new FacesMessage("Venda deve ter um produto cadastrado"));
			return;
		}
		
		if (venda.getUsuario() == null) {
			FacesContext.getCurrentInstance().addMessage("usuario", 
					new FacesMessage("Venda deve ter um usuario cadastrado"));
			return;
		}
		
		
		venda.setDataDoPedido(Calendar.getInstance());
			
		if (venda.getId() == null) {
			new DAO<Venda>(Venda.class).adiciona(this.venda);
		}else {
			new DAO<Venda>(Venda.class).atualiza(venda);
		}

		
		this.venda = new Venda();
	}
	public Integer getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
	public Integer getProdutoId() {
		return produtoId;
	}
	public void setProdutoId(Integer produtoId) {
		this.produtoId = produtoId;
	}
	
	
	
	

	
	
}
