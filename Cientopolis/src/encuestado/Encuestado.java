package encuestado;

import java.util.ArrayList;
import java.util.List;

import encuesta.Encuesta;
import pregunta.Pregunta;
import respuesta.Respuesta;

//ver q los tipos coincidan con lo de fede en pregunta y respuesta

public class Encuestado {
	private Encuesta encuestaAResponder;
	String textoRespuesta="";
	
	public Encuestado(Encuesta _encuesta) {
		encuestaAResponder=_encuesta;
	}
		
//obtengo las posibles respuestas
//necesito q las preguntas me den sus posibles respuestas(implementar mensaje)	
	public List<Respuesta> obtenerOpcionesDePregunta(){
		return encuestaAResponder.getOpcionesDePreguntaActual();
		
	}
//selecciono con la lista de numeros las opciones seleccionadas en la abierta pongo uno
	public void seleccionarOpciones(List<Integer> _opcionesSeleccionadas){
		List<Respuesta>opcionesSeleccionadas=new ArrayList<Respuesta>();
		for(Integer i:_opcionesSeleccionadas) {
			opcionesSeleccionadas.add(this.obtenerOpcionesDePregunta().get(i));
		}
		this.responderPreguntaActual( opcionesSeleccionadas );
		
	}
	
	public void responderPreguntaActual(List<Respuesta> _seleccionDeRespuesta) {
		this.encuestaAResponder.responderPreguntaActual( _seleccionDeRespuesta,this);
		
	}

	public void escribirTextoRespuesta(String _string) {
		 textoRespuesta=textoRespuesta+_string;
	}
	
//hacer para mandar el texto a la respuesta	
	public String getTextoRespuesta() {
		return textoRespuesta;
	}
	
	
//solo para ver la pregunta anterior
	public Pregunta anteriorPregunta() {
		return encuestaAResponder.getPreguntaAnterior();		
	}
//solo para ver la pregunta siguiente	
	public Pregunta siguientePregunta() {
		return encuestaAResponder.getPreguntaSiguiente();		
	}
	
}

//ver q se setee la siguiente pregunta en encuesta para q cuando vaya a responder
//la siguiente este seteada esa

