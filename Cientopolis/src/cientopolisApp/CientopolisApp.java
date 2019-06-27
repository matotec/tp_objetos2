package cientopolisApp;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import encuesta.Encuesta;
import investigador.Investigador;
import pregunta.Pregunta;
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
	private Date fechaActual;
	public CientopolisApp() {
		investigadores= new ArrayList<Investigador>();
	} 
	
//esto seria crear una cuenta de investigador	
	public void crearCuentaInvestigador(Investigador _investigador) {
		investigadores.add(_investigador);
	}
 
	public List<Investigador> getInvestigadores(){
		return investigadores;
	}
	
//esto seria agregar un proyecto
	public void agregarProyecto(Investigador _investigador,Proyecto _proyecto) {
		this.obtenerInvestigador(_investigador).crearProyecto(_proyecto);
	}
	
//se crea un proyecto,ahora tienen nombre los proy
	public Proyecto crearProyecto(String _descripcion,String _proposito,String _nombre) {
		return new Proyecto(_descripcion,_proposito,_nombre);	
	}
	
//agregar encuesta
//preguntar a nelson si le sirve esto
	public void agregarEncuesta(Investigador _investigador,Proyecto _proyecto,Encuesta _encuesta) {
		this.obtenerInvestigador(_investigador).agregarEncuestaAProyecto(_proyecto,_encuesta);		
	}
	 
//se crea una encuesta
//pedir a fede los param del constructor
	public Encuesta crearEncuesta(Pregunta _pregunta) {
		return new Encuesta(_pregunta);
	} 
	
//implementar todos los mens.necesarios en las otras clases
	public List<Respuesta> obtenerRespuestasDeEncuesta(Encuesta _encuesta) {
		List<Respuesta> respuestasDeEncuesta=new ArrayList<Respuesta>();
		
		for(Investigador inv:investigadores) {
			if(!(inv.obtenerRespuestasDeEncuesta(_encuesta).isEmpty())) {
				respuestasDeEncuesta=inv.obtenerRespuestasDeEncuesta(_encuesta);
			};
		} 
		return respuestasDeEncuesta;
	}
	
//implementar todos los mens.necesarios en las otras clases 
	public List<Encuesta> obtenerEncuestasDeProyecto(Proyecto _proyecto){
		List<Encuesta> encuestasDeProyecto=new ArrayList<Encuesta>();
		for(Investigador inv:investigadores) {
			//el if esta bien hecho?
			if(!(inv.obtenerEncuestasDeProyecto(_proyecto).isEmpty())) {
				encuestasDeProyecto=inv.obtenerEncuestasDeProyecto(_proyecto);
			};
		}
		return encuestasDeProyecto;
	}
		
//implementar todos los mens.necesarios en las otras clases
	public List<Proyecto> obtenerProyectosDeInvestigador(Investigador _investigador){
		List<Proyecto> proyectosDeInvestigador=new ArrayList<Proyecto>();
		if(investigadores.contains(_investigador)) {
			proyectosDeInvestigador=obtenerInvestigador(_investigador).getProyectos();
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

	public int obtenerMaximoCantDeRespuestas() {
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

	public List<Encuesta> obtenerEncuestasOrdenadasPor(Ordenamiento _ordenamiento){
		return _ordenamiento.ordenar(this);
		
	}
	
	public Date getFechaActual() {
		return fechaActual;
	}
	
	public void setFechaActual(Date _date) {
		fechaActual=_date;
	}
	
}
	



