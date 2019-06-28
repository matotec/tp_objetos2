package protocolo;

import java.util.List;

import respuesta.Respuesta;

public interface IEstadoProtocolo {

	List<Respuesta> getRespuestas(ConectorPreguntaRespuestas preguntasRespondidasYRespuestas);

}
