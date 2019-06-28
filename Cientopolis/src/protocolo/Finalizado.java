package protocolo;

import java.util.List;

import respuesta.Respuesta;

public class Finalizado implements IEstadoProtocolo {

	@Override
	public List<Respuesta> getRespuestas(ConectorPreguntaRespuestas preguntasRespondidasYRespuestas) {
		return preguntasRespondidasYRespuestas.getTodasLasRespuestas();
	}

}
