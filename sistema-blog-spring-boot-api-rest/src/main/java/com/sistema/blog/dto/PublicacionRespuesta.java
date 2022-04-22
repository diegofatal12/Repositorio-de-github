package com.sistema.blog.dto;

import java.util.List;

public class PublicacionRespuesta {
	private List<PublicacionDTO> contenido;
	private int numeroDePagina;
	private int medidaDePagina;
	private long totalElementos;
	private int totalPaginas;
	private boolean ultima;
	public PublicacionRespuesta() {
		super();
	}
	public List<PublicacionDTO> getContenido() {
		return contenido;
	}
	public void setContenido(List<PublicacionDTO> contenido) {
		this.contenido = contenido;
	}
	public int getNumeroDePagina() {
		return numeroDePagina;
	}
	public void setNumeroDePagina(int numeroDePagina) {
		this.numeroDePagina = numeroDePagina;
	}
	public int getMedidaDePagina() {
		return medidaDePagina;
	}
	public void setMedidaDePagina(int medidaDePagina) {
		this.medidaDePagina = medidaDePagina;
	}
	public long getTotalElementos() {
		return totalElementos;
	}
	public void setTotalElementos(long l) {
		this.totalElementos = l;
	}
	public int getTotalPaginas() {
		return totalPaginas;
	}
	public void setTotalPaginas(int totalPaginas) {
		this.totalPaginas = totalPaginas;
	}
	public boolean isUltima() {
		return ultima;
	}
	public void setUltima(boolean ultima) {
		this.ultima = ultima;
	}
	
}
