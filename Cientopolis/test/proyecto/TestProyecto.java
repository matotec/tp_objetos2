package proyecto;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import encuesta.Encuesta;

import static org.mockito.Mockito.*;


class testProyecto {
	private Proyecto unProyecto;
	private Encuesta mockedEncuesta;
	
	@BeforeEach 
	 public void setUp() {
		unProyecto= new Proyecto("Soy una descripcon","Soy un proposito");
		mockedEncuesta=mock(Encuesta.class);
		
	
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

	}
	


