package pregunta;

import encuesta.Encuesta;

public class NoRespondida implements IEstadoPregunta {

	@Override
	public void proximaPregunta(Pregunta preguntaActual, Encuesta encuestaActual) {
		// no paso a la proxima pregunta hasta que este contestada la actual.
	}

}
