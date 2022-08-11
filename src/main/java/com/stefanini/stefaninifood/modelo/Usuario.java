package com.stefanini.stefaninifood.modelo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Usuario implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -9222937960654786854L;
	@Id
	@GeneratedValue
	private Integer id;
	private String nome;
	private String email;
	private String telefone;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "usuario")
	private List<Venda> vendas;
	
	public Usuario() {
		endereco = new Endereco();
	}
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Venda> getVendas() {
		return vendas;
	}
	
	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, endereco, id, nome, telefone, vendas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(email, other.email) && Objects.equals(endereco, other.endereco)
				&& Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(telefone, other.telefone) && Objects.equals(vendas, other.vendas);
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone + ", endereco="
				+ endereco + ", vendas=" + vendas + "]";
	}
	
	
	
}
