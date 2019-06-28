package pregunta;

import encuesta.Encuesta;
import investigador.Investigador;
import proyecto.Proyecto;
import respuesta.Respuesta;

public class DireccionDePregunta {
	private Investigador unInvestigador;
	private Proyecto unProyecto;
	private Encuesta unaEncuesta;
	private Pregunta unaPregunta;
	private Respuesta unaRespuesta;
	
	public DireccionDePregunta(Investigador unInvestigador,Proyecto unProyecto,Encuesta unaEncuesta) {
		this.unInvestigador=unInvestigador;
		this.unProyecto=unProyecto;
		this.unaEncuesta=unaEncuesta;
	}

	public Investigador getUnInvestigador() {
		return unInvestigador;
	}

	public Proyecto getUnProyecto() {
		return unProyecto;
	}

	public Encuesta getUnaEncuesta() {
		return unaEncuesta;
	}

	public void setUnaEncuesta(Encuesta unaEncuesta) {
		this.unaEncuesta = unaEncuesta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.unaPregunta=pregunta;
	}

	public void setRespuesta(Respuesta unaRespuesta) {
this.unaRespuesta=unaRespuesta;		
	}

	public Respuesta getRespuesta() {
		// TODO Auto-generated method stub
		return this.unaRespuesta;
	}

	



}

