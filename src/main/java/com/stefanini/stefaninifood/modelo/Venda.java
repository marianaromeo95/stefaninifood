package com.stefanini.stefaninifood.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Calendar;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Venda implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8077911918236975598L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	private Produto produto;
	
	@ManyToOne
	private Usuario usuario;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataDoPedido;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataDaEntrega;

	@Override
	public int hashCode() {
		return Objects.hash(dataDaEntrega, dataDoPedido, id, produto, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venda other = (Venda) obj;
		return Objects.equals(dataDaEntrega, other.dataDaEntrega) && Objects.equals(dataDoPedido, other.dataDoPedido)
				&& Objects.equals(id, other.id) && Objects.equals(produto, other.produto)
				&& Objects.equals(usuario, other.usuario);
	}

	@Override
	public String toString() {
		return "Venda [id=" + id + ", produto=" + produto + ", usuario=" + usuario + ", dataDoPedido=" + dataDoPedido
				+ ", dataDaEntrega=" + dataDaEntrega + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Calendar getDataDoPedido() {
		return dataDoPedido;
	}

	public void setDataDoPedido(Calendar dataDoPedido) {
		this.dataDoPedido = dataDoPedido;
	}

	public Calendar getDataDaEntrega() {
		return dataDaEntrega;
	}

	public void setDataDaEntrega(Calendar dataDaEntrega) {
		this.dataDaEntrega = dataDaEntrega;
	}
}
