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
import com.stefanini.stefaninifood.modelo.Produto;
import com.stefanini.stefaninifood.modelo.UnidadeFederacao;
import com.stefanini.stefaninifood.modelo.Venda;
import com.stefanini.stefaninifood.modelo.Produto;

@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {

	private static final long serialVersionUID = -1435181974130694243L;

	private Produto produto = new Produto();
	
	private Integer estabelecimentoId;
	
	@PostConstruct
	public void postConstruct() {
	    String produtoId = FacesContext.getCurrentInstance()
	        .getExternalContext().getRequestParameterMap().get("produtoId");
	    if (produtoId != null) {
	        this.produto = new DAO<>(Produto.class).buscaPorId(Integer.parseInt(produtoId));
	        this.estabelecimentoId = this.produto.getEstabelecimento().getId();
	    }
	    
	    String deleteId = FacesContext.getCurrentInstance()
		        .getExternalContext().getRequestParameterMap().get("deleteId");
	    
	    if (deleteId != null) {
	    	DAO<Produto> dao = new DAO<>(Produto.class);
	    	Produto produto = dao.buscaPorId(Integer.valueOf(deleteId));
	    	
	    	for (Venda venda : produto.getVendas()) {
		    	DAO<Venda> vendaDao = new DAO<>(Venda.class);
		    	vendaDao.remove(vendaDao.buscaPorId(venda.getId()));
			}
	    	
	        dao.remove(produto);
	    }
	}
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public List<Produto> getProdutos() {
		return new DAO<Produto>(Produto.class).listaTodos();
	}
	
	public List<Estabelecimento> getEstabelecimentos() {
		return new DAO<Estabelecimento>(Estabelecimento.class).listaTodos();
	}
	
	public void cadastrar() {
		this.produto.setEstabelecimento(new DAO<>(Estabelecimento.class).buscaPorId(estabelecimentoId));
		System.out.println("Cadastrando produto " + this.produto.getNome());
		if (produto.getEstabelecimento() == null) {
			FacesContext.getCurrentInstance().addMessage("endereco", 
					new FacesMessage("Produto deve ter um endereço cadastrado"));
			return;
			}
		
		if (produto.getId() == null) {
			new DAO<Produto>(Produto.class).adiciona(this.produto);
		}else {
			new DAO<Produto>(Produto.class).atualiza(produto);
		}

		this.produto = new Produto();
	}
	public Integer getEstabelecimentoId() {
		return estabelecimentoId;
	}
	public void setEstabelecimentoId(Integer estabelecimentoId) {
		this.estabelecimentoId = estabelecimentoId;
	}
	
	
	
	

	
	
}
