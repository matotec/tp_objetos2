package respuesta;

import encuesta.Encuesta;
import pregunta.Pregunta;
import protocolo.Protocolo;

public interface IControlSiguiente {

	public void proximaPregunta(Respuesta respuestaActual, Protocolo protocoloActual);
	public void responder(Protocolo protocoloActual);
	public void setAnteriorASiguiente(Pregunta preguntaActual, Respuesta respuestaActual);
}

//package respuesta;
//
//import encuesta.Encuesta;
//import pregunta.Pregunta;
//
//public interface IControlSiguiente {
//
//	public void proximaPregunta(Respuesta respuestaActual, Encuesta encuestaActual);
//	public void responder(Encuesta encuestaActual);
//	public void setAnteriorASiguiente(Pregunta preguntaActual, Respuesta respuestaActual);
//}
