package com.example.demo.form.app.entity.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.form.app.entity.Role;
import com.example.demo.form.app.entity.Usuario;
import com.example.demo.form.app.entity.dao.IUsuarioDao;


@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService{

	private Logger logger=LoggerFactory.getLogger(getClass());
@Autowired
private IUsuarioDao usuarioDao;
	public JpaUserDetailsService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario=usuarioDao.findByUsername(username);
		if(usuario == null) {
			logger.error("Error login: no exite el usuario '"+username +"'");
			throw new UsernameNotFoundException("username "+username+" no existe en el sistema!");
		}
		
	
		List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();//Aquí vamos a guardar los roles del usuario ya que es una lista de tipo roles
		
		for(Role role:usuario.getRoles()) {
			logger.info("Role: ".concat(role.getAuthority()));
			authorities.add(new SimpleGrantedAuthority(role.getAuthority()));//estamos registrando los roles
			
		}
		
		if(authorities.isEmpty()) {//si no hay roles
			logger.error("Error login: usuario '"+username +"' no tiene roles asignados!");
			throw new UsernameNotFoundException("Error login: usuario '"+username +"' no tiene roles asignados!");
		}
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
		//UserDetails es una interface que representa un usuario autenticado
	}

}
