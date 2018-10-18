package edu.eci.pdsw.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;


@ManagedBean(name = "alquilerItemsBean")
@SessionScoped

public class AlquilerItemsBean extends BasePageBean {
	
	
	@Inject
	private ServiciosAlquiler serviciosAlquiler;
	
	public List<Cliente> consultarClientes() throws Exception {
		try {
			return serviciosAlquiler.consultarClientes();
		}catch (ExcepcionServiciosAlquiler ex) {
			System.out.println("lloro mutyyyyyyyyyy fuerte");
			throw ex;
		}
	}
	
	public void registrarCliente(String nombre, long documento, String telefono, String direccion, String email,boolean vetado)  throws Exception {
		try {
			Cliente cliente = new Cliente(nombre,documento,telefono,direccion,email);
			cliente.setVetado(vetado);
			serviciosAlquiler.registrarCliente(cliente);
			
			
		}catch (ExcepcionServiciosAlquiler ex) {
			System.out.println("lloro mutyyyyyyyyyy fuerte");
			throw ex;
		}
	}


}
