package edu.eci.pdsw.sampleprj.dao;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;

public interface ClienteDAO {
	
	public Cliente load(int id) throws PersistenceException;
	
	public void save(Cliente it) throws PersistenceException;

	public List<Cliente> loadConsultarClientes() throws PersistenceException;
	
	public void agregarItemRentadoACliente(Date date, long docu, Item item, int numdias) throws PersistenceException;

	public void updateVetarCliente (long docu, boolean estado) throws PersistenceException;
	
}
