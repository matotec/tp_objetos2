package encuesta;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import investigador.Investigador;
import pregunta.DireccionDePregunta;
import pregunta.Pregunta;
import protocolo.Protocolo;
import proyecto.Proyecto;
import respuesta.Respuesta;

public class Encuesta {
	
	private IEstadoEncuesta estadoActual;
	
	private Date dateCreacion;
	
	private List<Protocolo> protocolosCreados;
	
	private List<DireccionDePregunta> listaDeSubscriptoresPorProyecto;
	
	private List<DireccionDePregunta> subscriptoresPorRespuesta;
	
	public Encuesta(Pregunta preguntaInicial) {
		this.estadoActual = new EnEdicion(preguntaInicial);
		
//		LocalDate hoy = LocalDate.now();
//		LocalTime ahora = LocalTime.now();
		
		this.dateCreacion = new Date();
		
		this.protocolosCreados = new ArrayList<Protocolo>();
	}
	
	public void setPregunta(Pregunta nuevaPreguntaInicial) {
		this.getEstadoActual().setPregunta(nuevaPreguntaInicial);
	}
	
	public IEstadoEncuesta getEstadoActual() {
		return this.estadoActual;
	}
	
	public Boolean estaFinalizada() {
		return this.getEstadoActual().esFinalizada();
	}
	
	public Protocolo crearProtocolo() {
		return this.getEstadoActual().crearProtocolo(this);
	}
	
	public void pasarASiguienteEstado() {
		this.estadoActual = getEstadoActual().siguienteEstado();
	}
	
	public Date getDateCreacion() {
		return this.dateCreacion;
	}
	
	public Integer cantidadDeRespuestas() {
		return this.getRespuestas().size();
	}
	
	public Integer cantidadDeUsos() {
		return this.protocolosCreados.size();
	}
	
	public List<Respuesta> getRespuestas() {
		return this.getEstadoActual().getRespuestas(this.protocolosCreados);
	}
	
	public void recibirSubscripcion(DireccionDePregunta direccionDePregunta) {
		this.listaDeSubscriptoresPorProyecto.add(direccionDePregunta);
	}
	
	public void recibirSubscripcion(Investigador investigador, Proyecto unProyecto, Pregunta unaPregunta,Respuesta unaRespuesta) {
		DireccionDePregunta preguntaYRespuestaDeSubscripcion = new DireccionDePregunta(investigador,unProyecto,this);
		preguntaYRespuestaDeSubscripcion.setPregunta(unaPregunta);
		preguntaYRespuestaDeSubscripcion.setRespuesta(unaRespuesta);
		
		this.subscriptoresPorRespuesta.add(preguntaYRespuestaDeSubscripcion);
	}
}



//package encuesta;
//
//
//import java.sql.Date;
//import java.util.ArrayList;
//
//import java.util.List;
//
//import encuestado.Encuestado;
//import pregunta.DireccionDePregunta;
//import pregunta.Pregunta;
//import respuesta.Respuesta;
//
//public class Encuesta {
//	
//	private IEstadoEncuesta estadoActual;
//	private Pregunta preguntaActual;
//	private List<Respuesta> respuestas;
//	private Integer cantidadDeRespuestas;
//	private List<DireccionDePregunta> listaDeSubscriptores;
//	
//	public Encuesta(Pregunta preguntaInicial) {
//		this.preguntaActual = preguntaInicial;
//		this.estadoActual = new Disponible();
//		this.respuestas = new ArrayList<Respuesta>();
//		this.cantidadDeRespuestas = 0;
//	}
//	
//	public void setPregunta(Pregunta nuevaPreguntaActual) {
//		this.preguntaActual = nuevaPreguntaActual;
//	}
//
//	public Pregunta getPreguntaActual() {
//		return this.preguntaActual;
//	}
//	
//	public void agregarRespuesta(Respuesta nuevaRespuesta) {
//		this.respuestas.add(nuevaRespuesta);
//		this.cantidadDeRespuestas++;
//	}
//	
//	public List<Respuesta> getRespuestas() {
//		return this.getEstadoActual().getRespuestas(this);
//	}
//	
//	public List<Respuesta> getRespuestasPrivado() {
//		return this.respuestas;
//	}
//	
//	public Integer cantidadDeRespuestas() {
//		return this.getEstadoActual().cantidadDeRespuestas(this);
//	}
//	 
//	public Integer cantidadDeRespuestasPrivado() {
//		return this.cantidadDeRespuestas;
//	}
//	
//	public IEstadoEncuesta getEstadoActual() {
//		return this.estadoActual;
//	}
//	
//	public void setEstado(IEstadoEncuesta nuevoEstado) {
//		this.estadoActual = nuevoEstado;
//	}
//	
//	public void irAPreguntaSiguiente() {
//		this.getPreguntaActual().interaccionSiguientePregunta(this);
//	}
//	
//	public void irAPreguntaAnterior() {
//		this.getPreguntaActual().interaccionAnteriorPregunta(this);
//	}
//	
//	public void responderPreguntaActual(List<Respuesta> respuestas, Encuestado encuestado) {
//		this.getPreguntaActual().responder(respuestas, encuestado, this);
//	}
//	
//	public List<Respuesta> getOpcionesDePreguntaActual() {
//		return this.getPreguntaActual().getOpciones();
//	}
//
////mensaje agregado ver implementacion de fede
//	public Integer cantidadDeUsos() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
////mensaje agregado ver implementacion de fede	
//	public Date getDateCreacion() {
//		// TODO Auto-generated method stub
//		return null;
//	}
////	para abajo creo q agrego nelson
//	public boolean estaFinalizada() {
//		// TODO Auto-generated method stub
//		return (true);
//	}
//
//	
//
//	public void recibirSubscripcion(DireccionDePregunta direccionDePregunta) {
//		this.listaDeSubscriptores.add(direccionDePregunta);
//		
//	}
//
//}


