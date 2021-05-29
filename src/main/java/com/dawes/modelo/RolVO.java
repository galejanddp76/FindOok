package com.dawes.modelo;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class RolVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idrol;
	private String nombrerol;
}
