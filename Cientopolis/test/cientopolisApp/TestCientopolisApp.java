package cientopolisApp;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
//import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import encuesta.Encuesta;
import investigador.Investigador;
import pregunta.Pregunta;
import proyecto.Proyecto;

import static org.mockito.Mockito.*;


public class TestCientopolisApp {
	private CientopolisApp app;
	private Investigador mockInv1;
	private Investigador mockInv2;
	private Proyecto proy1;
	private Proyecto mockProy1;
	private Encuesta encuesta1;
	private Pregunta mockPreg1;
	
	
	@BeforeEach
	public void setUp() {
		app= new CientopolisApp();
		mockInv1= mock (Investigador.class);
		mockInv2= mock (Investigador.class);
		mockProy1= mock(Proyecto.class);
		mockPreg1= mock (Pregunta.class);
	}
	
	
	@Test
	public void testSeCreaUnaCuentaDeInvestigador() {
		app.crearCuentaInvestigador(mockInv1);
		assertEquals(app.getInvestigadores().size(),1);
	}

	@Test
	public void testSeCreanVariasCuentasDeInvestigadores() {
		app.crearCuentaInvestigador(mockInv1);
		app.crearCuentaInvestigador(mockInv2);
		assertEquals(app.getInvestigadores().size(),2);
	}
	
	@Test
	public void testAgregarProyectoAInvestigador() {
		app.crearCuentaInvestigador(mockInv1);
		proy1=app.crearProyecto("_unProyecto", "_paraAlgo");
		app.agregarProyecto(mockInv1,proy1);		
		verify(mockInv1).crearProyecto(proy1);
	}
	
	@Test
	public void testSeCreaProyecto() {
		proy1=app.crearProyecto("_unProyecto", "_paraAlgo");
		assertTrue(proy1.getDescripcion() =="_unProyecto" );
		assertTrue(proy1.getProposito() =="_paraAlgo" );
	}
	
	@Test
	public void testAgregarEncuestaAInvestigador() {
		encuesta1=app.crearEncuesta(mockPreg1);
		app.crearCuentaInvestigador(mockInv1);
		app.agregarEncuesta(mockInv1, mockProy1, encuesta1);
		
		verify(mockInv1).crearEncuesta(encuesta1, mockProy1);
	}
	
//	@Test
//	public void testSeCreaEncuesta() {
//		fail("Not yet implemented");
//	}
	
	@Test
	public void testObtenerRespuestasDeEncuesta() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testObtenerEncuestasDeProyecto() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testObtenerProyectosDeInvestigador() {
		fail("Not yet implemented");
	}
	@Test
	public void testobtenerEncuestasFinalizadasConMayorCantidadDeRespuestas() {
		fail("Not yet implemented");
	}
}
