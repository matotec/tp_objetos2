package encuesta;

import java.util.List;

import pregunta.Pregunta;
import protocolo.ConectorPreguntaRespuestas;
import protocolo.Protocolo;
import respuesta.Respuesta;

public interface IEstadoEncuesta {

	public List<Respuesta> getRespuestas(List<Protocolo> protocolosCreados);
	public Pregunta getPreguntaInicial();
	public void setPregunta(Pregunta nuevaPreguntaInicial);
	public IEstadoEncuesta siguienteEstado();
	public Boolean esFinalizada();
	public Protocolo crearProtocolo(Encuesta encuesta);
	public void responderPreguntaProtocolo(
			Protocolo protocoloActual,
			List<Respuesta> respuestas,
			ConectorPreguntaRespuestas conectorPreguntaRespuestas,
			Respuesta respuestaDePregActual,
			Pregunta preguntaActual
	);
	
}


//package encuesta;
//
//import java.util.List;
//
//import respuesta.Respuesta;
//
//public interface IEstadoEncuesta {
//
//	public List<Respuesta> getRespuestas(Encuesta encuesta);
//	public Integer cantidadDeRespuestas(Encuesta encuesta);
//	
//}

