package proyecto;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import encuesta.Encuesta;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;


public class TestProyecto {
	private Proyecto unProyecto;
	private Encuesta mockedEncuesta;
	private Encuesta mockedEncuesta2;
	private Encuesta mockedEncuesta3;
	private List<Encuesta> listaDeEncuestas;
	@BeforeEach 
	 public void setUp() {
		unProyecto= new Proyecto("Soy una descripcon","Soy un proposito");
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
	
} 
	


