package respuesta;

import encuesta.Encuesta;
import encuesta.Finalizada;
import pregunta.Pregunta;
import protocolo.Finalizado;
import protocolo.Protocolo;

public class NoTieneSiguiente implements IControlSiguiente {

	@Override
	public void proximaPregunta(Respuesta respuestaActual, Protocolo protocoloActual) {
		// No hago nada porque no tengo "siguiente" pregunta.
	}
 
	@Override
	public void responder(Protocolo protocoloActual) {
		protocoloActual.setEstado(new Finalizado());
	}

	@Override
	public void setAnteriorASiguiente(Pregunta preguntaActual, Respuesta respuestaActual) {
		// No hago nada porque no tengo "siguiente" pregunta para setear la actual como anterior.
	}

}
