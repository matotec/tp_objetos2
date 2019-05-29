package investigador;

import java.util.ArrayList;
import java.util.List;

import cientopolisApp.CientopolisApp;
import encuesta.Encuesta;
import pregunta.Pregunta;
import proyecto.Proyecto;

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
	

}

