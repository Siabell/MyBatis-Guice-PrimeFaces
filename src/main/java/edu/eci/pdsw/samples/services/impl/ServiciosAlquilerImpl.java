package edu.eci.pdsw.samples.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.pdsw.sampleprj.dao.ClienteDAO;
import edu.eci.pdsw.sampleprj.dao.ItemDAO;
//import edu.eci.pdsw.sampleprj.dao.PersistenceException;
import edu.eci.pdsw.sampleprj.dao.TipoItemDAO;
import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.entities.TipoItem;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

@Singleton
public class ServiciosAlquilerImpl implements ServiciosAlquiler {

   @Inject
   private ItemDAO itemDAO;
   @Inject
   private ClienteDAO clienteDAO;
   @Inject
   private TipoItemDAO tipoItemDAO;

   @Override
   public int valorMultaRetrasoxDia(int itemId) {
       return 1000;
   }
   
   @Override
   public Cliente consultarCliente(long docu) throws ExcepcionServiciosAlquiler {
	   try {
		   return clienteDAO.load((int)docu);    	   
       }
       catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al cargar el cliente "+(int)docu,ex);
       }
   }

   @Override
   public List<ItemRentado> consultarItemsCliente(long idcliente) throws ExcepcionServiciosAlquiler {
       try {
    	   
    	   return clienteDAO.loadItemsCliente((int) idcliente);
    	   
       }
       catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al cargar el cliente "+(int)idcliente,ex);
       }
       
   }

   @Override
   public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
	   try {
    	   return clienteDAO.loadConsultarClientes();
    	   
       }
       catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar los clientes ",ex);
       }
   }

   @Override
   public Item consultarItem(int id) throws ExcepcionServiciosAlquiler {
       try {
           return itemDAO.load(id);
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el item "+id,ex);
       }
   }

   @Override
   public List<Item> consultarItemsDisponibles() {
	   return itemDAO.loadItemsDisponibles();
       
   }

   /**
    * 
    */
   @Override
   public long consultarMultaAlquiler(int iditem, Date fechaDevolucion) throws ExcepcionServiciosAlquiler {
       try {
    	   Calendar c=Calendar.getInstance();
    	   c.setTime(fechaDevolucion);
    	   Calendar today = Calendar.getInstance();
    	   today.set(Calendar.HOUR_OF_DAY, 0);
    	   int d=0;
    	   if(fechaDevolucion.after(today.getTime())) {
    		   d = (int) ChronoUnit.DAYS.between(c.toInstant(),today.toInstant());
    	   }
    	   return itemDAO.load(iditem).getTarifaxDia()*d;
       }
       catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("No existe el item ",ex);
       }
       
   }

   @Override
   public TipoItem consultarTipoItem(int id) throws ExcepcionServiciosAlquiler {
	   try {
           return itemDAO.load(id).getTipo();
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("No existe el item "+id,ex);
       }
   }

   @Override
   public List<TipoItem> consultarTiposItem() throws ExcepcionServiciosAlquiler {
	   try {
           return tipoItemDAO.loadTiposItem();
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("No existe ningun tipo item ",ex);
       }
   }

   @Override
   public void registrarAlquilerCliente(Date date, long docu, Item item, int numdias) throws ExcepcionServiciosAlquiler {
	   try {
		   
           clienteDAO.agregarItemRentadoACliente(date, docu,  item, numdias);
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("No se pudo registrar el alquiler",ex);
       }
   }

   @Override
   public void registrarCliente(Cliente c) throws ExcepcionServiciosAlquiler {
	   try {
           clienteDAO.save(c);
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("No se pudo registrar cliente",ex);
       }
   }

   @Override
   public long consultarCostoAlquiler(int iditem, int numdias) throws ExcepcionServiciosAlquiler {
	   try {
		   if (numdias > 0) {
			   return (long) itemDAO.load(iditem).getTarifaxDia()*numdias;
		   }
		   else {
			   throw new ExcepcionServiciosAlquiler("El numero de dias a consultar son menores a 1");   
		   }
           
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("No se pudo consultar el costo de alquiler",ex);
       }
   }

   @Override
   public void actualizarTarifaItem(int id, long tarifa) throws ExcepcionServiciosAlquiler {
	   try {
           itemDAO.actualizarTarifaItem(id, tarifa);
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("No se pudo actualizar la tarifa del item",ex);
       }
   }
   
   @Override
   public void registrarItem(Item i) throws ExcepcionServiciosAlquiler {
	   try {
           itemDAO.save(i);
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("No se pudo registrar el item",ex);
       } //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public void vetarCliente(long docu, boolean estado) throws ExcepcionServiciosAlquiler {  
	   try {
           clienteDAO.updateVetarCliente(docu, estado);
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("No se pudo actualizar el estado de vetado del cliente",ex);
       } //To change body of generated methods, choose Tools | Templates.
   }
}