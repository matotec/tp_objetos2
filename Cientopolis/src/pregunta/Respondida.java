package pregunta;

import encuesta.Encuesta;
import respuesta.Respuesta;

public class Respondida implements IEstadoPregunta {

	@Override
	public void proximaPregunta(Pregunta preguntaActual, Encuesta encuestaActual) {
		this.getRespuesta(preguntaActual).getControlSiguiente().proximaPregunta(this.getRespuesta(preguntaActual), encuestaActual);
	} 
	
	private Respuesta getRespuesta(Pregunta preguntaActual) {
		return preguntaActual.getRespuestas().get(0);
	}

}
