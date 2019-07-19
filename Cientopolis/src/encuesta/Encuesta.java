package encuesta;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import observer.Observado;
import pregunta.Pregunta;
import protocolo.ConectorPreguntaRespuestas;
import protocolo.Protocolo;
import respuesta.Respuesta;

public class Encuesta extends Observado {
	
	private IEstadoEncuesta estadoActual;
	
	private Date dateCreacion;
	
	private List<Protocolo> protocolosCreados;
	
	public Encuesta(Pregunta preguntaInicial) {
		this.estadoActual = new EnEdicion(preguntaInicial);		
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
	
	public void responderPreguntaProtocolo(
			Protocolo protocoloActual,
			List<Respuesta> respuestas,
			ConectorPreguntaRespuestas preguntasRespondidasYRespuestas,
			Respuesta respuesta,
			Pregunta preguntaActual) {
		
		this.getEstadoActual().responderPreguntaProtocolo(
				protocoloActual,
				respuestas,
				preguntasRespondidasYRespuestas,
				respuesta,
				preguntaActual
		);
		
	}
}


