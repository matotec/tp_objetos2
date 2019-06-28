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

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import pregunta.DireccionDePregunta;

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
	private DireccionDePregunta mockedDireccionDePregunta;

	
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
 public void testRecibirSubscripcion() {
	this.unProyecto.agregarEncuesta(mockedEncuesta);

this.unProyecto.recibirSubscripcion(this.mockedInvestigador);
verify(mockedEncuesta, times(0)).recibirSubscripcion(mockedDireccionDePregunta);


}
}

//package proyecto;
//
//
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//
//import static org.hamcrest.CoreMatchers.*;
//import static org.junit.Assert.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import encuesta.Encuesta;
//
//import static org.mockito.Mockito.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class TestProyecto {
//	private Proyecto unProyecto;
//	private Encuesta mockedEncuesta;
//	private Encuesta mockedEncuesta2;
//	private Encuesta mockedEncuesta3;
//	private List<Encuesta> listaDeEncuestas;
//	@BeforeEach 
//	 public void setUp() {
//		unProyecto= new Proyecto("Soy una descripcon","Soy un proposito","proyectoUno");
//		mockedEncuesta=mock(Encuesta.class);
//		mockedEncuesta2=mock(Encuesta.class);
//		mockedEncuesta3=mock(Encuesta.class);
//		listaDeEncuestas=new ArrayList<>();
//	
//	} 
//	@Test
//	void alCrearSeElProyectoPoseeUnaDescripcionYUnProposito() {
//		assertFalse(unProyecto.getDescripcion().isEmpty());
//		assertFalse(unProyecto.getProposito().isEmpty());
//		assertTrue(unProyecto.obtenerEncuestas().isEmpty());
//		
//	}
//	@Test
//	public void agregarEncuestaAlProyecto() {
//		unProyecto.agregarEncuesta(mockedEncuesta);
//		assertFalse(unProyecto.obtenerEncuestas().isEmpty());
//		assertTrue(unProyecto.obtenerEncuestas().contains(mockedEncuesta));
//		
//		
//	}
//	
//	@Test
//	void testObtenerMaximoCantidadDeRespuestas() {
//		unProyecto.agregarEncuesta(mockedEncuesta);
//		unProyecto.agregarEncuesta(mockedEncuesta2);
//		when(mockedEncuesta.cantidadDeRespuestas()).thenReturn(2);
//		when(mockedEncuesta2.cantidadDeRespuestas()).thenReturn(1);
//		assertTrue(unProyecto.obtenerMaximoCantDeRespuestas()==2);
//	}
//	
//	@Test 
//	void testObtenerEncuestasConMaximoCantidadDeRespuestas() {
//		unProyecto.agregarEncuesta(mockedEncuesta);
//		unProyecto.agregarEncuesta(mockedEncuesta2);
//		unProyecto.agregarEncuesta(mockedEncuesta3);
//		listaDeEncuestas.add(mockedEncuesta);
//		when(mockedEncuesta.cantidadDeRespuestas()).thenReturn(2);
//		when(mockedEncuesta2.cantidadDeRespuestas()).thenReturn(1);
//		when(mockedEncuesta3.cantidadDeRespuestas()).thenReturn(1);
//		assertTrue(unProyecto.obtenerMaximoCantDeRespuestas()==2);
//		assertThat(unProyecto.obtenerEncuestasFinalizadasConMayorCantidadDeRespuestas(),is(listaDeEncuestas));
//	} 
//	
//} 
//	
//
//
