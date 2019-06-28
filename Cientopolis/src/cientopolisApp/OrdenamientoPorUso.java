package cientopolisApp;

import java.util.*;

import encuesta.Encuesta;
import proyecto.Proyecto;

public class OrdenamientoPorUso extends Ordenamiento {
	
	
	@Override
	public List<Encuesta> ordenar(CientopolisApp _cientopolisApp) {
		List<Integer> usosDeMayorAMenor= this.usosDeEncuestasOrdenados(_cientopolisApp);
		for(Integer uso:usosDeMayorAMenor) {
			if(encuestasOrdenadas.size()<=26) {
				encuestasOrdenadas.addAll(this.encuestasConMismoUso(uso,this.proyectosDeApp(_cientopolisApp)));
			}
		}
		encuestasOrdenadas=encuestasOrdenadas.subList(0,25);
		return encuestasOrdenadas;
	}

	private List<Encuesta> encuestasConMismoUso(Integer _uso, List<Proyecto> _proyectosDeApp) {
		List<Encuesta> encuestasConUso= new ArrayList<>();
		for(Proyecto proy:_proyectosDeApp) {
			encuestasConUso.addAll(this.encuestaConMismoUsoDeProyecto(_uso, proy.obtenerEncuestas()));
		}
		return encuestasConUso;
	}

	private List<Encuesta> encuestaConMismoUsoDeProyecto(Integer _uso, List<Encuesta> _encuestas) {
		List<Encuesta> encuestas= new ArrayList<>();
		for(Encuesta enc:_encuestas) {
		 	if(_uso == enc.cantidadDeUsos()) {
				encuestas.add(enc);
			}
		}
		return encuestas;
	}

	private List<Integer> usosDeEncuestasOrdenados(CientopolisApp _cientopolisApp) {
		List<Integer> usosOrdenados= new ArrayList<>();
		for(Proyecto proy:this.proyectosDeApp(_cientopolisApp)) {
			usosOrdenados.addAll(proy.usosDeEncuestas());
		}
		Collections.sort(usosOrdenados);
		Collections.reverse(usosOrdenados);
		return usosOrdenados;
	}

}
