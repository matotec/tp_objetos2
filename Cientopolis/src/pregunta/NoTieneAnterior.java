package pregunta;

import encuesta.Encuesta;

public class NoTieneAnterior implements IControlAnterior {

	@Override
	public void anteriorPregunta(Pregunta preguntaActual, Encuesta encuestaActual) {
		// No hago nada porque no tengo "anterior" pregunta.
	}

}
