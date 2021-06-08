package com.dawes.modelo;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comentarios")
public class ComentarioVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idcomentario;
	private String asunto;
	private String contenido;
	@DateTimeFormat(pattern = "yyy-MM-dd")
	private LocalDate fecha;
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="idusuario")
	private UsuarioVO usuario;
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="idpublicacion")
	private PublicacionVO publicacion;
}
