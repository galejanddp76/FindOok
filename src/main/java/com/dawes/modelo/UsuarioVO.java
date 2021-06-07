package com.dawes.modelo;

import java.time.LocalDate;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class UsuarioVO implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idusuario;
	
	@Size(min = 5, message = "El tamaño del nombre de usuario debe ser mayor de 5 carácteres")
	private String username;
	
	@Column(unique = true)
	private String correo;
	
	@DateTimeFormat(pattern = "yyy-MM-dd")
	private LocalDate fecharegistro;
	
	private String password;
	
	@Transient
	private String confirmarpassword;
	
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
	@JsonIgnore
	List<UsuarioRolVO> roles;
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> privilegios= new ArrayList<>();
		for(UsuarioRolVO u:roles)
		privilegios.add(new SimpleGrantedAuthority(u.getRol().getNombrerol()));
		return privilegios;
	}
	
	public boolean isAccountNonExpired() {
		return true;
	}
	public boolean isAccountNonLocked() {
		return true;
	}
	public boolean isCredentialsNonExpired() {
		return true;
	}
	public boolean isEnabled() {
		return true;
	}

	private static final long serialVersionUID = 1L;
}
