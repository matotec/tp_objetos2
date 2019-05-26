package encuestado;

import java.util.List;

import encuesta.Encuesta;
import pregunta.Pregunta;
import respuesta.Respuesta;

//ver q los tipos coincidan con lo de fede en pregunta y respuesta

public class Encuestado {
	private Encuesta encuestaAResponder;
	private Pregunta preguntaActual;
	
//setea la encuesta a responder 	
	public void obtenerEncuestaAResponder(Encuesta _encuestaAResponder) {
		encuestaAResponder=_encuestaAResponder;
	}
	
//obtengo la pregunta a responder
//necesito obtener la pregunta actual de una encuesta(implementar mensaje)	
	public Pregunta leerPregunta() {
		return  preguntaActual=encuestaAResponder.getPreguntaActual();		
	}
	
	public void setPreguntaActual(Pregunta _pregunta) {
		preguntaActual=_pregunta;
	}
	
	
//obtengo las posibles respuestas
//necesito q las preguntas me den sus posibles respuestas(implementar mensaje)	
	public List<Respuesta> obtenerOpcionesDePregunta(){
		return this.leerPregunta().getOpcionesDeRespuesta();
		
	}
	
//respondo la pregunta q necesita el string,ver q el mensaje para cargar el string sea el mismo en respuesta	
	public void responderPreguntaAbierta(String _respuestaEscrita) {
		this.leerPregunta().responder(_respuestaEscrita);
	}int
	
//respondo la pregunta q necesita muchas selecciones	
	public void responderPreguntaConMultiplesSelecciones(List<Integer> _opcionesSeleccionadas) {
		this.leerPregunta().reponder(_opcionesSeleccionadas);
	}
	
//respondo la pregunta q tiene una sola seleccion	
	public void responderPreguntaConSeleccionUnica(int _opcionesSeleccionada) {
		this.leerPregunta().responder(_opcionesSeleccionada);
	}
	
//obtengo la pregunta anterior	
	public Pregunta anteriorPregunta() {
		return this.leerPregunta().getPreguntaAnterior();		
	}
	
	public Pregunta siguientePregunta() {
		return this.preguntaActual.siguiente();		
	}
	
//la pregunta actual debe setearme la siguiente como actual 	
	public void pasarASiguientePregunta() {
		this.preguntaActual.pasarASiguiente(this)
	}
	
}



