package proyecto;


import java.util.List;

import encuesta.Encuesta;

public class ProyectoCompuesto extends Proyecto {

	public ProyectoCompuesto(String descripcion, String proposito, String _nombre) {
		super(descripcion, proposito,_nombre);
		
		
	}
	public void agregarSubProyectos(Proyecto unProyecto) {
		this.subsProyectos.add(unProyecto);
	}
	public List<Encuesta> obtenerEncuestas(){
		for(int i=0;i<this.subsProyectos.size();i++) {
			this.listadoDeEncuestas.addAll(this.subsProyectos.get(i).obtenerEncuestas());
		}
		return(this.listadoDeEncuestas);
	} 
	@Override
	protected List<Proyecto> getSubProyectos() {
		
		return this.subsProyectos;
	}
	
	

}
