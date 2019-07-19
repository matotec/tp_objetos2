package pregunta;

import java.util.List;

import encuesta.Encuesta;
import respuesta.Respuesta;

public class ReferenciasNotificacion {
	private Encuesta encuesta;
	private Pregunta pregunta;
	private List<Respuesta> respuestas;
	
	public ReferenciasNotificacion(Encuesta encuesta, Pregunta pregunta, List<Respuesta> respuestas) {
		this.encuesta = encuesta;
		this.pregunta = pregunta;
		this.respuestas = respuestas;
	}

	public Encuesta getEncuesta() {
		return this.encuesta;
	}

	public void setEncuesta(Encuesta nuevaEncuesta) {
		this.encuesta = nuevaEncuesta;
	}

	public Pregunta getPregunta() {
		return this.pregunta;
	}
	
	public void setPregunta(Pregunta nuevaPregunta) {
		this.pregunta = nuevaPregunta;
	}
	
	public List<Respuesta> getRespuestas() {
		return this.respuestas;
	}

	public void setRespuestas(List<Respuesta> nuevasRespuestas) {
		this.respuestas = nuevasRespuestas;
	}
}