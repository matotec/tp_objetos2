package pregunta;

import java.util.ArrayList;
import java.util.List;

import respuesta.Respuesta;

public class PreguntaMultiOpcionSimpleSeleccion extends Pregunta {
	
	public PreguntaMultiOpcionSimpleSeleccion (String textoPreg, List<Respuesta> opcionesDeRtas) {
		this.texto = textoPreg;
		this.opciones = opcionesDeRtas;
		this.controlAnterior = new NoTieneAnterior();
		opcionesDeRtas.stream().forEach(opcion -> {
			opcion.setearComoAnteriorDeLaSiguiente(this);
		});
	}

}
