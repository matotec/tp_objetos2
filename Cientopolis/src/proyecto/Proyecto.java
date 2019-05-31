package proyecto;

import java.util.ArrayList;
import java.util.List;


import encuesta.Encuesta;




public class Proyecto {

	private String proposito;
	private String descripcion;
	private List<Encuesta> listadoDeEncuestas;


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


	public void agregarEncuesta( Encuesta encuestaNueva) {
		listadoDeEncuestas.add(encuestaNueva);
		
		
	}


	public List<Encuesta> obtenerEncuestas() {
		return(this.listadoDeEncuestas);
	}

//esto no sirve,borrar test tambien	
//	public Encuesta encuestaConMayorNumeroDeRespuesta() {
//		encuestaMayor=this.obtenerEncuestas().get(0);
//		for(int i=0; i <=this.obtenerEncuestas().size()-1;i++) {
//			if(encuestaMayor.getCantDeRespuestas()<this.obtenerEncuestas().get(i).getCantDeRespuestas()) {
//				encuestaMayor=this.obtenerEncuestas().get(i);
//			}
//		}
//		return (encuestaMayor);
//	}

//hacer test se puede hacer uno q teste el mensaje de abajo y lo toma	
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

//hacer test	
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

}
