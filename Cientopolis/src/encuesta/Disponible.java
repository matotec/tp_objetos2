package encuesta;

import java.util.ArrayList;
import java.util.List;

import respuesta.Respuesta;

public class Disponible implements IEstadoEncuesta {

	@Override
	public List<Respuesta> getRespuestas(Encuesta encuesta) {
		return new ArrayList<Respuesta>();
	}

	@Override
	public Integer cantidadDeRespuestas(Encuesta encuesta) {
		return 0;
	}

}
