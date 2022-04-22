package com.sistema.blog.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.dto.PublicacionRespuesta;
import com.sistema.blog.entity.Publicacion;
import com.sistema.blog.exception.ResourceNotFoundException;
import com.sistema.blog.repository.PublicacionRepositorio;

@Service
public class PublicacionServicioImp implements PublicacionServicio {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PublicacionRepositorio publicacionRepositorio;
	
	@Override
	public PublicacionRespuesta obtenerTodasLasPublicaciones(int numeroDepagina, int medidaDePagina, String ordenarPor, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(ordenarPor).ascending():Sort.by(ordenarPor).descending();
		Pageable pageable = PageRequest.of(numeroDepagina, medidaDePagina, sort);
		Page<Publicacion> publicaciones = publicacionRepositorio.findAll(pageable);

		List<Publicacion> listaDePublicaciones = publicaciones.getContent();
		List<PublicacionDTO> contenido =
		listaDePublicaciones.stream().map(publicacion -> mapearDTO(publicacion)).collect(Collectors.toList());
		
		PublicacionRespuesta publicacionRespuesta = new PublicacionRespuesta();
		publicacionRespuesta.setContenido(contenido);
		publicacionRespuesta.setMedidaDePagina(publicaciones.getSize());
		publicacionRespuesta.setNumeroDePagina(publicaciones.getNumber());
		publicacionRespuesta.setTotalElementos(publicaciones.getTotalElements());
		publicacionRespuesta.setTotalPaginas(publicaciones.getTotalPages());
		publicacionRespuesta.setUltima(publicaciones.isLast());
		return publicacionRespuesta;
	}

	@Override
	public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
		// porlo que entendi de un jason pasamos a una entidad y esa entidad la
		// guardamos en
		// la base de datos y creamos una respuesta en formato jason y la devolvemos
		// Convertimos DTO a entidad
		Publicacion publicacion = new Publicacion();
		publicacion = mapearEntidad(publicacionDTO);
		// guardamos en la base de datos
		Publicacion nuevaPublicacion = publicacionRepositorio.save(publicacion);

		// Convertimos de entidad a DTO
		PublicacionDTO publicacionRespuesta = new PublicacionDTO();
		publicacionRespuesta = mapearDTO(nuevaPublicacion);
		return publicacionRespuesta;// retornamos el jason
	}



	// Convertir Entidad a DTO
	private PublicacionDTO mapearDTO(Publicacion publicacion) {
		/*PublicacionDTO publicacionDTO = new PublicacionDTO();
		publicacionDTO.setId(publicacion.getId());
		publicacionDTO.setTitulo(publicacion.getTitulo());
		publicacionDTO.setDescripcion(publicacion.getDescripcion());
		publicacionDTO.setContenido(publicacion.getContenido());*/
		PublicacionDTO publicacionDTO = modelMapper.map(publicacion, PublicacionDTO.class);
		return publicacionDTO;
	}

	// Convertir de DTO a entidad
	private Publicacion mapearEntidad(PublicacionDTO publicacionDTO) {
		/*Publicacion publicacion = new Publicacion();
		publicacion.setId(publicacionDTO.getId());
		publicacion.setTitulo(publicacionDTO.getTitulo());
		publicacion.setDescripcion(publicacionDTO.getDescripcion());
		publicacion.setContenido(publicacionDTO.getContenido());*/
		Publicacion publicacion = modelMapper.map(publicacionDTO, Publicacion.class);
		return publicacion;
	}

	@Override
	public PublicacionDTO obtenerPublicacionPorId(long id) {
		/*Optional<PublicacionDTO> publicacion = 
				publicacionRepositorio.findById(id).map(item -> mapearDTO(item))
						.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "Id", id));
		return publicacion.get();*/
		Optional<Publicacion> publicacion = publicacionRepositorio.findById(id);
		if (publicacion.isPresent()) {
			return mapearDTO(publicacion.get());
		} else {
			throw new ResourceNotFoundException("Publicacion", "Id", id);
		}
	
	}
	
	@Override
	public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, long id) {
		Optional<Publicacion> optionalPublicacion = publicacionRepositorio
						.findById(id);
		if(optionalPublicacion.isPresent()) {
		Publicacion publicacion = optionalPublicacion.get();
		publicacion.setTitulo(publicacionDTO.getTitulo());
		publicacion.setContenido(publicacionDTO.getContenido());
		publicacion.setDescripcion(publicacionDTO.getDescripcion());
		Publicacion publicacionActualizada = publicacionRepositorio.save(publicacion);
		return mapearDTO(publicacionActualizada);}
		else{
			throw new ResourceNotFoundException("Publicacion", "Id", id);
		}
	}

	@Override
	public void eliminarPublicacion(long id) {
		publicacionRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("publicacion", "Id", id));	
	}

}
