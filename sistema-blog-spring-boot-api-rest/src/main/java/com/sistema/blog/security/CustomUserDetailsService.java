package com.sistema.blog.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sistema.blog.entity.Rol;
import com.sistema.blog.entity.Usuario;
import com.sistema.blog.repository.UsuarioRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UsuarioRepository usuarioRepositorio;

	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepositorio.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
				.orElseThrow(()->new UsernameNotFoundException("Usuario no encontrado con ese usuario o email  " + usernameOrEmail));
		return new User(usuario.getEmail(),usuario.getPassword(), mapearRoles(usuario.getRoles()));
	}
	private Collection<? extends GrantedAuthority> mapearRoles(Set<Rol> roles){
		return roles.stream().map(rol->new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());
	}
} 
