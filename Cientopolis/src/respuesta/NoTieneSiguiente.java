package respuesta;

import pregunta.Pregunta;

import protocolo.Protocolo;

public class NoTieneSiguiente implements IControlSiguiente {

	@Override
	public void proximaPregunta(Respuesta respuestaActual, Protocolo protocoloActual) {
		// No hago nada porque no tengo "siguiente" pregunta.
	}
 
	@Override
	public void responder(Protocolo protocoloActual) {
		protocoloActual.finalizar();
	}

	@Override
	public void setAnteriorASiguiente(Pregunta preguntaActual, Respuesta respuestaActual) {
		// No hago nada porque no tengo "siguiente" pregunta para setear la actual como anterior.
	}

}
