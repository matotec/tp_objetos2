package encuesta;

import java.util.ArrayList;
import java.util.List;

import pregunta.Pregunta;
import protocolo.ConectorPreguntaRespuestas;
import protocolo.Protocolo;
import respuesta.Respuesta;

public class Disponible implements IEstadoEncuesta {

	private Pregunta preguntaInicial;
	
	public Disponible(Pregunta preguntaInicial) {
		this.preguntaInicial = preguntaInicial;
	}
	
	@Override
	public List<Respuesta> getRespuestas(List<Protocolo> protocolosCreados) {
		return new ArrayList<Respuesta>();
	}

	@Override
	public Pregunta getPreguntaInicial() {
		return this.preguntaInicial;
	}

	@Override
	public void setPregunta(Pregunta nuevaPreguntaInicial) {
		// no hago nada porque solo se puede editar una encuesta en estado EnEdicion.
	}

	@Override
	public IEstadoEncuesta siguienteEstado() {
		return new Finalizada(this.getPreguntaInicial());
	}

	@Override
	public Boolean esFinalizada() {
		return false;
	}

	@Override
	public Protocolo crearProtocolo(Encuesta encuesta) {
		return new Protocolo(this.getPreguntaInicial(), encuesta);
	}

	@Override
	public void responderPreguntaProtocolo(
			Protocolo protocoloActual,
			List<Respuesta> respuestas,
			ConectorPreguntaRespuestas conectorPreguntaRespuestas,
			Respuesta respuestaPreguntaActual,
			Pregunta preguntaActual) {
		
		//guardo la pregunta respondida junto con las respuestas seleccionadas en el conector.
		conectorPreguntaRespuestas.registrarPreguntaYRespuestas(preguntaActual, respuestas);
		
		//en caso de no tener siguiente, cambia el estado del protocolo a "Finalizado"
		respuestaPreguntaActual.getControlSiguiente().responder(protocoloActual);
		
		//paso a la proxima pregunta en caso de tener siguiente tras contestar la pregunta actual.
		protocoloActual.irAPreguntaSiguiente();
		
	}

}

//package encuesta;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import respuesta.Respuesta;
//
//public class Disponible implements IEstadoEncuesta {
//
//	@Override
//	public List<Respuesta> getRespuestas(Encuesta encuesta) {
//		return new ArrayList<Respuesta>();
//	}
//
//	@Override
//	public Integer cantidadDeRespuestas(Encuesta encuesta) {
//		return 0;
//	}
//
//}
