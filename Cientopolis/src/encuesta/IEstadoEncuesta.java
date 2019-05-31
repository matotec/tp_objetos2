package encuesta;

import java.util.List;

import respuesta.Respuesta;

public interface IEstadoEncuesta {

	public List<Respuesta> getRespuestas(Encuesta encuesta);
	public Integer cantidadDeRespuestas(Encuesta encuesta);
	
}

