package cientopolisApp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import encuesta.Encuesta;
import proyecto.Proyecto;

public class OrdenamientoAlfabeticamente extends Ordenamiento{
	
	
	@Override
	public List<Encuesta> ordenar(CientopolisApp _cientopolisApp) {
		List<String> nombresProy= this.nombresDeProyectos(_cientopolisApp);
		Collections.sort(nombresProy);
		List<Proyecto> proyectosOrdenados=this.proyectosOrdenadosAlfabeticamente(nombresProy,this.proyectosDeApp(_cientopolisApp));
		for(Proyecto proy:proyectosOrdenados) {
			encuestasOrdenadas.addAll(proy.obtenerEncuestas());
		}
		
		return encuestasOrdenadas;
	}

	private List<Proyecto> proyectosOrdenadosAlfabeticamente(List<String> _nombresProy, List<Proyecto> _allProyectos) {
		List<Proyecto> proyectosInOrder= new ArrayList<>();
		for(String nombre:_nombresProy) {
			proyectosInOrder.add(this.proyectoConNombre(nombre,_allProyectos));
			
		}
		return proyectosInOrder;
	}

	private Proyecto proyectoConNombre(String _nombre, List<Proyecto> _allProyectos) {		
		Proyecto proyRet=null;
		for(Proyecto proy:_allProyectos) {
			if(_nombre == proy.getNombre()) {
				proyRet= proy;
			}
		}
		return proyRet;
	}

	private List<String> nombresDeProyectos(CientopolisApp _cientopolisApp) {
		List<String> allNombresProyectos= new ArrayList<>();
		for(Proyecto proy:this.proyectosDeApp(_cientopolisApp)) {
			allNombresProyectos.add(proy.getNombre());
			
		}
		return allNombresProyectos;
	}
	 
}
