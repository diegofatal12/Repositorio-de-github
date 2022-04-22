package com.sistema.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sistema.blog.dto.ComentarioDTO;
import com.sistema.blog.entity.Comentario;
import com.sistema.blog.entity.Publicacion;
import com.sistema.blog.exception.BlogAppException;
import com.sistema.blog.exception.ResourceNotFoundException;
import com.sistema.blog.repository.ComentarioRepositorio;
import com.sistema.blog.repository.PublicacionRepositorio;

@Service
public class ComentarioServicioImp implements ComentarioServicio{
	@Autowired
	ComentarioRepositorio comentarioRepositorio;
	@Autowired
	PublicacionRepositorio publicacionRepositorio;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public ComentarioDTO crearComentario(long publicacionId, ComentarioDTO comentarioDTO) {
		Comentario  comentario = mapearEntidad(comentarioDTO);
		Publicacion publicacion = publicacionRepositorio.findById(publicacionId)
				.orElseThrow(()->new ResourceNotFoundException("publicacion", "id", publicacionId));
		comentario.setPublicacion(publicacion);
		Comentario nuevoComentario = comentarioRepositorio.save(comentario);
		return mapearDTO(nuevoComentario);
	}
	private ComentarioDTO mapearDTO(Comentario comentario) {
		/*ComentarioDTO comentarioDTO = new ComentarioDTO();
		comentarioDTO.setId(comentario.getId());
		comentarioDTO.setNombre(comentario.getNombre());
		comentarioDTO.setCuerpo(comentario.getCuerpo());
		comentarioDTO.setEmail(comentario.getEmail());*/
		ComentarioDTO comentarioDTO = modelMapper.map(comentario, ComentarioDTO.class);
		return comentarioDTO;
	}
	private Comentario mapearEntidad(ComentarioDTO comentarioDTO) {
		/*Comentario comentario = new Comentario();
		comentario.setId(comentarioDTO.getId());
		comentario.setNombre(comentarioDTO.getNombre());
		comentario.setCuerpo(comentarioDTO.getCuerpo());
		comentario.setEmail(comentarioDTO.getEmail());*/
		Comentario comentario = modelMapper.map(comentarioDTO, Comentario.class);
		return comentario;
	}
	@Override
	public List<ComentarioDTO> obtenerComentariosPorPublicacion(long publicacionId) {
		List<Comentario> comentarios = comentarioRepositorio.findByPublicacionId(publicacionId);
		return comentarios.stream().map(comentario->mapearDTO(comentario)).collect(Collectors.toList());
	}
	@Override
	public ComentarioDTO obtenerComentarioPorId(Long publicacionId, Long comentarioId) {
		Publicacion publicacion = publicacionRepositorio.findById(publicacionId)
				.orElseThrow(()->new ResourceNotFoundException("publicacion", "id", publicacionId));
		Comentario comentario = comentarioRepositorio.findById(comentarioId)
				.orElseThrow(()-> new ResourceNotFoundException("comentario", "id", comentarioId));
		if(!comentario.getPublicacion().getId().equals(publicacion.getId())) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");
		}
		return mapearDTO(comentario);
	}
	
	@Override
	public ComentarioDTO actualizarComentario(Long publicacionId, Long comentarioId, ComentarioDTO solicitudDeComentario) {
		Publicacion publicacion = publicacionRepositorio.findById(publicacionId)
				.orElseThrow(()->new ResourceNotFoundException("publicacion", "id", publicacionId));
		Comentario comentario = comentarioRepositorio.findById(comentarioId)
				.orElseThrow(()-> new ResourceNotFoundException("comentario", "id", comentarioId));
		if(!comentario.getPublicacion().getId().equals(publicacion.getId())) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");
		}
		comentario.setNombre(solicitudDeComentario.getNombre());
		comentario.setCuerpo(solicitudDeComentario.getCuerpo());
		comentario.setEmail(solicitudDeComentario.getEmail());
		Comentario comentarioActualizado = comentarioRepositorio.save(comentario);
 
		return mapearDTO(comentarioActualizado);
	}
	@Override
	public void eliminarComentario(Long publicacionId, Long comentarioId) {
		Publicacion publicacion = publicacionRepositorio.findById(publicacionId)
				.orElseThrow(()->new ResourceNotFoundException("publicacion", "id", publicacionId));
		Comentario comentario = comentarioRepositorio.findById(comentarioId)
				.orElseThrow(()-> new ResourceNotFoundException("comentario", "id", comentarioId));
		if(!comentario.getPublicacion().getId().equals(publicacion.getId())) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");
		}
		
		comentarioRepositorio.delete(comentario);
	}
	
}
