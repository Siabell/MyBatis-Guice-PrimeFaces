package edu.eci.pdsw.sampleprj.dao;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

import edu.eci.pdsw.samples.entities.TipoItem;

public interface TipoItemDAO {

	public List<TipoItem> loadTiposItem() throws PersistenceException;

}
