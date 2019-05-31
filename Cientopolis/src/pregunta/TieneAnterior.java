package pregunta;

import encuesta.Encuesta;

public class TieneAnterior implements IControlAnterior {

	@Override
	public void anteriorPregunta(Pregunta preguntaActual, Encuesta encuestaActual) {
		encuestaActual.setPregunta(preguntaActual.getAnterior());
	}

}
