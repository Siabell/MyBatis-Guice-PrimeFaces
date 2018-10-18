/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.samples.services.client;


import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.*;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.TipoItem;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author hcadavid
 */
public class MyBatisExample {

    /**
     * Método que construye una fábrica de sesiones de MyBatis a partir del
     * archivo de configuración ubicado en src/main/resources
     *
     * @return instancia de SQLSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        return sqlSessionFactory;
    }

    /**
     * Programa principal de ejempo de uso de MyBATIS
     * @param args
     * @throws SQLException 
     */
    public static void main(String args[]) throws SQLException {
        SqlSessionFactory sessionfact = getSqlSessionFactory();

        SqlSession sqlss = sessionfact.openSession();
        ClienteMapper cm=sqlss.getMapper(ClienteMapper.class);
        System.out.println("Consultar cliente-----------------------------------------");
        System.out.println(cm.consultarCliente(101847).toString());
        System.out.println("consultarItemsCliente-----------------------------------------");
        System.out.println(cm.consultarItemsCliente(22).toString());
        System.out.println("consultarClientes-----------------------------------------");
        System.out.println(cm.consultarClientes().toString());
        System.out.println("agregar item rentado------------------------------------------- ");	   
		//cm.agregarItemRentadoACliente(317658,22,new Date(2018,01,05),new Date(2018,01,15));
        System.out.println("consultar item rentado------------------------------------------- ");	   
        System.out.println(cm.consultarItemRentado(5).toString());
        System.out.println("insertar cliente------------------------------------------- ");	   
        System.out.println("consultar todos los clientes------------------------------------------- ");	   
        System.out.println(cm.consultarTClientes());
        System.out.println("update cliente vetado------------------------------------------- ");	   
        
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        
        ItemMapper im = sqlss.getMapper(ItemMapper.class);
        System.out.println("insertar item------------------------------------------- ");	 
        //TipoItem tipo = new TipoItem(5,"producto");
        //Item insertar = new Item(tipo,113659853,"maravilla"," es una maravilla" , new Date(118,9,15),10000,"gota a gota","literario");
        System.out.println("consultar item------------------------------------------- ");	 
        System.out.println(im.consultarItem(113659853).toString());
        System.out.println("consultar items------------------------------------------- ");	 
        System.out.println(im.consultarItems().toString());
        System.out.println("consultar items disponibles------------------------------------------- ");	 
        System.out.println(im.consultarItemsDisponibles().toString());
        
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        
        TipoItemMapper tim = sqlss.getMapper(TipoItemMapper.class);
        System.out.println("get tipos items------------------------------------------- ");	 
        System.out.println(tim.getTiposItems().toString());
        
        /*
        System.out.println("insertar item------------------------------------------- ");	 
        TipoItem tipo = new TipoItem(5,"producto");
        Item insertar = new Item(tipo,113659853,"maravilla"," es una maravilla" , new Date(118,9,15),10000,"gota a gota","literario");
        ItemMapper im = sqlss.getMapper(ItemMapper.class);
        //im.insertarItem(insertar);
        System.out.println(im.consultarItem(113659853).toString());
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Consultar items------------------------------------------- ");
        System.out.println(im.consultarItems());*/
        
        
        
        
        
        
        
        sqlss.commit();
        
        
        sqlss.close();

        
        
    }


}
