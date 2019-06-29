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


