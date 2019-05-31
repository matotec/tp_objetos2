package encuesta;

import java.util.List;

import respuesta.Respuesta;

public class Finalizada implements IEstadoEncuesta {

	@Override
	public List<Respuesta> getRespuestas(Encuesta encuesta) {
		return encuesta.getRespuestasPrivado();
	}

	@Override
	public Integer cantidadDeRespuestas(Encuesta encuesta) {
		return encuesta.cantidadDeRespuestasPrivado();
	}
	
}