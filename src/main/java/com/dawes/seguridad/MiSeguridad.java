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
	
	
	// programa la autenticacion
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(su);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/insertar", "/modificar","/eliminar").hasAnyRole("USER","ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,  "/panel").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/proveedores").hasRole("ADMIN");
        //seguridad de ProveedoresWS
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/proveedor").hasAnyRole("AUTENTICADO","ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/proveedor").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/proveedor/*").hasRole("ADMIN");
		http.httpBasic();
		http.formLogin().loginPage("/login");
		http.exceptionHandling().accessDeniedPage("/403");
		http.logout().logoutSuccessUrl("/index");
		http.csrf().disable();
	}
	
}
