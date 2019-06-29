package encuesta;

import java.util.ArrayList;
import java.util.List;

import pregunta.Pregunta;
import protocolo.ConectorPreguntaRespuestas;
import protocolo.Protocolo;
import respuesta.Respuesta;

public class Finalizada implements IEstadoEncuesta {
	
	private Pregunta preguntaInicial;
	
	public Finalizada(Pregunta preguntaInicial) {
		this.preguntaInicial = preguntaInicial;
	}

	@Override
	public List<Respuesta> getRespuestas(List<Protocolo> protocolosCreados) {
		List<Respuesta> respuestasARetornar = new ArrayList<Respuesta>();
		
		protocolosCreados.stream().forEach(
				protocolo -> respuestasARetornar.addAll(protocolo.getRespuestas())
		);
		
		return respuestasARetornar;
	}

	@Override
	public Pregunta getPreguntaInicial() {
		return this.preguntaInicial;
	} 

	@Override
	public void setPregunta(Pregunta nuevaPreguntaInicial) {
		// no hago nada porque solo se puede editar una encuesta en estado EnEdicion.
	}

	@Override
	public IEstadoEncuesta siguienteEstado() {
		return this;
	}

	@Override
	public Boolean esFinalizada() {
		return true;
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
