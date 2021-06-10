package com.dawes.seguridad;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.dawes.servicioImpl.ServicioUsuarioImpl;

@Configuration
@EnableWebSecurity
public class MiSeguridad extends WebSecurityConfigurerAdapter{

	@Autowired
	ServicioUsuarioImpl su;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public String encripta(String password) {
		return passwordEncoder().encode(password);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(su);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/insertar", "/editarusuario", "/editarpublicacion","/eliminarpublicacion", "/verPublicacion", "/perfil", "/exito").hasAnyRole("USER","ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/panel", "/eliminarusuario").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/persistirpublicacion","/editarusuario", "/editarpublicacion").hasAnyRole("USER","ADMIN");
		http.httpBasic();
		http.formLogin().loginPage("/login");
		http.exceptionHandling().accessDeniedPage("/403");
		http.logout().logoutSuccessUrl("/index");
		http.csrf().disable();
	}
	
}
