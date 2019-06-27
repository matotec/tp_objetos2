package cientopolisApp;

import java.sql.Date;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

import encuesta.Encuesta;
import proyecto.Proyecto;

public class OrdenamientoPorCreacion extends Ordenamiento{

	@Override
	public List<Encuesta> ordenar(CientopolisApp _cientopolisApp) {
		List<Date> fechasDeEncuestasOrdenadas= this.fechasOrdenadas(_cientopolisApp);
		for(Date fecha:fechasDeEncuestasOrdenadas) {
			if(encuestasOrdenadas.size()<=20) {
			encuestasOrdenadas.addAll(this.encuestasConMismaFecha(fecha,this.proyectosDeApp(_cientopolisApp)));
			}
		}
		encuestasOrdenadas=encuestasOrdenadas.subList(0,20);
		return encuestasOrdenadas;
	}

	private List<Encuesta> encuestasConMismaFecha(Date _fecha, List<Proyecto> _proyectosDeApp) {
		List<Encuesta> encuestasConMismaFecha= new ArrayList<>();
		for(Proyecto proy:_proyectosDeApp) {
			encuestasConMismaFecha.addAll(this.encuestaConMismaFechaDeProyecto(_fecha, proy.obtenerEncuestas()));
		}
		return encuestasConMismaFecha;
	}

	private List<Encuesta> encuestaConMismaFechaDeProyecto(Date _fecha,List<Encuesta> _obtenerEncuestas) {
		List<Encuesta> encuestas= new ArrayList<>();
		for(Encuesta enc:_obtenerEncuestas) {
			if(_fecha.compareTo(enc.getDateCreacion()) == 0 ) {
				encuestas.add(enc);
			}
		}
		return encuestas;
	}

	private List<Date> fechasOrdenadas(CientopolisApp _cientopolisApp) {
		List<Date> fechasDeEncuestasOrd= new ArrayList<>();
		for(Proyecto proy:this.proyectosDeApp(_cientopolisApp)) {
			fechasDeEncuestasOrd.addAll(this.fechasDeEncuestasDeProyecto(proy));
		}
		Collections.sort(fechasDeEncuestasOrd);
		Collections.reverse(fechasDeEncuestasOrd);
		return fechasDeEncuestasOrd;
	}

	private List<Date> fechasDeEncuestasDeProyecto(Proyecto _proy) {
		List<Date> fechasDeEncuestasDeProy= new ArrayList<>();
		for(Encuesta enc:_proy.obtenerEncuestas()) {
			fechasDeEncuestasDeProy.add(enc.getDateCreacion());
		}
		return fechasDeEncuestasDeProy;
	}

}
