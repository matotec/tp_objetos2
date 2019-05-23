package proyecto;
/*clase q implementa nelson*/

import java.util.ArrayList;

public class Proyecto {
	String descripcion;
	String proposito;
	private ArrayList<Encuesta> encuestas=new ArrayList<Encuesta>();
	public Proyecto(String _descripcion, String _proposito) {
		descripcion=_descripcion;
		proposito=_proposito;
	}
	
	//mensajes a implementar,seguro algunos faltan
	
	public List<Encuesta> obtenerEncuestasFinalizadas();//solo las finalizadas
	public List<Encuesta> obtenerEncuestas();//las encuestas del proyecto
	public void agregarEncuesta(Encuesta _encuesta);//agrega una encuesta al proyecto
}
