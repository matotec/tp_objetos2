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

//package respuesta;
//
//import encuesta.Encuesta;
//import pregunta.Pregunta;
//
//public class TieneSiguiente implements IControlSiguiente {
//
//	@Override
//	public void proximaPregunta(Respuesta respuestaActual, Encuesta encuestaActual) {
//		encuestaActual.setPregunta(respuestaActual.getSiguientePregunta());
//	}
//
//	@Override
//	public void responder(Encuesta encuestaActual) {
//		// No cambio el estado de la encuesta porque no finaliza hasta que no queden "siguientes" por contestar.
//	}
//
//	@Override
//	public void setAnteriorASiguiente(Pregunta preguntaActual, Respuesta respuestaActual) {
//		respuestaActual.getSiguientePregunta().setAnterior(preguntaActual);
//	}
//
//}
