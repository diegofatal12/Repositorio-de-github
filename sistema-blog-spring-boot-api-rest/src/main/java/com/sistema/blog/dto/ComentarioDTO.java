package com.sistema.blog.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ComentarioDTO {
	private long id;
	@NotEmpty(message="El nombre no debe ser vacio o nulo")
	private String nombre;
	@NotEmpty(message="El email no debe ser vacio o nulo")
	@Email
	private String email;
	@NotEmpty
	@Size(min = 10, message = "El cuerpo debe tener al menos diez caracteres")
	private String cuerpo;
	public ComentarioDTO() {
		super();
	}
	public ComentarioDTO(long id, String nombre, String email, String cuerpo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.cuerpo = cuerpo;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCuerpo() {
		return cuerpo;
	}
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	
}
