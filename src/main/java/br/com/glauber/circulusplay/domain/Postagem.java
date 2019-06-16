package br.com.glauber.circulusplay.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Postagem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(pattern = "dd/MM/yyyy hh:mm")
	@CreationTimestamp
	private Date instante;

	@Column(length = 4000)
	private String titulo;

	@ManyToOne
	@JoinColumn(name="filme_id")
	private Filme filmeAssistido;
	
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario portadoPor;

	public Postagem() {

	}

	public Postagem(Long id, Date instante, String titulo, Filme filmeAssistido, Usuario portadoPor) {
		this.id = id;
		this.instante = instante;
		this.titulo = titulo;
		this.filmeAssistido = filmeAssistido;
		this.portadoPor = portadoPor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Filme getFilmeAssistido() {
		return filmeAssistido;
	}

	public void setFilmeAssistido(Filme filmeAssistido) {
		this.filmeAssistido = filmeAssistido;
	}

	public Usuario getPortadoPor() {
		return portadoPor;
	}

	public void setPortadoPor(Usuario portadoPor) {
		this.portadoPor = portadoPor;
	}

}