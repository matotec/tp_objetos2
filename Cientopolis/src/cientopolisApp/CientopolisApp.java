package cientopolisApp;


import java.util.ArrayList;
import java.util.List;

import encuesta.Encuesta;
import investigador.Investigador;
//import pregunta.Pregunta;
import proyecto.Proyecto;
import respuesta.Respuesta;


public class CientopolisApp {
	private List<Investigador> investigadores;
	
	public CientopolisApp() {
		investigadores= new ArrayList<Investigador>();
	} 
	
	
	public void crearCuentaInvestigador(Investigador _investigador) {
		investigadores.add(_investigador);
	}
 
	public List<Investigador> getInvestigadores(){
		return investigadores;
	}
	

	public void agregarProyecto(Investigador _investigador,Proyecto _proyecto) {
		this.obtenerInvestigador(_investigador).crearProyecto(_proyecto);
	}
	

	
	public void agregarEncuesta(Investigador _investigador,Proyecto _proyecto,Encuesta _encuesta) {
		this.obtenerInvestigador(_investigador).agregarEncuestaAProyecto(_proyecto,_encuesta);		
	}
	  
	

	public List<Respuesta> obtenerRespuestasDeEncuesta(Encuesta _encuesta) {
		List<Respuesta> respuestasDeEncuesta=new ArrayList<Respuesta>();
		
		for(Investigador inv:investigadores) {
			if(!(inv.obtenerRespuestasDeEncuesta(_encuesta).isEmpty())) {
				respuestasDeEncuesta=inv.obtenerRespuestasDeEncuesta(_encuesta);
			};
		} 
		return respuestasDeEncuesta;
	}
	
 
	public List<Encuesta> obtenerEncuestasDeProyecto(Proyecto _proyecto){
		List<Encuesta> encuestasDeProyecto=new ArrayList<Encuesta>();
		for(Investigador inv:investigadores) {
			
			if(!(inv.obtenerEncuestasDeProyecto(_proyecto).isEmpty())) {
				encuestasDeProyecto=inv.obtenerEncuestasDeProyecto(_proyecto);
			};
		}
		return encuestasDeProyecto;
	}
		

	public List<Proyecto> obtenerProyectosDeInvestigador(Investigador _investigador){
		List<Proyecto> proyectosDeInvestigador=new ArrayList<Proyecto>();
		if(investigadores.contains(_investigador)) {
			proyectosDeInvestigador=obtenerInvestigador(_investigador).getProyectos();
		} 
		return proyectosDeInvestigador;
	}
	
	
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
	
	
	
} 
	



