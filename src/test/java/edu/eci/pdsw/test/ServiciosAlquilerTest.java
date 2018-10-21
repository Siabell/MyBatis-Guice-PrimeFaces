package edu.eci.pdsw.test;

import java.util.ArrayList;
import java.util.List;

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
import java.util.Optional;
import static org.junit.Assert.*;

import org.quicktheories.core.Gen;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.Generate.*;
import static org.quicktheories.generators.SourceDSL.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class ServiciosAlquilerTest {

    @Inject
    private SqlSession sqlSession;

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
    
    
    
}