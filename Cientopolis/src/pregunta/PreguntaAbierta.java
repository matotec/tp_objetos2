package pregunta;

import java.util.ArrayList;

import respuesta.Respuesta;

public class PreguntaAbierta extends Pregunta {

	public PreguntaAbierta(String textoPreg, Respuesta respuestaSinTexto) {
		this.texto = textoPreg;
		this.opciones = new ArrayList<Respuesta>();
		opciones.add(respuestaSinTexto);
		this.controlAnterior = new NoTieneAnterior();
		respuestaSinTexto.setearComoAnteriorDeLaSiguiente(this);
	}
	
} 
