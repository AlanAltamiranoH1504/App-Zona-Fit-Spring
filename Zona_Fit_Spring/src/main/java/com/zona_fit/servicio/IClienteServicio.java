package com.zona_fit.servicio;

import com.zona_fit.modelo.Cliente;

import java.util.List;

public interface IClienteServicio {
    //Definimos los metodo para el select, selectByID, insert, update y dele
    public abstract List<Cliente> select();
    public abstract Cliente selectByID(Integer idCliente);
    public abstract void guardarCliente(Cliente cliente); //Hace Insert y Update
    public abstract void delete(Cliente cliente);
}
