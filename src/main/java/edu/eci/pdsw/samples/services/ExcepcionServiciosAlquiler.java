package edu.eci.pdsw.samples.services;


public class ExcepcionServiciosAlquiler extends Exception {
	
    public ExcepcionServiciosAlquiler(String msg) {
        super(msg);
    }
    
    public ExcepcionServiciosAlquiler(String msg ,Exception ex) {
    	super (msg + ex.getMessage());
    }
}