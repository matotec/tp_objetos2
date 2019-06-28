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

//package pregunta;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import encuestado.Encuestado;
//import respuesta.Respuesta;
//
//public class PreguntaMultiOpcionSimpleSeleccion extends Pregunta {
//	
//	public PreguntaMultiOpcionSimpleSeleccion (String textoPreg, List<Respuesta> opcionesDeRtas) {
//		this.texto = textoPreg;
//		this.opciones = opcionesDeRtas;
//		this.estadoActual = new NoRespondida();
//		this.controlAnterior = new NoTieneAnterior();
//		opcionesDeRtas.stream().forEach(opcion -> {
//			opcion.setearComoAnteriorDeLaSiguiente(this);
//		});
//		this.respuestas = new ArrayList<Respuesta>();
//	}
//	
//	@Override
//	protected void seteoTextoRespuesta(Respuesta respuesta, Encuestado encuestado) {
//		// No hago nada porque no necesito setear texto de respuesta
//	}
//
//}
