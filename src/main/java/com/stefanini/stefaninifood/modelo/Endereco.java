package com.stefanini.stefaninifood.modelo;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Endereco implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3566718766864542611L;

	@Id
	@GeneratedValue
	private Integer id;

	private String endereco;
	private String numero;
	private String cidade;
	private String estado;
	private String cep;

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cep, cidade, endereco, estado, id, numero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		return Objects.equals(cep, other.cep) && Objects.equals(cidade, other.cidade)
				&& Objects.equals(endereco, other.endereco) && Objects.equals(estado, other.estado)
				&& Objects.equals(id, other.id) && Objects.equals(numero, other.numero);
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", endereco=" + endereco + ", numero=" + numero + ", cidade=" + cidade
				+ ", estado=" + estado + ", cep=" + cep + "]";
	}
	
	

}
