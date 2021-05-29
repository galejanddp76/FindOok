package com.dawes.modelo;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuariosroles")
public class UsuarioRolVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idur;
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="idrol")
	private RolVO rol;
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="idusuario")
	private UsuarioVO usuario;

}
