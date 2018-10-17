package edu.eci.pdsw.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import edu.eci.pdsw.samples.services.ServiciosAlquiler;

@ManagedBean(name = "clienteBean")
@RequestScoped

public class AlquilerItemsBean extends BasePageBean {
	
	@Inject
	private ServiciosAlquiler serviciosAlquiler;
	

}
