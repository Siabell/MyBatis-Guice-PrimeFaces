package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.ItemRentado;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author 2106913
 */
public interface ClienteMapper {
    
    public Cliente consultarCliente( @Param("idcli") int id); 
    
    
    
    
    
    
    /**
     * Registrar un nuevo item rentado asociado al cliente identificado
     * con 'idc' y relacionado con el item identificado con 'idi'
     * @param id
     * @param idit
     * @param fechainicio
     * @param fechafin 
     */
       
    public void agregarItemRentadoACliente(  @Param("idc")int id, 
    		@Param("idi") int idit, 
    		@Param("fechainicio") Date fechainicio,
    		@Param("fechafin") Date fechafin);

    /**
     * Consultar todos los clientes
     * @return 
     */
    public List<Cliente> consultarClientes();
    
    /**
     * Consultar todos los clientes
     * @return 
     */
    public List<Cliente> consultarTClientes();
    
    /**
     * Insertar todos los clientes
     * @param cliente
     */
    public  void insertarCliente(@Param("cli") Cliente cli) ;
    
    
    /**
     * Consultar item rentados
     */
    public ItemRentado consultarItemRentado(@Param("idr") int iditem);
    
    /**
     * Consulta los items rentados de los clientes
     */
    public List<ItemRentado> consultarItemsCliente(@Param("idc") int idcli);
    
    
   public void vetarCliente (@Param("idc") int docu, @Param("estado") boolean estado);
    
}
