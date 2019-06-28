package proyecto;

import java.util.ArrayList;
import java.util.List;


import encuesta.Encuesta;
import investigador.Investigador;
import pregunta.DireccionDePregunta;




public abstract class Proyecto {
	protected List<Proyecto> subsProyectos;
	private String proposito;
	private String descripcion;
	private String nombre;
	protected List<Encuesta> listadoDeEncuestas;
	private Encuesta enc;
	private DireccionDePregunta direccionASubscribir;

	public Proyecto(String descripcion, String proposito, String nombre) {
		this.descripcion=descripcion;
		this.proposito=proposito;
		this.nombre=nombre;
		this.listadoDeEncuestas = new ArrayList<Encuesta>();
		subsProyectos=new ArrayList<Proyecto> ();
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
		return(this.listadoDeEncuestas);
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

	public boolean estaFinalizado () {
	
		
		Boolean proyFinalizado=true;
		for (Encuesta enc : this.listadoDeEncuestas) {
			
			proyFinalizado = proyFinalizado && enc.estaFinalizada();
			
		}
		
		
		for (Proyecto proy : this.getSubProyectos()) {
			
			proyFinalizado = proyFinalizado && proy.estaFinalizado();
			
		}
		
		return proyFinalizado;
	}


	protected List<Proyecto> getSubProyectos(){
	return this.subsProyectos;
	}


	public void recibirSubscripcion(Investigador investigador) {
		for(int i=0;i<this.subsProyectos.size();i++) {
			this.subsProyectos.get(i).recibirSubscripcion(investigador);
		}
		for(int i=0;i<this.listadoDeEncuestas.size();i++) {
			this.direccionASubscribir= new DireccionDePregunta(investigador,this,this.listadoDeEncuestas.get(i));

			
			this.listadoDeEncuestas.get(i).recibirSubscripcion(this.direccionASubscribir);
		}
	}
	
	public String getNombre() {
		
		return nombre;
	}
	
	public List<Integer> usosDeEncuestas() {

		return null;
	}
}


//package proyecto;
//
//import java.util.ArrayList;
////import java.util.Collection;
//import java.util.List;
//
//
//import encuesta.Encuesta;
//
//
//
//
//public class Proyecto {
//
//	private String proposito;
//	private String descripcion;
//	private String nombre;
//	private List<Encuesta> listadoDeEncuestas;
//
//
//	public Proyecto(String _descripcion, String _proposito, String _nombre) {
//		this.descripcion=_descripcion;
//		this.proposito=_proposito;
//		this.nombre=_nombre;
//		this.listadoDeEncuestas = new ArrayList<Encuesta>();
//		
//	}
// 
//	
//	public String getProposito() {
//		return proposito;
//	}
//
//
//	public String getDescripcion() {
//		return descripcion;
//	}
//
//
//	public void agregarEncuesta( Encuesta encuestaNueva) {
//		listadoDeEncuestas.add(encuestaNueva);
//		
//		
//	}
//
//
//	public List<Encuesta> obtenerEncuestas() {
//		return this.listadoDeEncuestas;
//	}
//
//	
////recorro todas las encuestas y me quedo con la cant maxima de respuestas
//	public int obtenerMaximoCantDeRespuestas() {
//		int maximoCantDeRespuestas=0;
//		for(Encuesta e:listadoDeEncuestas) {
//			if(maximoCantDeRespuestas < e.cantidadDeRespuestas()) {
//				maximoCantDeRespuestas=e.cantidadDeRespuestas();
//			}
//		}
//		return maximoCantDeRespuestas;
//	}
//
//	
////	recorro todas las encuestas y me quedo con las q tienen la maxima cant de respuestas
//	public List<Encuesta> obtenerEncuestasFinalizadasConMayorCantidadDeRespuestas(){
//		List<Encuesta> encuestasConMayorCantDeRespuestas=new ArrayList<Encuesta>();
//		int maximaCantDeRespuestas=this.obtenerMaximoCantDeRespuestas();
//		
//		for(Encuesta e:listadoDeEncuestas) {
//			if(maximaCantDeRespuestas == e.cantidadDeRespuestas()) {
//				encuestasConMayorCantDeRespuestas.add(e);
//			}
//		}
//		return encuestasConMayorCantDeRespuestas;
//	}
//
////mensaje agregado ver implementacion de nelson
//	public String getNombre() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
////mensaje agregado ver implementacion de nelson
//	public List<Integer> usosDeEncuestas() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
