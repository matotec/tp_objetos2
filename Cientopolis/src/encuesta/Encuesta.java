package encuesta;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import encuestado.Encuestado;
import pregunta.DireccionDePregunta;
import pregunta.Pregunta;
import respuesta.Respuesta;

public class Encuesta {
	
	private IEstadoEncuesta estadoActual;
	private Pregunta preguntaActual;
	private List<Respuesta> respuestas;
	private Integer cantidadDeRespuestas;
	private List<DireccionDePregunta> listaDeSubscriptores;
	
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

//mensaje agregado ver implementacion de fede
	public Integer cantidadDeUsos() {
		// TODO Auto-generated method stub
		return null;
	}

//mensaje agregado ver implementacion de fede	
	public Date getDateCreacion() {
		// TODO Auto-generated method stub
		return null;
	}
//	para abajo creo q agrego nelson
	public boolean estaFinalizada() {
		// TODO Auto-generated method stub
		return (true);
	}

	

	public void recibirSubscripcion(DireccionDePregunta direccionDePregunta) {
		this.listaDeSubscriptores.add(direccionDePregunta);
		
	}

}


