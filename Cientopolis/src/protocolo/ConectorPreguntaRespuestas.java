package protocolo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import pregunta.Pregunta;
import respuesta.Respuesta;

public class ConectorPreguntaRespuestas {

	private List<Pregunta> preguntasRespondidas;
	private List<List<Respuesta>> respuestas;
	
	public ConectorPreguntaRespuestas() {
		this.preguntasRespondidas = new ArrayList<Pregunta>();
		this.respuestas = new ArrayList<List<Respuesta>>();
	}
	
	public void registrarPreguntaYRespuestas(Pregunta pregunta, List<Respuesta> respuestas) {
		this.preguntasRespondidas.add(pregunta);
		this.respuestas.add(respuestas);
	}
	
	public Boolean estaRespondida(Pregunta pregunta) {
		return this.preguntasRespondidas.contains(pregunta);
	}
	
	public List<Respuesta> getRespuestasDePregunta(Pregunta pregunta) {
		if (this.estaRespondida(pregunta)) {
			return this.respuestas.get(this.preguntasRespondidas.indexOf(pregunta));
		} else {
			return new ArrayList<Respuesta>();
		}
	}
	
	public List<Respuesta> getTodasLasRespuestas() {
		List<Respuesta> listaARetornar = 
				this.respuestas.stream()
							   .flatMap(List<Respuesta>::stream)
							   .collect(Collectors.toList());
		
		return listaARetornar;
	}
}
