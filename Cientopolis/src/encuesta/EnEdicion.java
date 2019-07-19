package encuesta;

import java.util.ArrayList;
import java.util.List;

import pregunta.Pregunta;
import protocolo.ConectorPreguntaRespuestas;
import protocolo.Protocolo;
import respuesta.Respuesta;

public class EnEdicion implements IEstadoEncuesta {
 	
	private Pregunta preguntaInicial;
	
	public EnEdicion(Pregunta preguntaInicial) {
		this.preguntaInicial = preguntaInicial;
	}

	@Override
	public List<Respuesta> getRespuestas(List<Protocolo> protocolosCreados) {
		return new ArrayList<Respuesta>();
	}

	@Override
	public Pregunta getPreguntaInicial() {
		return this.preguntaInicial;
	}

	@Override
	public void setPregunta(Pregunta nuevaPreguntaInicial) {
		this.preguntaInicial = nuevaPreguntaInicial;
	}

	@Override
	public IEstadoEncuesta siguienteEstado() {
		return new Disponible(this.getPreguntaInicial());
	}

	@Override
	public Boolean esFinalizada() {
		return false;
	}

	@Override
	public Protocolo crearProtocolo(Encuesta encuesta) {
		return null;
	}

	@Override
	public void responderPreguntaProtocolo(
			Protocolo protocoloActual,
			List<Respuesta> respuestas,
			ConectorPreguntaRespuestas conectorPreguntaRespuestas,
			Respuesta respuestaPreguntaActual,
			Pregunta preguntaActual) {
		// no hago nada porque solo se puede responder una pregunta cuando la encuesta esta Disponible.
	}
}
