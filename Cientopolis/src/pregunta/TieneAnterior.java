package pregunta;

//import encuesta.Encuesta;
import protocolo.Protocolo;

public class TieneAnterior implements IControlAnterior {

	@Override
	public void anteriorPregunta(Pregunta preguntaActual, Protocolo protocoloActual) {
		protocoloActual.setPreguntaActual(preguntaActual.getAnterior());
	}

}
