package cientopolisApp;



import java.util.ArrayList;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		
		return encuestasOrdenadas;
	}
 
	private List<Encuesta> encuestasConMismaFecha(Date _fecha, List<Proyecto> _proyectosDeApp) {
		List<Encuesta> encuestasConMismaFecha= new ArrayList<>();
		for(Proyecto proy:_proyectosDeApp) {
			encuestasConMismaFecha.addAll(this.encuestaConMismaFechaDeProyecto(_fecha, proy.obtenerEncuestas()));
		}
		return encuestasConMismaFecha;
	}
	// _fecha.getYear()==enc.getDateCreacion().getYear()&&_fecha.getMonth()==enc.getDateCreacion().getMonth()&&_fecha.getDate()==enc.getDateCreacion().getDate()
	private List<Encuesta> encuestaConMismaFechaDeProyecto(Date _fecha,List<Encuesta> _obtenerEncuestas) {
		List<Encuesta> encuestas= new ArrayList<>();
		for(Encuesta enc:_obtenerEncuestas) {
			if(encuestas.size()!=20&& _fecha.compareTo(enc.getDateCreacion())==0   ) {
				encuestas.add(enc);
			}
		}
		return encuestas;
	}
 
	private List<Date> fechasOrdenadas(CientopolisApp _cientopolisApp) {
		List<Date> fechasDeEncuestasOrd= new ArrayList<>();
		for(Proyecto proy:this.proyectosDeApp(_cientopolisApp)) {
			fechasDeEncuestasOrd.addAll(proy.fechaDeEncuestas());
		}
		Set<Date> hashSet = new HashSet<Date>(fechasDeEncuestasOrd);
        fechasDeEncuestasOrd.clear();
        fechasDeEncuestasOrd.addAll(hashSet);
		Collections.sort(fechasDeEncuestasOrd);
		Collections.reverse(fechasDeEncuestasOrd);
		return fechasDeEncuestasOrd;
	}


}
