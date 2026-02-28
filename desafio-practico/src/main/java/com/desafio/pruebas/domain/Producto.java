package com.desafio.pruebas.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Esto le dice a Spring que esta clase es una tabla en la DB
@Data // Magia de Lombok: Crea Getters, Setters, toString, etc.
@AllArgsConstructor // Crea un constructor con todos los campos
@NoArgsConstructor // Crea un constructor vacío (obligatorio para JPA)
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Nuestra llave primaria automática

    private String nombre;
    private Double precio;
}
