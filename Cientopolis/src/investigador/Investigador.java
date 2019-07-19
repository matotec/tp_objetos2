package investigador;
import java.util.ArrayList;
import java.util.List;

import encuesta.Encuesta;
import observer.Observado;
import observer.Observador;
//import pregunta.Pregunta;
import proyecto.Proyecto;
import respuesta.Respuesta;

public class Investigador implements Observador{
 
private String nombre;
//private List<Encuesta> encuestasMayor = new ArrayList<Encuesta>();
private List<Proyecto> listaDeProyectos;

	public Investigador(String unNombre) {
		this.nombre=unNombre;
		this.listaDeProyectos= new ArrayList<Proyecto>();
	}
	public String getNombre() {
		return nombre;
	} 
	
	public void agregarEncuestaAProyecto( Proyecto proyecto,Encuesta encuesta) {
		proyecto.agregarEncuesta(encuesta);
	}
	
	public List<Proyecto> getProyectos(){
		return(this.listaDeProyectos);
	}

	public int obtenerCantDeRespuestasMaximo() {
		int maximoCantDeRespuestas=0;
		for(Proyecto proy:listaDeProyectos) {
			if(maximoCantDeRespuestas < proy.obtenerMaximoCantDeRespuestas()) {
				maximoCantDeRespuestas=proy.obtenerMaximoCantDeRespuestas();
			}
		}
		return maximoCantDeRespuestas;
		
	} 

	public List<Encuesta> obtenerEncuestasConMayorCantDeRespuestas() {
		List<Encuesta> encuestasConMayorCantDeRespuestas=new ArrayList<Encuesta>();
		int maximaCantDeRespuestas=this.obtenerCantDeRespuestasMaximo();
		
		for(Proyecto proy:listaDeProyectos) {
			if(maximaCantDeRespuestas == proy.obtenerMaximoCantDeRespuestas()) {
				encuestasConMayorCantDeRespuestas.addAll(proy.obtenerEncuestasFinalizadasConMayorCantidadDeRespuestas() );
			}
		}
		return encuestasConMayorCantDeRespuestas;
	} 
		
	public void crearProyecto(Proyecto unProyecto) {
		this.agregarProyecto(unProyecto);

	}

	public void agregarProyecto(Proyecto proyecto) {
		this.listaDeProyectos.add(proyecto);		
	}

	public void crearEncuesta(Encuesta encuestaNueva,Proyecto proyecto) {
		proyecto.agregarEncuesta(encuestaNueva);
		
	} 
	

	public List<Encuesta> obtenerEncuestasDeProyecto(Proyecto proyecto) {
		return (proyecto.obtenerEncuestas());
	}

	public List<Respuesta> obtenerRespuestasDeEncuesta(Encuesta encuesta) {

		return (encuesta.getRespuestas());
	}
	
	@Override
	public void actualizar(Observado observable) {
		observable.getReferencias();
		//hacer algo eventualmente con dichas referencias (no especifica enunciado).
		//seria delegar el comportamiento en caso de ser suscripcion de respuesta vs suscripcion de proyecto (por ejemplo).
	}
	
	public void suscribirAProyecto(Proyecto proyecto) {
		if (this.listaDeProyectos.contains(proyecto)) {
			proyecto.suscribir(this);
		}
	}
	
	public void suscribirARespuesta(Respuesta respuesta) {
		respuesta.agregar(this);
	}
}