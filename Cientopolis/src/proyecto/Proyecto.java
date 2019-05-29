package proyecto;

import java.util.ArrayList;
import java.util.function.BooleanSupplier;

import encuesta.Encuesta;



public class Proyecto {

	private String proposito;
	private String descripcion;
	private ArrayList<Encuesta> listadoDeEncuestas;

	public Proyecto(String descripcion, String proposito) {
		this.descripcion=descripcion;
		this.proposito=proposito;
		this.listadoDeEncuestas = new ArrayList<Encuesta>();
		
	}

	
	public String getProposito() {
		return proposito;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void agregarEncuesta( Encuesta mockedEncuesta) {
		listadoDeEncuestas.add(mockedEncuesta);
		
		
	}


	public ArrayList<Encuesta> obtenerEncuestas() {
		return(this.listadoDeEncuestas);
	}


	

	

}


//package proyecto;
///*clase q implementa nelson*/
//
//import java.util.ArrayList;
//
//public class Proyecto {
//	String descripcion;
//	String proposito;
//	private ArrayList<Encuestado> encuestas=new ArrayList<Encuestado>();
//	public Proyecto(String _descripcion, String _proposito) {
//		descripcion=_descripcion;
//		proposito=_proposito;
//	}
//	
//	//mensajes a implementar,seguro algunos faltan
//	
//	public List<Encuestado> obtenerEncuestasFinalizadas();//solo las finalizadas
//	public List<Encuestado> obtenerEncuestas();//las encuestas del proyecto
//	public void agregarEncuesta(Encuestado _encuesta);//agrega una encuesta al proyecto
//}
