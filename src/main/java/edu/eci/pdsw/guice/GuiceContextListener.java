package edu.eci.pdsw.guice;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;
import com.google.inject.Guice;
import com.google.inject.Injector;

import edu.eci.pdsw.sampleprj.dao.ClienteDAO;
import edu.eci.pdsw.sampleprj.dao.ItemDAO;
import edu.eci.pdsw.sampleprj.dao.TipoItemDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.MyBATISClienteDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.MyBATISItemDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.MyBATISTipoItemDAO;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import edu.eci.pdsw.samples.services.impl.ServiciosAlquilerImpl;

public class GuiceContextListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.removeAttribute(Injector.class.getName());
    }

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Injector injector = Guice.createInjector(new XMLMyBatisModule() {
            @Override
            protected void initialize() {
                install(JdbcHelper.MySQL);
                setEnvironmentId("development");
                setClassPathResource("mybatis-config.xml");

                // TODO Add service class associated to Stub implementation
                bind(ClienteDAO.class).to(MyBATISClienteDAO.class);
                bind(ItemDAO.class).to(MyBATISItemDAO.class);
                bind(TipoItemDAO.class).to(MyBATISTipoItemDAO.class);
                //
                bind(ServiciosAlquiler.class).to(ServiciosAlquilerImpl.class);
            }
        });

        servletContextEvent.getServletContext().setAttribute(Injector.class.getName(), injector);
    }
}