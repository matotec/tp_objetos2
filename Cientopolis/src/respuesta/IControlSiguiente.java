package respuesta;


import pregunta.Pregunta;
import protocolo.Protocolo;

public interface IControlSiguiente {

	public void proximaPregunta(Respuesta respuestaActual, Protocolo protocoloActual);
	public void responder(Protocolo protocoloActual);
	public void setAnteriorASiguiente(Pregunta preguntaActual, Respuesta respuestaActual);
}
