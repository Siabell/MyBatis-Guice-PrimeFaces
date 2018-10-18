package edu.eci.pdsw.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.pdsw.sampleprj.dao.ClienteDAO;
//import edu.eci.pdsw.sampleprj.dao.PersistenceException;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.pdsw.samples.entities.Cliente;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

public class MyBATISClienteDAO implements ClienteDAO{

  @Inject
  private ClienteMapper clienteMapper;    

  @Override
  public void save (Cliente cli ) throws PersistenceException{
  try{
	  clienteMapper.insertarCliente(cli);
  }
  catch(org.apache.ibatis.exceptions.PersistenceException e){
      throw new PersistenceException("Error al registrar el cliente "+cli.toString(),e);
  }        

  }

  @Override
  public Cliente load(int id) throws PersistenceException {
  try{
	  
      return clienteMapper.consultarCliente(id);
  }
  catch(org.apache.ibatis.exceptions.PersistenceException e){
      throw new PersistenceException("Error al consultar el cliente "+id,e);
  }
  }
  
  @Override
  public List<ItemRentado> loadItemsCliente (int id) throws PersistenceException {
  try{
      return clienteMapper.consultarItemsCliente(id);
  }
  catch(org.apache.ibatis.exceptions.PersistenceException e){
      throw new PersistenceException("Error al consultar el cliente "+id,e);
  }

  }
  
  @Override
  public List<Cliente> loadConsultarClientes() throws PersistenceException {
	  try{
	      return clienteMapper.consultarClientes();
	  }
	  catch(org.apache.ibatis.exceptions.PersistenceException e){
	      throw new PersistenceException("Error al consultar los clientes ",e);
	  }
  }
  
  
  @Override
  public void agregarItemRentadoACliente(Date date, long docu, Item item, int numdias) throws PersistenceException{
	  try {
		    Calendar calendar = Calendar.getInstance();
		 
		    calendar.setTime(date); // Configuramos la fecha que se recibe
		  
		    calendar.add(Calendar.DAY_OF_YEAR, numdias);
		    java.util.Date dev = calendar.getTime();
		    
		    java.sql.Date ss = new java.sql.Date(date.getTime());
		    
		    java.sql.Date sd = new java.sql.Date(dev.getTime());
		    
		    
		    clienteMapper.agregarItemRentadoACliente((int) docu, item.getId(),ss,sd);
		  
	  }
	  catch(org.apache.ibatis.exceptions.PersistenceException e) {
		  throw new PersistenceException("Error al registrar el alquiler del item",e);
		  
	  }
  }
	  
	@Override
	public void updateVetarCliente (long docu, boolean estado) throws PersistenceException{
		 try {
			 clienteMapper.vetarCliente((int) docu, estado);
		 }
		 catch(org.apache.ibatis.exceptions.PersistenceException e) {
			  throw new PersistenceException("Error al actualizar el estado de vetado del cliente",e);
			  
		  }
			
	}
  

  }