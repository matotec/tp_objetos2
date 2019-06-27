package proyecto;

import java.util.ArrayList;
//import java.util.Collection;
import java.util.List;


import encuesta.Encuesta;




public class Proyecto {

	private String proposito;
	private String descripcion;
	private String nombre;
	private List<Encuesta> listadoDeEncuestas;


	public Proyecto(String _descripcion, String _proposito, String _nombre) {
		this.descripcion=_descripcion;
		this.proposito=_proposito;
		this.nombre=_nombre;
		this.listadoDeEncuestas = new ArrayList<Encuesta>();
		
	}
 
	
	public String getProposito() {
		return proposito;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void agregarEncuesta( Encuesta encuestaNueva) {
		listadoDeEncuestas.add(encuestaNueva);
		
		
	}


	public List<Encuesta> obtenerEncuestas() {
		return this.listadoDeEncuestas;
	}

	
//recorro todas las encuestas y me quedo con la cant maxima de respuestas
	public int obtenerMaximoCantDeRespuestas() {
		int maximoCantDeRespuestas=0;
		for(Encuesta e:listadoDeEncuestas) {
			if(maximoCantDeRespuestas < e.cantidadDeRespuestas()) {
				maximoCantDeRespuestas=e.cantidadDeRespuestas();
			}
		}
		return maximoCantDeRespuestas;
	}

	
//	recorro todas las encuestas y me quedo con las q tienen la maxima cant de respuestas
	public List<Encuesta> obtenerEncuestasFinalizadasConMayorCantidadDeRespuestas(){
		List<Encuesta> encuestasConMayorCantDeRespuestas=new ArrayList<Encuesta>();
		int maximaCantDeRespuestas=this.obtenerMaximoCantDeRespuestas();
		
		for(Encuesta e:listadoDeEncuestas) {
			if(maximaCantDeRespuestas == e.cantidadDeRespuestas()) {
				encuestasConMayorCantDeRespuestas.add(e);
			}
		}
		return encuestasConMayorCantDeRespuestas;
	}

//mensaje agregado ver implementacion de nelson
	public String getNombre() {
		// TODO Auto-generated method stub
		return null;
	}

//mensaje agregado ver implementacion de nelson
	public List<Integer> usosDeEncuestas() {
		// TODO Auto-generated method stub
		return null;
	}

}
