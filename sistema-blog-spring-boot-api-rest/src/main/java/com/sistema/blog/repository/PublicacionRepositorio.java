package com.sistema.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.blog.entity.Publicacion;
@Repository
public interface PublicacionRepositorio extends JpaRepository<Publicacion, Long> {

}
