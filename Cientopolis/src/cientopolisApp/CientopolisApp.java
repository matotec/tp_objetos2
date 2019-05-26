package cientopolisApp;

import java.util.ArrayList;
import java.util.List;

import encuesta.Encuesta;
import investigador.Investigador;
import proyecto.Proyecto;
import respuesta.Respuesta;

// Crear cuentas para los investigadores y todo lo referente a ello.
// Crear proyectos y lo referente a ellos.
// Crear encuestas nuevas.
// Responder a una encuesta y registrar sus respuestas.
// Obtener todas las respuestas de una encuesta.
// Obtener las encuestas de un proyecto.
// Obtener los proyectos de un investigador.
// Obtener las encuestas finalizadas con mayor cantidad de respuestas.

public class CientopolisApp {
	private List<Investigador> investigadores;
	
	public CientopolisApp() {
		investigadores= new ArrayList<Investigador>();
	}
	
//esto seria crear una cuenta de investigador	
	public void crearCuentaInvestigador(Investigador _investigador) {
		investigadores.add(_investigador);
	}

//esto seria agregar un proyecto
	public void agregarProyecto(Investigador _investigador,Proyecto _proyecto) {
		this.obtenerInvestigador(_investigador).agregarProyecto(_proyecto);
	}
	
//se crea un proyecto
	public Proyecto crearProyecto(String _descripcion,String _proposito) {
		return new Proyecto(_descripcion,_proposito);	
	}
	
//agregar encuesta
//preguntar a nelson si le sirve esto
	public void agregarEncuesta(Investigador _investigador,Encuesta _encuesta) {
		this.obtenerInvestigador(_investigador).crearEncuesta(_encuesta);		
	}
	
//se crea una encuesta
//pedir a fede los param del constructor
	public Encuesta crearEncuesta() {
		return new Encuesta();
	}
	
//implementar todos los mens.necesarios en las otras clases
	public List<Respuesta> obtenerRespuestasDeEncuesta(Encuesta _encuesta) {
		List<Respuesta> respuestasDeEncuesta=new ArrayList<Respuesta>();
		
		for(Investigador inv:investigadores) {
			//el if esta bien hecho?
			if(not(inv.obtenerRespuestasDeEncuesta(_encuesta).isEmpty())) {
				respuestasDeEncuesta=inv.obtenerRespuestasDeEncuesta;
			};
		}
		return respuestasDeEncuesta;
	}
	
//implementar todos los mens.necesarios en las otras clases
	public List<Encuesta> obtenerEncuestasDeProyecto(Proyecto _proyecto){
		List<Encuesta> encuestasDeProyecto=new ArrayList<Encuesta>();
		for(Investigador inv:investigadores) {
			//el if esta bien hecho?
			if(not(inv.obtenerEncuestasDeProyecto(_proyecto).isEmpty())) {
				encuestasDeProyecto=inv.obtenerEncuestasDeProyecto;
			};
		}
		return encuestasDeProyecto;
	}
		
//implementar todos los mens.necesarios en las otras clases
	public List<Proyecto> obtenerProyectosDeInvestigador(Investigador _investigador){
		List<Proyecto> proyectosDeInvestigador=new ArrayList<Proyecto>();
		if(investigadores.contains(_investigador)) {
			proyectosDeInvestigador=obtenerInvestigador(_investigador).obtenerProyectos();
		}
		return proyectosDeInvestigador;
	}
	
//aca necesito q se implementen mensajes en proyecto e investigador y encuesta	
	public List<Encuesta> obtenerEncuestasFinalizadasConMayorCantidadDeRespuestas(){
		List<Encuesta> encuestasConMayorCantDeRespuestas=new ArrayList<Encuesta>();
		int maximaCantDeRespuestas=this.obtenerMaximoCantDeRespuestas();
		
		for(Investigador inv:investigadores) {
			if(maximaCantDeRespuestas == inv.obtenerCantDeRespuestasMaximo()) {
				encuestasConMayorCantDeRespuestas.addAll(inv.obtenerEncuestasConMayorCantDeRespuestas());
			}
		}
		return encuestasConMayorCantDeRespuestas;
	}
	
//obtener el maximo cant de respuestas en todas las encuestas existentes
//aca hay mensajes q tiene q implementar nelson
	private int obtenerMaximoCantDeRespuestas() {
		int cantDeRespuestasMaximo=0;
		for(Investigador inv:investigadores) {
			//esto es un int con el maximo cant de respuestas q tiene alguna encuesta
			if( cantDeRespuestasMaximo < inv.obtenerCantDeRespuestasMaximo()) {
				cantDeRespuestasMaximo=inv.obtenerCantDeRespuestasMaximo();
			}
		}	
		return cantDeRespuestasMaximo;		
	}
	
//obtener el investigador buscado	
	private Investigador obtenerInvestigador(Investigador _investigador) {
		int cantDeInvestigadores=investigadores.size();
		Investigador investigadorARetornar=null;

		for(int i=0;i<cantDeInvestigadores;i++) {
			if(_investigador.equals(investigadores.get(i))) {
				investigadorARetornar= investigadores.get(i);
			}
		}
		return investigadorARetornar;
	}
	
}
	



//preguntas:
//se tiene q modelar lo de los lugares en las encuestas
