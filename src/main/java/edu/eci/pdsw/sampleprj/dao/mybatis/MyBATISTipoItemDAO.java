package edu.eci.pdsw.sampleprj.dao.mybatis;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

import com.google.inject.Inject;

import edu.eci.pdsw.sampleprj.dao.TipoItemDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.TipoItemMapper;
import edu.eci.pdsw.samples.entities.TipoItem;

public class MyBATISTipoItemDAO implements TipoItemDAO{
	
	@Inject
	  private TipoItemMapper tipoItemMapper;   
	
	@Override
	  public List<TipoItem> loadTiposItem() throws PersistenceException {
	  try{
	      return tipoItemMapper.getTiposItems();
	  }
	  catch(org.apache.ibatis.exceptions.PersistenceException e){
	      throw new PersistenceException("Error al consultar los tipo item ",e);
	  }

}
	
	
	
}
