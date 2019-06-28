package pregunta;

import java.util.List;

import encuesta.Encuesta;
import protocolo.Protocolo;
import respuesta.Respuesta;
import investigador.Investigador;
import proyecto.Proyecto;

public abstract class Pregunta {
	
	protected IControlAnterior controlAnterior;
	protected Pregunta anterior;
	protected String texto;
	protected List<Respuesta> opciones;
//	protected List<DireccionDePregunta> direccionDePregunta;
//	protected DireccionDePregunta protocologoASubscribir;

	public void interaccionAnteriorPregunta(Protocolo protocoloActual) {
		this.controlAnterior.anteriorPregunta(this, protocoloActual);
	} 
	
	public String getTexto() {
		return this.texto;
	}
	
	public List<Respuesta> getOpciones() {
		return this.opciones;
	}

	public void setAnterior(Pregunta anterior) {
		this.anterior = anterior;
		this.controlAnterior = new TieneAnterior();
	}
	
	public Pregunta getAnterior() { //No necesito validar aca porque ya valido antes de llamar este metodo
		return this.anterior;
	}
	
//	public void recibirSubscripcion(Investigador investigador, Proyecto unProyecto, Encuesta unaEncuesta,Respuesta unaRespuesta) {
//		this.protocologoASubscribir= new DireccionDePregunta(investigador,unProyecto,unaEncuesta);
//		this.protocologoASubscribir.setPregunta(this);
//		this.protocologoASubscribir.setRespuesta(unaRespuesta);
//		
//		
//		this.direccionDePregunta.add(this.protocologoASubscribir);
//		
//	}
}


//package pregunta;
//
//import java.util.List;
//
//import encuesta.Encuesta;
//import encuestado.Encuestado;
//import investigador.Investigador;
//import proyecto.Proyecto;
//import respuesta.Respuesta;
//
//public abstract class Pregunta {
//	
//	protected IEstadoPregunta estadoActual;
//
//	protected IControlAnterior controlAnterior;
//	protected Pregunta anterior;
//	protected String texto;
//	protected List<Respuesta> opciones;
//	protected List<Respuesta> respuestas;
//	protected List<DireccionDePregunta> direccionDePregunta;
//	protected DireccionDePregunta 		protocologoASubscribir;
//	
//	public void interaccionSiguientePregunta(Encuesta encuesta) {
//		this.getEstadoActual().proximaPregunta(this, encuesta);
//	};
//	 
//	public void interaccionAnteriorPregunta(Encuesta encuesta) {
//		this.getControlAnterior().anteriorPregunta(this, encuesta);
//	};
//	
//	public String getTexto() {
//		return this.texto;
//	}
//	
//	public List<Respuesta> getOpciones() {
//		return this.opciones;
//	}
//	
//	public List<Respuesta> getRespuestas() {
//		return this.respuestas;
//	}
//	
//	public void setRespuestas(List<Respuesta> nuevasRespuestas) {
//		this.respuestas = nuevasRespuestas;
//		this.estadoActual = new Respondida();
//	}
//	
//	public void setAnterior(Pregunta anterior) {
//		this.anterior = anterior;
//		this.controlAnterior = new TieneAnterior();
//	}
//	
//	public Pregunta getAnterior() {
//		return this.anterior;
//	}
//	
//	protected IEstadoPregunta getEstadoActual() {
//		return this.estadoActual;
//	}
//	
//	protected void setEstado(IEstadoPregunta nuevoEstado) {
//		this.estadoActual = nuevoEstado;
//	}
//	
//	protected IControlAnterior getControlAnterior() {
//		return this.controlAnterior;
//	}
//	
//	protected void setControlAnterior(IControlAnterior nuevoControlAnterior) {
//		this.controlAnterior = nuevoControlAnterior;
//	}
//
//	public void responder(List<Respuesta> respuestas, Encuestado encuestado, Encuesta encuesta) {
//		//seteo texto de respuesta
//		this.seteoTextoRespuesta(getRespuesta(respuestas), encuestado);
//		
//		//guardo respuestas seleccionadas en la pregunta y ahi actualizo el estado de la misma.
//		this.setRespuestas(respuestas);
//		
//		//guardo las respuestas en la encuesta
//		respuestas.stream().forEach(respuesta -> {
//			encuesta.agregarRespuesta(respuesta);
//		});
//		 
//		//en caso de no tener siguiente, cambia el estado de la encuesta a "Finalizada"
//		this.getRespuesta(respuestas).getControlSiguiente().responder(encuesta);
//		this.interaccionSiguientePregunta(encuesta);
//	}
//	
//	
//	protected Respuesta getRespuesta(List<Respuesta> respuestas) {
//		return respuestas.get(0);
//	}
//	
//	protected abstract void seteoTextoRespuesta(Respuesta respuesta, Encuestado encuestado);
//	
//	public void recibirSubscripcion(Investigador investigador, Proyecto unProyecto, Encuesta unaEncuesta,Respuesta unaRespuesta) {
//		this.protocologoASubscribir= new DireccionDePregunta(investigador,unProyecto,unaEncuesta);
//		this.protocologoASubscribir.setPregunta(this);
//		this.protocologoASubscribir.setRespuesta(unaRespuesta);
//		
//		
//		this.direccionDePregunta.add(this.protocologoASubscribir);
//		
//	}
//} 

