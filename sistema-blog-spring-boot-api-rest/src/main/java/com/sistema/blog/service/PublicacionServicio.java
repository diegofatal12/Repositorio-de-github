package com.sistema.blog.service;



import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.dto.PublicacionRespuesta;


public interface PublicacionServicio {
	public PublicacionRespuesta obtenerTodasLasPublicaciones(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir);
	public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);
	public PublicacionDTO obtenerPublicacionPorId(long id);
	public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, long id);
	public void eliminarPublicacion(long id);
}
