package com.dawes.modelo;



import java.time.LocalDate;

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
@Table(name = "publicaciones")
public class PublicacionVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idpublicacion;
	private String titulo;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechacreacion;
	private double precio;
	private String descripcion;
	private String imagenpublicacion;
	@ManyToOne
	@JoinColumn(name="idusuario")
	private UsuarioVO usuario;

}
