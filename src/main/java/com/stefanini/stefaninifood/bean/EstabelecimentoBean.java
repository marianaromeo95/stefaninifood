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
import javax.persistence.EntityManager;

import com.stefanini.stefaninifood.dao.DAO;
import com.stefanini.stefaninifood.modelo.Endereco;
import com.stefanini.stefaninifood.modelo.Estabelecimento;
import com.stefanini.stefaninifood.modelo.Produto;
import com.stefanini.stefaninifood.modelo.UnidadeFederacao;
import com.stefanini.stefaninifood.modelo.Usuario;
import com.stefanini.stefaninifood.modelo.Venda;

@ManagedBean
@ViewScoped
public class EstabelecimentoBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8254016412819510112L;
	private Estabelecimento estabelecimento = new Estabelecimento();
	
	@PostConstruct
	public void postConstruct() {
	    String estabelecimentoIdParam = FacesContext.getCurrentInstance()
	        .getExternalContext().getRequestParameterMap().get("estabelecimentoId");
	    if (estabelecimentoIdParam != null) {
	        this.estabelecimento = new DAO<>(Estabelecimento.class).buscaPorId(Integer.parseInt(estabelecimentoIdParam));
	    }
	    
	    String deleteId = FacesContext.getCurrentInstance()
		        .getExternalContext().getRequestParameterMap().get("deleteId");
	    
	    if (deleteId != null) {
	    	delete(deleteId);
	    }
	}

	
	public void delete(String deleteId) {
		DAO<Estabelecimento> dao = new DAO<>(Estabelecimento.class);	    	
		EntityManager entityManager = dao.entityManager();
		Estabelecimento estabelecimento = dao.buscaPorId(Integer.parseInt(deleteId));
		
		
		entityManager.getTransaction().begin();
		entityManager.createQuery("delete Venda v where v.produto in :produtos")
		.setParameter("produtos", estabelecimento.getProdutos())
		.executeUpdate();
		entityManager.getTransaction().commit();
		dao.remove(estabelecimento);
	}
	
	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}
	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	
	public List<Estabelecimento> getEstabelecimentos() {
		return new DAO<Estabelecimento>(Estabelecimento.class).listaTodos();
	}
	
	public List<Endereco> getEnderecos() {
		return new DAO<Endereco>(Endereco.class).listaTodos();
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
	
//	public void cadastrarEndereco() {
//		Endereco endereco = new DAO<Endereco>(Endereco.class).buscaPorId(this.enderecoId);
//		this.usuario.setEndereco(endereco);
//	}
	
	public void cadastrar() {
		System.out.println("Cadastrando usuario " + this.estabelecimento.getNome());
		if (estabelecimento.getEndereco() == null) {
			FacesContext.getCurrentInstance().addMessage("endereco", 
					new FacesMessage("Usuario deve ter um endereço cadastrado"));
			return;
			}
		
		
		if (estabelecimento.getId() == null) {
			new DAO<Estabelecimento>(Estabelecimento.class).adiciona(this.getEstabelecimento());
		}else {
			new DAO<Estabelecimento>(Estabelecimento.class).atualiza(estabelecimento);
		}

		this.estabelecimento = new Estabelecimento();
	}
	
	
	
	

	
	
}
