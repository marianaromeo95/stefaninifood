package com.stefanini.stefaninifood.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.stefanini.stefaninifood.dao.DAO;
import com.stefanini.stefaninifood.modelo.Endereco;
import com.stefanini.stefaninifood.modelo.Estabelecimento;
import com.stefanini.stefaninifood.modelo.Produto;
import com.stefanini.stefaninifood.modelo.UnidadeFederacao;
import com.stefanini.stefaninifood.modelo.Produto;

@ManagedBean
@ViewScoped
public class PageBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -960736448806142677L;

	public String moveToUsuario() {
		return "usuario?faces-redirect=true";
	}
	
	public String moveToVenda() {
		return "venda?faces-redirect=true";
	}
	
	public String moveToProduto() {
		return "produto?faces-redirect=true";
	}
	
	public String moveToEstabelecimento() {
		return "estabelecimento?faces-redirect=true";
	}
}
