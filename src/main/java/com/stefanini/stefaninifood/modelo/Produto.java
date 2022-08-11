package com.stefanini.stefaninifood.modelo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Produto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3120432906003573547L;

	@Id
	@GeneratedValue
	private Integer id;
	
	private String nome;
	
	private Double preco;
	
	@ManyToOne
	private Estabelecimento estabelecimento;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "produto")
	private List<Venda> vendas;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", preco=" + preco + ", estabelecimento=" + estabelecimento.getEmail()
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(estabelecimento, id, nome, preco, getVendas());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(estabelecimento, other.estabelecimento) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) && Objects.equals(preco, other.preco)
				&& Objects.equals(getVendas(), other.getVendas());
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}


	
	
	
}
