package com.sistema.blog.dto;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.sistema.blog.entity.Comentario;
public class PublicacionDTO {
	
	private Long id;
	@NotEmpty
	@Size(min=2, message="El título de la publicacion deberia tener al menos dos caracteres")
	private String titulo;
	@NotEmpty
	@Size(min=10, message="La descripción de la publicación debería tener al menos diez caracteres")
	private String descripcion;
	@NotEmpty
	private String contenido;
	private Set<Comentario> comentarios; 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public PublicacionDTO() {
		super();
	}
	public Set<Comentario> getComentarios() {
		return comentarios;
	}
	public void setComentarios(Set<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	
}
