package com.zona_fit.servicio;

import com.zona_fit.modelo.Cliente;
import com.zona_fit.repositorio.ClienteRepositorio;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Agregamos anotacion service para que la clase sea parte de la fabrica de objetos de spring
@Service

//Implementamos la interfaz IClienteServicio
public class ClienteServicioImpl implements IClienteServicio{

    //Agregamos inyeccion de la interfaz ClienteRepositorio
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Override
    public List<Cliente> select() {
        //Definimos arrayList que contiene los clientes de la DB
        List<Cliente> clientes = clienteRepositorio.findAll();

        //Regresamos la lista
        return clientes;
    }

    @Override
    public Cliente selectByID(Integer idCliente) {
        //Definimos varible que contiene el cliente encontrado
        Cliente clienteEncontrado = clienteRepositorio.findById(idCliente).orElse(null);

        return clienteEncontrado;
    }

    @Override
    public void guardarCliente(Cliente cliente) {
        //Guardamos el objecto que recibimos. Hace insert y update
        clienteRepositorio.save(cliente);
    }

    @Override
    public void delete(Cliente cliente) {
        //Guardamos el objeto que recibimos
        clienteRepositorio.delete(cliente);
    }
}
