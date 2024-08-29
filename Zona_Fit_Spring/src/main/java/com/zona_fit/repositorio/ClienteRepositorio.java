package com.zona_fit.repositorio;

import com.zona_fit.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

//Extendemos de otra interface para tener los metodos basicos para JPA
//Dentro de la interfaz que extendemos, indicamos el tipo de dato con el que trabajamoos, en este caso tipo Cliente,
//y el tipo de la llave PK
public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {
}
