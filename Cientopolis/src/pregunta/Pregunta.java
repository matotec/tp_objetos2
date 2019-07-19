package pregunta;

import java.util.List;


import protocolo.Protocolo;
import respuesta.Respuesta;

public abstract class Pregunta {
	
	protected IControlAnterior controlAnterior;
	protected Pregunta anterior;
	protected String texto;
	protected List<Respuesta> opciones;

	public void interaccionAnteriorPregunta(Protocolo protocoloActual) {
		this.controlAnterior.anteriorPregunta(this, protocoloActual);
	};
	
	public String getTexto() {
		return this.texto;
	}
	
	public List<Respuesta> getOpciones() {
		return this.opciones;
	}

	public void setAnterior(Pregunta anterior) {
		this.anterior = anterior;
		this.controlAnterior = new TieneAnterior();
	}
	
	public Pregunta getAnterior() { 
		return this.anterior;
	}
}

