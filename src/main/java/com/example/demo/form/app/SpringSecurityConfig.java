package com.example.demo.form.app;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.form.app.auth.handler.LoginSucccesHandler;
import com.example.demo.form.app.entity.service.JpaUserDetailsService;


@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)//para poder poner antoaciones en cuanto a rol
@Configuration
public class SpringSecurityConfig{
	//inyectamos el datasource para la conexion a la base de datos y de ahí sacar los ususarios y credenciales
	@Autowired 
	DataSource dataSource;
	@Autowired
	private JpaUserDetailsService  userDetailsService;//nuestro service para los usuarios
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;//este metodo estaba antes aquí, puede ir sin problema
	
	
	
	@Autowired
	private LoginSucccesHandler successHandler;
	
	///creamos los usuarios
	@Bean
	public UserDetailsService userDetailsService(AuthenticationManagerBuilder build)throws Exception{
		/*		
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User
	            .withUsername("mauricio")
	            .password(passwordEncoder.encode("user"))
	            .roles("USER")
	            .build());
		 manager.createUser(User
		            .withUsername("admin")
		            .password(passwordEncoder.encode("admin"))
		            .roles("ADMIN","USER")
		            .build());
		*/
		
		
		
		/*
		 *con jdbc
		 *build.jdbcAuthentication()
		.dataSource(dataSource)//le pasamos la conexion
		.passwordEncoder(passwordEncoder)
		.usersByUsernameQuery("select username, password, enabled from users where username=?")//para el login
		.authoritiesByUsernameQuery("select u.username, a.authority from authorities a inner join users u on (a.user_id=u.id) where u.username=?");
		
		*/
		//jpa
		build.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);
		
		return null;
	}
	
	
	//protegemos las rutas
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
 
		http.authorizeRequests().antMatchers("/css/**", "/js/**").permitAll()//rutas pulicas
		//rutas privadas	
		.antMatchers("/").hasAnyRole("USER")
			
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.successHandler(successHandler)//mostrar nuestro mensaje de exito al iniciar sesión
				.loginPage("/login")//nuestro login está mapeado a esta ruta
				.permitAll()//el login está permitido para todos
				.and()
				.logout().permitAll()
				.and()
				.exceptionHandling().accessDeniedPage("/error_403");//para mostar la pagina de acceso denegado
 
		return http.build();
	}
}
