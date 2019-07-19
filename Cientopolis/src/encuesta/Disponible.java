package encuesta;

import java.util.ArrayList;
import java.util.List;

import pregunta.Pregunta;
import pregunta.ReferenciasNotificacion;
import protocolo.ConectorPreguntaRespuestas;
import protocolo.Protocolo;
import respuesta.Respuesta;

public class Disponible implements IEstadoEncuesta {

	private Pregunta preguntaInicial;
	
	public Disponible(Pregunta preguntaInicial) {
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
		// no hago nada porque solo se puede editar una encuesta en estado EnEdicion.
	}

	@Override
	public IEstadoEncuesta siguienteEstado() {
		return new Finalizada(this.getPreguntaInicial());
	}

	@Override
	public Boolean esFinalizada() {
		return false;
	}

	@Override
	public Protocolo crearProtocolo(Encuesta encuesta) {
		return new Protocolo(this.getPreguntaInicial(), encuesta, new ConectorPreguntaRespuestas());
	}

	@Override
	public void responderPreguntaProtocolo(
			Protocolo protocoloActual,
			List<Respuesta> respuestas,
			ConectorPreguntaRespuestas conectorPreguntaRespuestas,
			Respuesta respuestaPreguntaActual,
			Pregunta preguntaActual) {
		
		//disparo el "notify" de los observables.
		Encuesta encuestaActual = protocoloActual.getEncuesta();
		this.notificarObservables(encuestaActual, preguntaActual, respuestas);
		
		//guardo la pregunta respondida junto con las respuestas seleccionadas en el conector.
		conectorPreguntaRespuestas.registrarPreguntaYRespuestas(preguntaActual, respuestas);
		
		//en caso de no tener siguiente, cambia el estado del protocolo a "Finalizado"
		respuestaPreguntaActual.getControlSiguiente().responder(protocoloActual);
		
		//paso a la proxima pregunta en caso de tener siguiente tras contestar la pregunta actual.
		protocoloActual.irAPreguntaSiguiente();
		
	}
	
	private void notificarObservables(Encuesta encuesta, Pregunta pregunta, List<Respuesta> respuestas) {
		ReferenciasNotificacion referencias = new ReferenciasNotificacion(encuesta, pregunta, respuestas);
		encuesta.setReferenciasYNotificar(referencias);
		for (Respuesta respuesta : respuestas) {
			respuesta.setReferenciasYNotificar(referencias);
		}
	}

}
