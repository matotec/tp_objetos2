package respuesta;

import pregunta.Pregunta;

public class Respuesta {
	
	//IMPORTANTE: Revisar si es necesario tener en cuenta el escenario del llamado al metodo
	// "getSiguientePregunta" cuando no hay una "siguiente" seteada. (diria q no xq se valida
	// desde Pregunta el estado de la pregunta actual, para saber si es la ultima).
	
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
	
//	public void setSiguientePregunta(Pregunta siguientePregunta) {
//		this.siguiente = siguientePregunta;
//		this.controlSiguiente = new TieneSiguiente();
//	}
	
	public Pregunta getSiguientePregunta() {
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
