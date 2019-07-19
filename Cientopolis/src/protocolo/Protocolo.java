package protocolo;

import java.util.List;

import encuesta.Encuesta;

import pregunta.Pregunta;

import respuesta.Respuesta;

public class Protocolo {

	private IEstadoProtocolo estadoActual;
	private Pregunta preguntaActual;
	private Encuesta encuesta;
	
	private ConectorPreguntaRespuestas preguntasRespondidasYRespuestas;
	
	public Protocolo(Pregunta preguntaInicial, Encuesta encuesta, ConectorPreguntaRespuestas preguntasRespondidasYRespuestas) {
		this.estadoActual = new Activo();
		this.preguntaActual = preguntaInicial;
		this.encuesta = encuesta;
		
		this.preguntasRespondidasYRespuestas = preguntasRespondidasYRespuestas;
	}
	
	public Pregunta getPreguntaActual() {
		return this.preguntaActual;
	}
	
	public void setPreguntaActual(Pregunta nuevaPreguntaActual) {
		this.preguntaActual = nuevaPreguntaActual;
	}
	
	public void registrarPreguntaYRespuestas(Pregunta pregunta, List<Respuesta> respuestas) {

		this.preguntasRespondidasYRespuestas.registrarPreguntaYRespuestas(pregunta, respuestas);
	}
	
	public List<Respuesta> getRespuestas() {
		return this.estadoActual.getRespuestas(this.preguntasRespondidasYRespuestas);
	}
	
	private Boolean estaRespondidaLaActual() {
		return this.preguntasRespondidasYRespuestas.estaRespondida(preguntaActual);
	}
	
	private Respuesta getRespuestaDePreguntaActual() {
		return this.preguntasRespondidasYRespuestas.getRespuestasDePregunta(preguntaActual).get(0);
	}
	
	public void irAPreguntaSiguiente() {
		if (this.estaRespondidaLaActual()) {
			this.getRespuestaDePreguntaActual()
				.getControlSiguiente()
				.proximaPregunta(this.getRespuestaDePreguntaActual(), this);
		}
	}
	
	public void irAPreguntaAnterior() {
		this.preguntaActual.interaccionAnteriorPregunta(this);
	}
	
	public void responderPreguntaActual(List<Respuesta> respuestas) { //Aca asumo que el front end se encarga de que las respuestas pasadas por parametro sean opciones validas de la pregunta actual.
		this.encuesta.responderPreguntaProtocolo(
				this,
				respuestas,
				this.preguntasRespondidasYRespuestas,
				respuestas.get(0),
				preguntaActual
		);
	} 
	
	public Encuesta getEncuesta() {
		return this.encuesta;
	}
	
	public void finalizar() {
		this.estadoActual = new Finalizado();
	}
}
