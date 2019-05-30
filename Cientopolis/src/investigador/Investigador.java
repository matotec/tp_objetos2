package investigador;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cientopolisApp.CientopolisApp;
import encuesta.Encuesta;
import pregunta.Pregunta;
import proyecto.Proyecto;
import respuesta.Respuesta;

public class Investigador {
private List<Proyecto> proyectos;
private String nombre;
private CientopolisApp unaAplicacion;
	public Investigador(String unNombre) {
		this.nombre=unNombre;
	}
	public String getNombre() {
		return nombre;
	}
	public void crearEncuesta(Encuesta _encuesta,Proyecto _proyecto) {
	  //aca hay q hacer la busqueda del proyecto para agregar encuesta
	  //proyectos=new ArrayList<Proyecto>();
	}
	
	
	public void crearProyecto(Proyecto _proyecto) {
		proyectos.add(_proyecto);
		
	}
//	public void CrearProyecto(CientopolisApp mockedAplicacion, String descripcion, String proposito) {
//		mockedAplicacion.CrearProyecto(descripcion,proposito);
//		
//	}
	public void agregarEncuestaAProyecto(CientopolisApp aplicacion, Proyecto proyecto,
			Encuesta encuesta) {
		aplicacion.agregarEncuestaAProyecto(proyecto,encuesta);
		
	}
	
//Nelson:ver si esta implementado	
	public List<Respuesta> obtenerRespuestasDeEncuesta(Encuesta _encuesta) {
		// TODO Auto-generated method stub
		return null;
	}
//Nelson:ver si esta implementado	
	public List<Encuesta> obtenerEncuestasDeProyecto(Proyecto _proyecto) {
		// TODO Auto-generated method stub
		return null;
	}
//Nelson:ver si esta implementado	
	public List<Proyecto> getProyectos() {
		// TODO Auto-generated method stub
		return null;
	}
//Nelson:ver si esta implementado
	public int obtenerCantDeRespuestasMaximo() {
		// TODO Auto-generated method stub
		return 0;
	}
//Nelson:ver si esta implementado	
	public List<Encuesta> obtenerEncuestasConMayorCantDeRespuestas() {
		// TODO Auto-generated method stub
		return null;
	}
	

}

