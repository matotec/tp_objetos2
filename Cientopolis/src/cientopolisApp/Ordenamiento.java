package cientopolisApp;


import java.util.ArrayList;
import java.util.List;

import encuesta.Encuesta;
import investigador.Investigador;
import proyecto.Proyecto;

public abstract class Ordenamiento {
	List <Encuesta> EncuestasOrdenadas= new ArrayList<Encuesta>();
	
	public List<Proyecto> proyectosDeApp(CientopolisApp _cientopolisApp){
		List <Proyecto> proyectosRet= new ArrayList<Proyecto>();
		
		for(Investigador inv:_cientopolisApp.getInvestigadores()) {
			proyectosRet.addAll(inv.getProyectos());
		}
		
		return proyectosRet;
	}
	public abstract List<Encuesta> ordenar(CientopolisApp _cientopolisApp);
}
