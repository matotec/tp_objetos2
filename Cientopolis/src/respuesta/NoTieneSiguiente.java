package respuesta;

import encuesta.Encuesta;
import encuesta.Finalizada;
import pregunta.Pregunta;

public class NoTieneSiguiente implements IControlSiguiente {

	@Override
	public void proximaPregunta(Respuesta respuestaActual, Encuesta encuestaActual) {
		// No hago nada porque no tengo "siguiente" pregunta.
	}

	@Override
	public void responder(Encuesta encuestaActual) {
		encuestaActual.setEstado(new Finalizada());
	}

	@Override
	public void setAnteriorASiguiente(Pregunta preguntaActual, Respuesta respuestaActual) {
		// No hago nada porque no tengo "siguiente" pregunta para setear la actual como anterior.
	}

}
