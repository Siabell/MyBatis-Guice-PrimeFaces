package edu.eci.pdsw.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.pdsw.sampleprj.dao.ItemDAO;
import org.apache.ibatis.exceptions.PersistenceException;
//import edu.eci.pdsw.sampleprj.dao.PersistenceException;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.pdsw.samples.entities.TipoItem;
import java.sql.SQLException;
import java.util.List;

public class MyBATISItemDAO implements ItemDAO{

  @Inject
  private ItemMapper itemMapper;    

  @Override
  public void save(Item it) throws PersistenceException{
  try{
      itemMapper.insertarItem(it);
  }
  catch(org.apache.ibatis.exceptions.PersistenceException e){
      throw new PersistenceException("Error al registrar el item "+it.toString(),e);
  }        

  }

  @Override
  public Item load(int id) throws PersistenceException {
  try{
      return itemMapper.consultarItem(id);
  }
  catch(org.apache.ibatis.exceptions.PersistenceException e){
      throw new PersistenceException("Error al consultar el item "+id,e);
  }

  }
  
  @Override
  public List<Item> loadItemsDisponibles()  {

  return itemMapper.consultarItemsDisponibles();

  /*
  catch(org.apache.ibatis.exceptions.PersistenceException e){
      throw new PersistenceException("Error al consultar el item ", e);
  }*/

  }

  
  @Override
  public void actualizarTarifaItem (int id, long tarifa) throws PersistenceException {
  try{
      itemMapper.actualizarTarifaItem(id, (int) tarifa);
  }
  catch(org.apache.ibatis.exceptions.PersistenceException e){
      throw new PersistenceException("Error al consultar el item "+id,e);
  }

  }
  
  
  
  
  }
