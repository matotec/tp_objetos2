package encuesta;

import java.util.List;

import encuestado.Encuestado;
import pregunta.Pregunta;
import respuesta.Respuesta;

public class Encuesta {

	public Encuesta(Pregunta _pregunta) {
		// TODO Auto-generated constructor stub
	}

	public List<Respuesta> getRespuestas() {
		// TODO Auto-generated method stub
		return null;
	}

//fede:ver si ya existe	
	public int getCantDeRespuestas() {
		// TODO Auto-generated method stub
		return 0;
	}
//fede:ver si ya existe
	public Pregunta preguntaActual() {
		// TODO Auto-generated method stub
		return null;
	}
//fede:ver si ya existe,lo uso para obtener las opciones pero lo q respondo lo simulo en los tests
	public List<Respuesta> getOpcionesDePreguntaActual() {
		// TODO Auto-generated method stub
		return null;
	}
//fede:este ya lo tenias
	public void responderPreguntaActual(List<Respuesta> _seleccionDeRespuesta, Encuestado encuestado) {
		// TODO Auto-generated method stub
		
	}
//fede:ver si ya existe
	public Pregunta getPreguntaAnterior() {
		// TODO Auto-generated method stub
		return null;
	}
//fede:ver si ya existe
	public Pregunta getPreguntaSiguiente() {
		// TODO Auto-generated method stub
		return null;
	}

}
