package observer;

import java.util.ArrayList;
import java.util.List;

import pregunta.ReferenciasNotificacion;
 
public abstract class Observado {
	private List<Observador> observers = new ArrayList<Observador>();
	private ReferenciasNotificacion referencias;
	
	public void agregar(Observador observer) {
		if (!observers.contains(observer)) {
			this.observers.add(observer);
		}
	};
	
	public void remover(Observador observer) {
		this.observers.remove(observer);
	};
	
	public ReferenciasNotificacion getReferencias() {
		return this.referencias;
	};
	
	public void setReferenciasYNotificar(ReferenciasNotificacion nuevasReferencias) {
		this.setReferencias(nuevasReferencias);
		this.notificar();
	}
	
	private void notificar() {
		for (Observador obs : observers) {
			obs.actualizar(this);
		}
	}
	
	private void setReferencias(ReferenciasNotificacion nuevasReferencias) {
		this.referencias = nuevasReferencias;
	}
}
