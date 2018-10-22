package edu.eci.pdsw.test;

import com.google.inject.Inject;
//import edu.eci.pdsw.sampleprj.dao.PersistenceException;
import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquilerFactory;
import org.apache.ibatis.session.SqlSession;

import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.*;

import org.quicktheories.core.Gen;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.Generate.*;
import static org.quicktheories.generators.SourceDSL.*;
//import java.sql.Connection;
//import java.sql.DriverManager;

public class ServiciosAlquilerTest{

    @Inject
    private SqlSession sqlSession;
    @Inject
    ServiciosAlquiler serviciosAlquiler;

    public ServiciosAlquilerTest() {
        serviciosAlquiler = ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
    }

    @Before
    public void setUp() {
    }

    @Test
    public void emptyDB() {
        qt().forAll(longs().from(1).upTo(1000)).check((documento) -> {
            boolean r = true;
            try {
                Cliente cliente = serviciosAlquiler.consultarCliente(documento);
            } catch(ExcepcionServiciosAlquiler | NullPointerException  e) {
                r = true;
            } catch(IndexOutOfBoundsException e) {
                r = true;
            }
            return r;
        });
    }
    
    
    @Test
    public void insertaryConsultarCliente() {
    	
    	 qt().forAll(ServiciosAlquilerGenerator.clientes()).check( cliente -> {
    		boolean clienteAgregado = false;
    		Optional<Cliente> client = Optional.empty();
    		serviciosAlquiler = ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
         	try {
         		serviciosAlquiler.registrarCliente(cliente);         		
         	} 
         	 catch(ExcepcionServiciosAlquiler e){
         		 System.out.println(e.getMessage());
 			}
         	try {
         		client = Optional.of(serviciosAlquiler.consultarCliente(cliente.getDocumento()));
         	} 
         	 catch(ExcepcionServiciosAlquiler e){
         		 System.out.println(e.getMessage());
 			}
         	if (client.isPresent()) {
         		long i = client.get().getDocumento();
         		long e = cliente.getDocumento();
         		clienteAgregado= (i==e);
         	}
			return clienteAgregado;
    	 });
    	
    }
    
    @Test
    public void insertaryConsultarItem() {
    	qt().forAll(ServiciosAlquilerGenerator.items()).check( item -> {
    		boolean ItemAgregado = false;
    		Optional<Item> itemOpt = Optional.empty();
    		serviciosAlquiler = ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
         	try {
         		serviciosAlquiler.registrarItem(item);
         	} 
         	 catch(ExcepcionServiciosAlquiler e){
         		 System.out.println(e.getMessage());
 			}
         	try {
         		itemOpt = Optional.of(serviciosAlquiler.consultarItem(item.getId()));
         	} 
         	 catch(ExcepcionServiciosAlquiler e){
         		 System.out.println(e.getMessage());
 			}
         	if (itemOpt.isPresent()) {
         		int i = itemOpt.get().getId();
         		int e = item.getId();
         		ItemAgregado= (i==e);
         	}
			return ItemAgregado; 
    	 });
    }
  
    
    private static Gen<Integer> dias(){
		return integers().between(1,50);
	}
    
    /* public void registrarAlquilerCliente(Date date, long docu, Item item, int numdias)*/
    @Test
    public void registrarAlquilerCliente() {
    	qt().forAll(dates().withMilliseconds(0),ServiciosAlquilerGenerator.clientes(),ServiciosAlquilerGenerator.items(),dias()).check( (fecha,cliente,item,numDias) -> {
    		boolean registroAlquilado = false;
    		java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
    		Optional<List<ItemRentado>> rentados = Optional.empty();
    		try {
    			serviciosAlquiler.registrarItem(item);
    			serviciosAlquiler.registrarCliente(cliente);
    			serviciosAlquiler.registrarAlquilerCliente(sqlDate, cliente.getDocumento(), item, numDias);
    			Cliente client = serviciosAlquiler.consultarCliente(cliente.getDocumento());
    			rentados =  Optional.of(serviciosAlquiler.consultarItemsCliente(client.getDocumento()));
    			
    		} catch (ExcepcionServiciosAlquiler e) {
                System.out.println(e.getMessage());
                registroAlquilado = false;
            }
    		if (rentados.isPresent() && !rentados.get().isEmpty()) {
    			registroAlquilado = true;
         	}
    		return registroAlquilado;
    		});
    	}
 
}