package pregunta;

//import java.util.ArrayList;
import java.util.List;

import respuesta.Respuesta;

public class PreguntaMultiOpcionMultiSeleccion extends Pregunta {
 
	public PreguntaMultiOpcionMultiSeleccion (String textoPreg, List<Respuesta> opcionesDeRtas) {
		this.texto = textoPreg;
		this.opciones = opcionesDeRtas;
		this.controlAnterior = new NoTieneAnterior();
		opcionesDeRtas.stream().forEach(opcion -> {
			opcion.setearComoAnteriorDeLaSiguiente(this);
		});
	}

}
