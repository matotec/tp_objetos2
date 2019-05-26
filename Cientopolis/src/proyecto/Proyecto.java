package proyecto;
/*clase q implementa nelson*/

import java.util.ArrayList;

public class Proyecto {
	String descripcion;
	String proposito;
	private ArrayList<Encuestado> encuestas=new ArrayList<Encuestado>();
	public Proyecto(String _descripcion, String _proposito) {
		descripcion=_descripcion;
		proposito=_proposito;
	}
	
	//mensajes a implementar,seguro algunos faltan
	
	public List<Encuestado> obtenerEncuestasFinalizadas();//solo las finalizadas
	public List<Encuestado> obtenerEncuestas();//las encuestas del proyecto
	public void agregarEncuesta(Encuestado _encuesta);//agrega una encuesta al proyecto
}
