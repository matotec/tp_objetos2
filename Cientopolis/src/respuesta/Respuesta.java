package respuesta;

import observer.Observado;
import pregunta.Pregunta;

public class Respuesta extends Observado{
	
	private String texto;
	private Pregunta siguiente;
	private IControlSiguiente controlSiguiente;
	
	public Respuesta(String textoRta) {
		this.texto = textoRta;
		this.controlSiguiente = new NoTieneSiguiente();
	}
	
	public Respuesta(String textoRta, Pregunta siguientePregunta) {
		this.texto = textoRta;
		this.siguiente = siguientePregunta;
		this.controlSiguiente = new TieneSiguiente();
	}
	
	public void setTexto(String nuevoTextoRta) {
		this.texto = nuevoTextoRta;
	}
	
	public String getTexto() {
		return this.texto;
	}
	
	
	public void setSiguientePregunta(Pregunta siguientePregunta, Pregunta preguntaActual) {
		this.siguiente = siguientePregunta;
		this.controlSiguiente = new TieneSiguiente();
		this.setearComoAnteriorDeLaSiguiente(preguntaActual);
	} 
	
	public Pregunta getSiguientePregunta() { //No valido aca porque valido en otro lado.
		return this.siguiente;
	}
	
	public IControlSiguiente getControlSiguiente() {
		return this.controlSiguiente;
	}
	
	public void setControlSiguiente(IControlSiguiente nuevoControlSiguiente) {
		this.controlSiguiente = nuevoControlSiguiente;
	}

	public void setearComoAnteriorDeLaSiguiente(Pregunta preguntaActual) {
		this.getControlSiguiente().setAnteriorASiguiente(preguntaActual, this);
	}
}
