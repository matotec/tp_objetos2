package respuesta;

import encuesta.Encuesta;
import pregunta.Pregunta;
import protocolo.Protocolo;

public class TieneSiguiente implements IControlSiguiente {

	@Override
	public void proximaPregunta(Respuesta respuestaActual, Protocolo protocoloActual) {
		protocoloActual.setPreguntaActual(respuestaActual.getSiguientePregunta());
	}

	@Override
	public void responder(Protocolo protocoloActual) {
		// No cambio el estado del protocolo porque no finaliza hasta que no queden "siguientes" por contestar.
	}

	@Override
	public void setAnteriorASiguiente(Pregunta preguntaActual, Respuesta respuestaActual) {
		respuestaActual.getSiguientePregunta().setAnterior(preguntaActual);
	}

}
