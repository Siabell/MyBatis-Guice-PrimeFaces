package edu.eci.pdsw.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;


@ManagedBean(name = "alquilerItemsBean")
@RequestScoped

public class AlquilerItemsBean extends BasePageBean {
	
	@ManagedProperty(value = "#{param.docu}")
	private int docu;
	
	public int valor=0;
	public boolean disponible=true;
	@Inject
	private ServiciosAlquiler serviciosAlquiler;
	public boolean valido =true;
	
	public List<Cliente> consultarClientes() throws Exception {
		try {
			return serviciosAlquiler.consultarClientes();
		}catch (ExcepcionServiciosAlquiler ex) {
			throw ex;
		}
	}
	
	
	public void registrarCliente(String nombre, long documento, String telefono, String direccion, String email,boolean vetado)  throws Exception {
		try {
			Cliente cliente = new Cliente(nombre,documento,telefono,direccion,email);
			cliente.setVetado(vetado);
			serviciosAlquiler.registrarCliente(cliente);
			
			
		}catch (ExcepcionServiciosAlquiler ex) {
			
			throw ex;
		}
	}
	
	
	public List<ItemRentado> consultarItemsCliente() throws Exception {
		try {
			 return serviciosAlquiler.consultarItemsCliente((long) docu);
		}
		catch (ExcepcionServiciosAlquiler ex) {
			throw ex;
		}
		
	}

	public int getDocu(){
		return this.docu;
	}
	public void setDocu(int doc){
		System.out.println(valido);
		if(valido) {
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+doc);
			this.docu=doc;
		}
	}
	
	public void turnValido(int bol) {
		if(bol==1) {
			valido =true;
		}
		else{valido=false;}
		System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz "+valido);
	}
	
	public boolean getDisponible(){
		return this.disponible;
	}
	public void setDisponible(boolean disp){
		this.disponible=disp;
	}

	public void registrarAlquiler(int itemId, int numdias) throws ExcepcionServiciosAlquiler{
	try{
		
		
		java.util.Date d = new java.util.Date();
		java.sql.Date sd = new java.sql.Date(d.getTime());
		serviciosAlquiler.registrarAlquilerCliente(sd, (long)docu, serviciosAlquiler.consultarItem(itemId), numdias);
		disponible =true;
	}
	catch (ExcepcionServiciosAlquiler ex){
		
		disponible=false;
		throw ex;
	}
	}
	
	public int consultarMulta(int iditem, Date fechaDev) throws ExcepcionServiciosAlquiler {
		
		try {
			
			return (int) serviciosAlquiler.consultarMultaAlquiler(iditem,(java.sql.Date) fechaDev);
		} catch (ExcepcionServiciosAlquiler e) {
			
			throw e;
		}
	}
	
	public void consultarValorRenta(int idItem,int cantDias) throws ExcepcionServiciosAlquiler {
		try {
			
			valor = (int) serviciosAlquiler.consultarCostoAlquiler(idItem, cantDias);
			
		
		} catch (ExcepcionServiciosAlquiler e) {
			
			throw e;
		}
		
	}
	
	public void setValor(int a) {valor =a;}
	public int getValor() {return valor;}


}
