package com.zona_fit.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

//Definimos que la clase sea reconocida como una clase Entity
@Entity
public class Cliente {
    //Definimos atributos de la clase
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellido;
    private Integer membresia;

    //Constructor vacio
    public Cliente(){

    }
    //Constructor que recibe solo el id
    public Cliente(Integer id){
        this.id = id;
    }
    //Constructor que recibe todos menos el id
    public Cliente(String nombre, String apellido, Integer membresia){
        this.nombre = nombre;
        this.apellido = apellido;
        this.membresia = membresia;
    }
    //Constructor que recibe todos los argumentos
    public Cliente(Integer id, String nombre, String apellido, Integer membresia){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.membresia = membresia;
    }

    //Metodos GET y SET de los atributos
    public Integer getId(){
        return this.id;
    }
    public void setId(Integer id){
        this.id = id;
    }

    public String getNombre(){
        return this.nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getApellido(){
        return this.apellido;
    }
    public void setApellido(String apellido){
        this.apellido = apellido;
    }

    public Integer getMembresia(){
        return this.membresia;
    }
    public void setMembresia(Integer membresia){
        this.membresia = membresia;
    }

    //Sobreescribimos metodo toString
    public String toString(){
        return "Id: " + this.id + ", Nombre: " + this.nombre + ", Apellido: " + this.apellido + ", Membresia: " + this.membresia;
    }

    //Sobreescribimos metodo hashcode y equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) && Objects.equals(nombre, cliente.nombre) && Objects.equals(apellido, cliente.apellido) && Objects.equals(membresia, cliente.membresia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, membresia);
    }
}
