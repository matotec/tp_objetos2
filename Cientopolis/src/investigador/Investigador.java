package investigador;
import java.util.ArrayList;
import java.util.List;

import encuesta.Encuesta;
//import pregunta.Pregunta;
import proyecto.Proyecto;
import respuesta.Respuesta;






public class Investigador {
 
private String nombre;

private List<Proyecto> listaDeProyectos;
//private Encuesta encuestaNueva;
//private Proyecto unProyecto;
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
	 
//	este no sirve tienen q ser encuestas no una encuesta,recorrer todos los proyectos 
//	public Encuesta ObtenerEncuenstaFinalizadaConMayorNumerosDeRespuestas() {
//		encuestaMayor=listaDeProyectos.get(0).encuestaConMayorNumeroDeRespuesta();
//		for (int a=0; a<=(listaDeProyectos.size()-1); a++) {
//		if(	(encuestaMayor.getCantDeRespuestas())<=(listaDeProyectos.get(a).encuestaConMayorNumeroDeRespuesta().getCantDeRespuestas())) {
//			encuestaMayor=(listaDeProyectos.get(a).encuestaConMayorNumeroDeRespuesta());
//		}
//		}
//	return(encuestaMayor);
//	}
	
//Nelson:de todos los proyectos obtener el maximo numero de respuestas,es un 
//	recorrido
	public int obtenerCantDeRespuestasMaximo() {
		int maximoCantDeRespuestas=0;
		for(Proyecto proy:listaDeProyectos) {
			if(maximoCantDeRespuestas < proy.obtenerMaximoCantDeRespuestas()) {
				maximoCantDeRespuestas=proy.obtenerMaximoCantDeRespuestas();
			}
		}
		return maximoCantDeRespuestas;
		
	}
//Nelson:de todos los proyectos obtener un List<Encuesta> con las encuestas
//	conn mas respuestas,es un recorrido
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
		
	
	
	
	
	
//	los parametros no hacen falta,ver en test si cambia algo
	public void crearProyecto(Proyecto unProyecto) {
		this.agregarProyecto(unProyecto);
		//this.listaDeProyectos.add(new Proyecto(string,string2));
	}
	public void agregarProyecto(Proyecto proyecto) {
		this.listaDeProyectos.add(proyecto);		
	}
//	reimplementar,es un recorrido buscando el proyecto y agregando la encuesta
	public void crearEncuesta(Encuesta encuestaNueva,Proyecto proyecto) {
		proyecto.agregarEncuesta(encuestaNueva);
		
	}
	
//	reimplementar
	public List<Encuesta> obtenerEncuestasDeProyecto(Proyecto proyecto) {
		return (proyecto.obtenerEncuestas());
	}
//	reimplementar
	public List<Respuesta> obtenerRespuestasDeEncuesta(Encuesta encuesta) {
		// TODO Auto-generated method stub
		return (encuesta.getRespuestas());
	}
	
}

