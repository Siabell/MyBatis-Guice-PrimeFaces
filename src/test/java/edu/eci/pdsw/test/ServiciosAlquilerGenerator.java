package edu.eci.pdsw.test;


import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.TipoItem;

import org.quicktheories.core.Gen;
import org.quicktheories.generators.Generate;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.Generate.*;
import static org.quicktheories.generators.SourceDSL.*;

import java.util.Date;

public class ServiciosAlquilerGenerator {
	

	//-------------------------------------------------------------------------------------------------------------
	// Cliente
	
	/**
	 * private String nombre;
    private long documento;
    private String telefono;
    private String direccion;
    private String email;
      public Cliente(String nombre, long documento, String telefono, String direccion, String email) {
	 */
	
	private static Gen<String> nombres(){
		return strings().basicLatinAlphabet().ofLengthBetween(1, 10);
	}
	
	
	private static Gen<String> direccions(){
		return strings().basicLatinAlphabet().ofLengthBetween(10, 20);
	}
	
	private static Gen<Long> documentos(){
		return (longs()).between(100,5000);
	}

	private static Gen<String> telefonos(){
		return strings().numericBetween(1000000, 9999999);
	}
	
	private static Gen<String> emails(){
		return strings().basicLatinAlphabet().ofLengthBetween(1, 10);
	}
	
		
	public static Gen<Cliente> clientes(){
		return nombres().zip(documentos(),telefonos(),direccions(),emails(), (nombre,documento,telefono, direccion,email)-> new Cliente(nombre,documento,telefono, direccion,email));
	}
	
	
	
	//-------------------------------------------------------------------------------------------------------------
	// Tipo Item
	
	private static Gen<String> tiposIt(){
		return strings().basicLatinAlphabet().ofLengthBetween(1, 6);
	}
	
	private static Gen<Integer> tiposId(){
		return integers().between(1,100);
	}
	
	public static Gen<TipoItem> genTipo(){
		return tiposIt().zip(tiposId(), (tipo,num)-> new TipoItem(num,tipo));
		
	}
	
	
	//-------------------------------------------------------------------------------------------------------------
	// Item
	
	/**
	private TipoItem tipo;
    private int id;
    private String nombre;
    private String descripcion;
    private Date fechaLanzamiento;
    private long tarifaxDia;
    private String formatoRenta;
    private String genero;
	*/
	private static Gen<String> nombre(){
		return strings().basicLatinAlphabet().ofLengthBetween(1, 10);
	}
	
	private static Gen<Integer> idItem(){
		return integers().between(1,10000000);
	}
	private static Gen<Long> tarifas(){
		return longs().between(1, 10000000);
	}
	
	private static Gen<Date> fechasdeLanzamiento() {
	    return dates().withMilliseconds(0);
	}
	
	public static Gen<Item> items(){
		return genTipo().zip(idItem(),nombre(),fechasdeLanzamiento(),tarifas(), (tipo,id,nombre,fechasLanzamiento,tarifa) -> new Item(tipo,id,nombre,"",fechasLanzamiento,tarifa,"",""));
	}

}
