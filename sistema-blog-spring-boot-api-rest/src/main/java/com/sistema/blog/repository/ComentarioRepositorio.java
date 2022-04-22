package com.sistema.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.blog.entity.Comentario;

@Repository
public interface ComentarioRepositorio extends JpaRepository<Comentario, Long>{
	public List<Comentario> findByPublicacionId(long publicacionId);
}
