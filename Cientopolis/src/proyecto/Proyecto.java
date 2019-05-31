package proyecto;

import java.util.ArrayList;
import java.util.List;


import encuesta.Encuesta;



public class Proyecto {

	private String proposito;
	private String descripcion;
	private List<Encuesta> listadoDeEncuestas;
	private Encuesta encuestaMayor;

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

	public Encuesta encuestaConMayorNumeroDeRespuesta() {
		encuestaMayor=this.obtenerEncuestas().get(0);
		for(int i=0; i <=this.obtenerEncuestas().size()-1;i++) {
			if(encuestaMayor.getCantDeRespuestas()<this.obtenerEncuestas().get(i).getCantDeRespuestas()) {
				encuestaMayor=this.obtenerEncuestas().get(i);
			}
		}
		return (encuestaMayor);
	}


	

	

}
