package edu.eci.pdsw.test;

import com.google.inject.Inject;
//import edu.eci.pdsw.sampleprj.dao.PersistenceException;
import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.entities.TipoItem;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquilerFactory;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.quicktheories.core.Gen;
import org.quicktheories.generators.Generate;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.Generate.*;
import static org.quicktheories.generators.SourceDSL.*;

import java.util.ArrayList;
import java.util.Date;

public class ClienteGenerator {
	
	
	/**
	 * private String nombre;
    private long documento;
    private String telefono;
    private String direccion;
    private String email;
    private boolean vetado;
    private ArrayList<ItemRentado> rentados; 
	 */
	
	private static Gen<String> nombres(){
		return strings().basicLatinAlphabet().ofLengthBetween(1, 10);
	}
	
	private static Gen<Long> documentos(){
		return (longs()).between(100,50000);
	}

	private static Gen<Integer> telefonos(){
		return integers().between(100,100000);
	}
	
	private static Gen<String> emails(){
		return strings().basicLatinAlphabet().ofLengthBetween(1, 10);
	}
	
	private static Gen<Boolean> vetados(){
		return Generate.booleans();
	}
	
		
	
	
	
	
	//-------------------------------------------------------------------------------------------------------------
	// Tipo Item
	private static Gen<String> tiposIt(){
		return strings().basicLatinAlphabet().ofLengthBetween(1, 6);
	}
	
	private static Gen<Integer> tiposId(){
		return integers().between(1,100);
	}
	
	private Gen<TipoItem> genTipo(){
		
		return tiposIt().zip(tiposId(), (tipo,num)-> new TipoItem(num,tipo));
		
	}
	
	
	//-------------------------------------------------------------------------------------------------------------
	// Item
	
	/* private TipoItem tipo;
    private int id;
    private String nombre;
    private String descripcion;
    private Date fechaLanzamiento;
    private long tarifaxDia;
    private String formatoRenta;
    private String genero;*/
	
	private static Gen<String> nombre(){
		return strings().basicLatinAlphabet().ofLengthBetween(1, 10);
	}
	private static Gen<String> descripcion(){
		return strings().basicLatinAlphabet().ofLengthBetween(1,20);
	}
	private static Gen<String> formatoRenta(){
		return strings().basicLatinAlphabet().ofLengthBetween(1, 5);
	}
	private static Gen<String> genero(){
		return strings().basicLatinAlphabet().ofLengthBetween(1, 10);
	}
	
	private static Gen<Integer> idItem(){
		return integers().between(1,10000000);
	}
	private static Gen<Integer> tarifas(){
		return integers().between(1,100);
	}
	/*
		public Gen<Date> withMilliseconds(long millisecondsFromEpoch) {
		    lowerBoundGEQZero(millisecondsFromEpoch);
		    return Dates.withMilliSeconds(millisecondsFromEpoch);
		  }
		private void lowerBoundGEQZero(long milliSecondsFromEpoch) {
		    ArgumentAssertions.checkArguments(milliSecondsFromEpoch >= 0,
		        "A negative long (%s) is not an accepted number of milliseconds",
		        milliSecondsFromEpoch);
		  }
	
	private Gen<Item> items(){
		return genTipo().zip(idItem,nombre,descripcion,, mapping)
	}*/

}
