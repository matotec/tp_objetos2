package pregunta;

import java.util.ArrayList;

import encuestado.Encuestado;
import respuesta.Respuesta;

public class PreguntaAbierta extends Pregunta {

	public PreguntaAbierta(String textoPreg, Respuesta respuestaSinTexto) {
		this.texto = textoPreg;
		this.opciones = new ArrayList<Respuesta>();
		opciones.add(respuestaSinTexto);
		this.estadoActual = new NoRespondida();
		this.controlAnterior = new NoTieneAnterior();
		respuestaSinTexto.setearComoAnteriorDeLaSiguiente(this);
		this.respuestas = new ArrayList<Respuesta>();
	}

	@Override
	protected void seteoTextoRespuesta(Respuesta respuesta, Encuestado encuestado) {
		respuesta.setTexto(encuestado.getTextoRespuesta());
	}
	
}
