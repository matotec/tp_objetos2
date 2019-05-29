package investigador;

import cientopolisApp.CientopolisApp;
import encuesta.Encuesta;
import pregunta.Pregunta;
import proyecto.Proyecto;

public class Investigador {
 
private String nombre;
private CientopolisApp unaAplicacion;
	public Investigador(String unNombre) {
		this.nombre=unNombre;
	}
	public String getNombre() {
		return nombre;
	}
	public void crearEncuesta(Pregunta mockedPregunta, CientopolisApp unaAplicacion) {
	  unaAplicacion.crearEncuesta(mockedPregunta);
	}
	public void CrearProyecto(CientopolisApp mockedAplicacion, String descripcion, String proposito) {
		mockedAplicacion.CrearProyecto(descripcion,proposito);
		
	}
	public void agregarEncuentaAProyecto(CientopolisApp aplicacion, Proyecto proyecto,
			Encuesta encuesta) {
		aplicacion.agregarEncuestaAProyecto(proyecto,encuesta);
		
	}
	

}

