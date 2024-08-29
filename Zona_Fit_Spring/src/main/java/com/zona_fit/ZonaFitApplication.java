package com.zona_fit;

import com.zona_fit.modelo.Cliente;
import com.zona_fit.servicio.IClienteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

//Implememtamos interfaz para que el proyecto se ejecute como un proyecto de consola
@SpringBootApplication
public class ZonaFitApplication implements CommandLineRunner {
	//Inyectamos un objeto de la clase IClienteServicioImpl
	@Autowired
	private IClienteServicio clienteServicio;

	//Definimos variable Logger para los mensajes en consola
	private static final Logger logger = LoggerFactory.getLogger(ZonaFitApplication.class);

	public static void main(String[] args) {
		logger.info("Iniciando la aplicacion");
		//Levantamos la fabrica de spring
		SpringApplication.run(ZonaFitApplication.class, args);
		logger.info("Terminando la aplicacion");
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		Boolean salir = false;

		do {
			logger.info("\n\t*** APLICACION ZONA FIT - SPRING ***");
			logger.info("1. Listar clientes");
			logger.info("2. Buscar cliente por ID");
			logger.info("3. Agregar un cliente");
			logger.info("4. Actualziar un cliente");
			logger.info("5. Eliminar un cliente");
			logger.info("6. Salir de la aplicacion");
			logger.info("Seleccion la opcion que deseas realizar: ");

			var opcion = Integer.parseInt(scanner.nextLine());

			switch (opcion){
				case 1:
					logger.info("\n*** Listar Clientes");
					//Llamamos al metodo listarClientes
					listarClientes(clienteServicio);
					System.out.println();
					break;

				case 2:
					logger.info("\n*** Buscar Cliente por ID ***");
					//Llamamos al metodo buscarPorID
					buscarPorID(clienteServicio, scanner);
					System.out.println();
					break;

				case 3:
					logger.info("\n*** Aregar un Cliente ***");
					//Llamamos al metodo agregarCliente
					agregarCliente(clienteServicio, scanner);
					System.out.println();
					break;

				case 4:
					logger.info("\n*** Actualziar un Cliente ***");
					//Llamamos al metodo que lista los clientes
					logger.info("» Los clientes actuales son los siguientes: ");
					listarClientes(clienteServicio);
					//Llamamos al metodo que actualiza un cliente
					actualizarCliente(clienteServicio, scanner);
					System.out.println();
					break;

				case 5:
					logger.info("\n*** Eliminar un Cliente ***");
					//Llamamos al metodo que lista los clientes
					logger.info("» Los clientes actuales son los siguientes: ");
					listarClientes(clienteServicio);
					//Llamamos al metodo que elimina un cliente de la DB
					eliminarCliente(clienteServicio, scanner);
					System.out.println();
					break;

				case 6:
					logger.info("Hasta luego... :)");
					salir = true;
					break;

				default:
					logger.info("La opcion que ingresaste es incorrecta...");
					break;
			}
		}while (!salir);
	}

	//Metodo que lista los clientes de la DB
	public void listarClientes(IClienteServicio clienteServicio){
		//Creamos arrayList que va a contener los clientes de la DB
		List<Cliente> clientes = clienteServicio.select();

		//Iteramos el array de los clientes
		for (var cliente: clientes){
			System.out.println("cliente = " + cliente);
		}
	}

	//Metodo que busca un cliente por el id
	public void buscarPorID(IClienteServicio clienteServicio, Scanner scanner){
		logger.info("→ Ingresa el ID del cliente que quieres buscar: ");
		var ID = Integer.parseInt(scanner.nextLine());

		//Llamamos al getByID
		var clienteEncontrado = clienteServicio.selectByID(ID);

		//Mostramos el cliente
		System.out.println("Cliente = " + clienteEncontrado);
	}

	//Metodo que agrega un cliente a la DB
	public void agregarCliente(IClienteServicio clienteServicio, Scanner scanner){
		//Generamos atributos del nuevo cliente
		logger.info("→ Ingresa el nombre del Cliente: ");
		var nombre = scanner.nextLine();
		logger.info("→ Ingresa los apellido del Cliente: ");
		var apellido = scanner.nextLine();
		logger.info("→ Generando numero de membresia...");
		var aleatorio = new Random();
		var membresia = aleatorio.nextInt(0, 99999);

		//Generamos nuevo objeto de tipo Cliente con los atributos anteriores
		Cliente clienteInsert = new Cliente(nombre, apellido, membresia);

		//Ejecutamos el metodo guardarCliente
		clienteServicio.guardarCliente(clienteInsert);
		logger.info("→ Cliente guardado en la DB");
	}

	//Metodo que actualiza un cliente en la DB
	public void actualizarCliente(IClienteServicio clienteServicio, Scanner scanner){
		logger.info("→ Ingresa el ID del cliente que deseas actualizar: ");
		var ID = Integer.parseInt(scanner.nextLine());
		logger.info("→ Ingresa el nuevo nombre del Cliente: ");
		var nombre = scanner.nextLine();
		logger.info("→ Ingresa los nuevos apellidos del Cliente: ");
		var apellidos = scanner.nextLine();
		logger.info("→ Generando nuevo numero de membresia....");
		var aleatorio = new Random();
		var membresia = aleatorio.nextInt(0, 99999);

		//Creamos nuevo objeto de tipo Cliente con los atributos anteriores
		Cliente cliente = new Cliente(ID, nombre, apellidos, membresia);

		//Llamamos al metodo guardarCliente
		clienteServicio.guardarCliente(cliente);
		logger.info("→ Cliente actualizado en la DB");
	}

	//Metodo que elimina un cliente de la DB
	public void eliminarCliente(IClienteServicio clienteServicio, Scanner scanner){
		logger.info("→ Ingresa el ID del cliente que quieres eliminar: ");
		var ID = Integer.parseInt(scanner.nextLine());

		//Creamos objeto con el ID dado
		Cliente cliente = new Cliente(ID);

		//Llamamos al metodo delete
		clienteServicio.delete(cliente);
		logger.info("→  Cliente eliminado de la DB");
	}
}
