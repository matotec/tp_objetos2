package protocolo;

import java.util.ArrayList;
import java.util.List;

import respuesta.Respuesta;

public class Activo implements IEstadoProtocolo {

	@Override
	public List<Respuesta> getRespuestas(ConectorPreguntaRespuestas preguntasRespondidasYRespuestas) {
		return new ArrayList<Respuesta>();
	}
	
}
