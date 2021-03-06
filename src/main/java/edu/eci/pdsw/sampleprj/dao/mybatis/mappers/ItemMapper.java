package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;


import edu.eci.pdsw.samples.entities.Item;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author 2106913
 */
public interface ItemMapper {
    
    
    public List<Item> consultarItems();        
    
    public Item consultarItem( @Param("it") int id);
    
    public void insertarItem(@Param("it") Item it);
    
    public List<Item> consultarItemsDisponibles();
    
    public void actualizarTarifaItem( @Param("idt") int id, @Param("tarifa") int tarifa);
    
    

        
}
