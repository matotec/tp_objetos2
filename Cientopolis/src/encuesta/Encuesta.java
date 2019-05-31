//package encuesta;
//
//import java.util.List;
//
//import encuestado.Encuestado;
//import pregunta.Pregunta;
//import respuesta.Respuesta;
//
//public class Encuesta {
//
//	public Encuesta(Pregunta _pregunta) {
//		// TODO Auto-generated constructor stub
//	}
//
//	public List<Respuesta> getRespuestas() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
////fede:ver si ya existe	
//	public int getCantDeRespuestas() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
////fede:ver si ya existe
//	public Pregunta preguntaActual() {
//		// TODO Auto-generated method stub
//		return null;
//	}
////fede:ver si ya existe,lo uso para obtener las opciones pero lo q respondo lo simulo en los tests
//	public List<Respuesta> getOpcionesDePreguntaActual() {
//		// TODO Auto-generated method stub
//		return null;
//	}
////fede:este ya lo tenias
//	public void responderPreguntaActual(List<Respuesta> _seleccionDeRespuesta, Encuestado encuestado) {
//		// TODO Auto-generated method stub
//		
//	}
////fede:ver si ya existe
//	public Pregunta getPreguntaAnterior() {
//		// TODO Auto-generated method stub
//		return null;
//	}
////fede:ver si ya existe
//	public Pregunta getPreguntaSiguiente() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}


package encuesta;

import java.util.ArrayList;
import java.util.List;

import encuestado.Encuestado;
import pregunta.Pregunta;
import respuesta.Respuesta;

public class Encuesta {
	
	private IEstadoEncuesta estadoActual;
	private Pregunta preguntaActual;
	private List<Respuesta> respuestas;
	private Integer cantidadDeRespuestas;
	
	public Encuesta(Pregunta preguntaInicial) {
		this.preguntaActual = preguntaInicial;
		this.estadoActual = new Disponible();
		this.respuestas = new ArrayList<Respuesta>();
		this.cantidadDeRespuestas = 0;
	}
	
	public void setPregunta(Pregunta nuevaPreguntaActual) {
		this.preguntaActual = nuevaPreguntaActual;
	}

	public Pregunta getPreguntaActual() {
		return this.preguntaActual;
	}
	
	public void agregarRespuesta(Respuesta nuevaRespuesta) {
		this.respuestas.add(nuevaRespuesta);
		this.cantidadDeRespuestas++;
	}
	
	public List<Respuesta> getRespuestas() {
		return this.getEstadoActual().getRespuestas(this);
	}
	
	public List<Respuesta> getRespuestasPrivado() {
		return this.respuestas;
	}
	
	public Integer cantidadDeRespuestas() {
		return this.getEstadoActual().cantidadDeRespuestas(this);
	}
	
	public Integer cantidadDeRespuestasPrivado() {
		return this.cantidadDeRespuestas;
	}
	
	public IEstadoEncuesta getEstadoActual() {
		return this.estadoActual;
	}
	
	public void setEstado(IEstadoEncuesta nuevoEstado) {
		this.estadoActual = nuevoEstado;
	}
	
	public void irAPreguntaSiguiente() {
		this.getPreguntaActual().interaccionSiguientePregunta(this);
	}
	
	public void irAPreguntaAnterior() {
		this.getPreguntaActual().interaccionAnteriorPregunta(this);
	}
	
	public void responderPreguntaActual(List<Respuesta> respuestas, Encuestado encuestado) {
		this.getPreguntaActual().responder(respuestas, encuestado, this);
	}
	
	public List<Respuesta> getOpcionesDePreguntaActual() {
		return this.getPreguntaActual().getOpciones();
	}
	
}


