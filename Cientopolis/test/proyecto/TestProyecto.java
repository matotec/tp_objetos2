package proyecto;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import encuesta.Encuesta;
import encuesta.Finalizada;
import investigador.Investigador;
import observer.Observado;
import observer.Observador;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestProyecto {
	private Proyecto unProyecto;
	private Encuesta mockedEncuesta;
	private Encuesta mockedEncuesta2;
	private Encuesta mockedEncuesta3;
	private List<Encuesta> listaDeEncuestas;
	private Proyecto unProyectoSimple;
	private Proyecto otroProyectoSimple;
	private ProyectoCompuesto unProyectoCompuesto;
	private ProyectoCompuesto  otroProyectoCompuesto;
	private Investigador mockedInvestigador;

	
	@BeforeEach 
	 public void setUp() {
		unProyecto= new ProyectoSimple("Soy una descripcon","Soy un proposito","Soy un nombre");
		this.unProyectoSimple=new ProyectoSimple("Soy una descripcon","Soy un proposito","Soy un nombre");
		this.otroProyectoSimple=new ProyectoSimple("Soy una descripcon","Soy un proposito","Soy un nombre");
		this.unProyectoCompuesto=new ProyectoCompuesto("Soy una descripcon","Soy un proposito","Soy un nombre");
		this.otroProyectoCompuesto=new ProyectoCompuesto("Soy una descripcon","Soy un proposito","Soy un nombre");
		this.mockedInvestigador=  mock(Investigador.class);
		
		
		
		mockedEncuesta=mock(Encuesta.class);
		mockedEncuesta2=mock(Encuesta.class);
		mockedEncuesta3=mock(Encuesta.class);
		listaDeEncuestas=new ArrayList<>();
	
	} 
	@Test
	void alCrearSeElProyectoPoseeUnaDescripcionYUnProposito() {
		assertFalse(unProyecto.getDescripcion().isEmpty());
		assertFalse(unProyecto.getProposito().isEmpty());
		assertTrue(unProyecto.obtenerEncuestas().isEmpty());
		
	}
	@Test
	public void agregarEncuestaAlProyecto() {
		unProyecto.agregarEncuesta(mockedEncuesta);
		assertFalse(unProyecto.obtenerEncuestas().isEmpty());
		assertTrue(unProyecto.obtenerEncuestas().contains(mockedEncuesta));
		
		
	}
	
	@Test
	void testObtenerMaximoCantidadDeRespuestas() {
		unProyecto.agregarEncuesta(mockedEncuesta);
		unProyecto.agregarEncuesta(mockedEncuesta2);
		when(mockedEncuesta.cantidadDeRespuestas()).thenReturn(2);
		when(mockedEncuesta2.cantidadDeRespuestas()).thenReturn(1);
		assertTrue(unProyecto.obtenerMaximoCantDeRespuestas()==2);
	}
	
	@Test 
	void testObtenerEncuestasConMaximoCantidadDeRespuestas() {
		unProyecto.agregarEncuesta(mockedEncuesta);
		unProyecto.agregarEncuesta(mockedEncuesta2);
		unProyecto.agregarEncuesta(mockedEncuesta3);
		listaDeEncuestas.add(mockedEncuesta);
		when(mockedEncuesta.cantidadDeRespuestas()).thenReturn(2);
		when(mockedEncuesta2.cantidadDeRespuestas()).thenReturn(1);
		when(mockedEncuesta3.cantidadDeRespuestas()).thenReturn(1);
		assertTrue(unProyecto.obtenerMaximoCantDeRespuestas()==2);
		assertThat(unProyecto.obtenerEncuestasFinalizadasConMayorCantidadDeRespuestas(),is(listaDeEncuestas));
	} 
	
@Test 
public void testRetornarEncuestasDeUnProyectoCompuesto(){
	unProyecto.agregarEncuesta(mockedEncuesta);
	this.unProyectoSimple.agregarEncuesta(mockedEncuesta2);
	this.unProyectoCompuesto.agregarEncuesta(mockedEncuesta3);
	this.otroProyectoCompuesto.agregarEncuesta(mockedEncuesta);
	this.unProyectoCompuesto.agregarSubProyectos(this.unProyectoSimple);
	this.unProyectoCompuesto.agregarSubProyectos(this.otroProyectoSimple);
	this.unProyectoCompuesto.agregarSubProyectos(this.otroProyectoCompuesto);
	
	assertTrue(unProyectoCompuesto.obtenerEncuestas().size()==3);

} 
@ Test
public void testProyectoFinalizado() {
	when(mockedEncuesta.estaFinalizada()).thenReturn(true);
	when(mockedEncuesta2.estaFinalizada()).thenReturn(true);
	when(mockedEncuesta3.estaFinalizada()).thenReturn(true);
	
	
	
	
	unProyecto.agregarEncuesta(mockedEncuesta);
	this.unProyectoSimple.agregarEncuesta(mockedEncuesta2);
	this.unProyectoCompuesto.agregarEncuesta(mockedEncuesta3);
	this.otroProyectoCompuesto.agregarEncuesta(mockedEncuesta);
	this.unProyectoCompuesto.agregarSubProyectos(this.unProyectoSimple);
	this.unProyectoCompuesto.agregarSubProyectos(this.otroProyectoSimple);
	this.unProyectoCompuesto.agregarSubProyectos(this.otroProyectoCompuesto);
	assertTrue(unProyectoCompuesto.estaFinalizado());
}

@Test
public void testGetNombre() {
	String unNombre="Soy un nombre";
	assertThat(unNombre,is(unProyecto.getNombre()));
}

@Test
public void testUsosEncuestas() {
	List<Integer> usosDeEncuestas=new ArrayList<>();
	unProyecto.agregarEncuesta(mockedEncuesta);
	unProyecto.agregarEncuesta(mockedEncuesta2);
	unProyecto.agregarEncuesta(mockedEncuesta3);
	usosDeEncuestas.add(4);
	usosDeEncuestas.add(10);
	usosDeEncuestas.add(5);
	when(mockedEncuesta.cantidadDeUsos()).thenReturn(4);
	when(mockedEncuesta2.cantidadDeUsos()).thenReturn(10);
	when(mockedEncuesta3.cantidadDeUsos()).thenReturn(5);
	
	assertThat(unProyecto.usosDeEncuestas(),is(usosDeEncuestas));
}
 
@Test
public void testFechaDeEncuestas() {
	List<Date> fechaDeEncuestas=new ArrayList<>();
	unProyecto.agregarEncuesta(mockedEncuesta);
	unProyecto.agregarEncuesta(mockedEncuesta2);
	unProyecto.agregarEncuesta(mockedEncuesta3);
	Date date1=new Date(1,1,1);
	Date date2=new Date(2,2,2);
	Date date3=new Date(3,3,3); 
	
	fechaDeEncuestas.add(date1);
	fechaDeEncuestas.add(date2);
	fechaDeEncuestas.add(date3);
	when(mockedEncuesta.getDateCreacion()).thenReturn(date1);
	when(mockedEncuesta2.getDateCreacion()).thenReturn(date2);
	when(mockedEncuesta3.getDateCreacion()).thenReturn(date3);
	
	assertThat(unProyecto.fechaDeEncuestas(),is(fechaDeEncuestas));
}

@Test
public void testSuscribirInvestigadorProyectoSimple() {
	
	Proyecto spyUnProyecto = spy(unProyecto);
	
	spyUnProyecto.agregarEncuesta(mockedEncuesta);
	
	spyUnProyecto.suscribir(mockedInvestigador);
	
	verify(mockedEncuesta, times(1)).agregar(mockedInvestigador);
	verify(mockedEncuesta2, times(0)).agregar(mockedInvestigador);
	
	spyUnProyecto.agregarEncuesta(mockedEncuesta2);
	
	spyUnProyecto.suscribir(mockedInvestigador);
	
	verify(mockedEncuesta, times(2)).agregar(mockedInvestigador);
	verify(mockedEncuesta2, times(1)).agregar(mockedInvestigador);
	
	Investigador mockedInvestigador2 = mock(Investigador.class);
	
	spyUnProyecto.suscribir(mockedInvestigador2);
	
	verify(mockedEncuesta, times(2)).agregar(mockedInvestigador);
	verify(mockedEncuesta2, times(1)).agregar(mockedInvestigador);
	
	verify(mockedEncuesta, times(1)).agregar(mockedInvestigador2);
	verify(mockedEncuesta2, times(1)).agregar(mockedInvestigador2);
	
}

@Test
public void testSuscribirInvestigadorProyectoCompuesto() {
	
	Proyecto spyUnProyecto = spy(unProyecto);
	spyUnProyecto.agregarEncuesta(mockedEncuesta);
	
	ProyectoCompuesto spyUnProyectoCompuesto = spy(unProyectoCompuesto);
	spyUnProyectoCompuesto.agregarSubProyectos(unProyecto);
	spyUnProyectoCompuesto.agregarEncuesta(mockedEncuesta2);
	
	spyUnProyectoCompuesto.suscribir(mockedInvestigador);
	
	verify(mockedEncuesta, times(1)).agregar(mockedInvestigador);
	verify(mockedEncuesta2, times(1)).agregar(mockedInvestigador);
	verify(mockedEncuesta3, times(0)).agregar(mockedInvestigador);
	
	spyUnProyectoCompuesto.agregarEncuesta(mockedEncuesta3);
	
	spyUnProyectoCompuesto.suscribir(mockedInvestigador);
	
	verify(mockedEncuesta, times(2)).agregar(mockedInvestigador);
	verify(mockedEncuesta2, times(2)).agregar(mockedInvestigador);
	verify(mockedEncuesta3, times(1)).agregar(mockedInvestigador);
	
	Investigador mockedInvestigador2 = mock(Investigador.class);
	
	spyUnProyectoCompuesto.suscribir(mockedInvestigador2);
	
	verify(mockedEncuesta, times(2)).agregar(mockedInvestigador);
	verify(mockedEncuesta2, times(2)).agregar(mockedInvestigador);
	verify(mockedEncuesta3, times(1)).agregar(mockedInvestigador);
	
	verify(mockedEncuesta, times(1)).agregar(mockedInvestigador2);
	verify(mockedEncuesta2, times(1)).agregar(mockedInvestigador2);
	verify(mockedEncuesta3, times(1)).agregar(mockedInvestigador2);
	
}


}

