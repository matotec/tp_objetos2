package cientopolisApp;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
//import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertArrayEquals;

//import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import encuesta.Encuesta;
import investigador.Investigador;
import proyecto.Proyecto;
import respuesta.Respuesta;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;


public class TestCientopolisApp {
	private CientopolisApp app;
	private Investigador mockInv1;
	private Investigador mockInv2;
	private Proyecto proy1;
	private Proyecto mockProy1;
	//private Proyecto mockProy2;
	private List<Proyecto> listaDeProyecto;
	private Encuesta mockEncuesta1;
	private Encuesta mockEncuesta2;
	private List<Encuesta> listaDeEncuesta;
	private List<Encuesta> listaDeEncuesta1;
	private Respuesta mockResp1;
	private Respuesta mockResp2;
	//private Respuesta mockResp3;
	private List<Respuesta> listaDeRespuesta;
	
	@BeforeEach
	public void setUp() {
		app= new CientopolisApp();
		mockInv1= mock (Investigador.class);
		mockInv2= mock (Investigador.class);
		mockProy1= mock(Proyecto.class);
		//mockProy2= mock(Proyecto.class);
		listaDeProyecto= new ArrayList<Proyecto>();
		mockEncuesta1= mock(Encuesta.class);
		mockEncuesta2= mock(Encuesta.class);
		listaDeEncuesta=new ArrayList<Encuesta>();
		listaDeEncuesta1=new ArrayList<Encuesta>();
		mockResp1=mock (Respuesta.class);
		mockResp2=mock (Respuesta.class);
		//mockResp3=mock (Respuesta.class);
		listaDeRespuesta=new ArrayList<Respuesta>();
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
//		encuesta1=app.crearEncuesta(mockPreg1);
		app.crearCuentaInvestigador(mockInv1); 
		app.agregarEncuesta(mockInv1, mockProy1, mockEncuesta1);
		
		verify(mockInv1).agregarEncuestaAProyecto(mockProy1, mockEncuesta1);
	}
	
//	@Test
//	public void testSeCreaEncuesta() {
//		fail("Not yet implemented");
//	}
	
	@Test
	public void testObtenerRespuestasDeEncuesta() {
		app.crearCuentaInvestigador(mockInv1);
		app.agregarEncuesta(mockInv1, mockProy1, mockEncuesta1);
		listaDeRespuesta.add(mockResp1);
		listaDeRespuesta.add(mockResp2);
		when(mockInv1.obtenerRespuestasDeEncuesta(mockEncuesta1)).thenReturn(listaDeRespuesta);
		assertTrue(mockResp1 ==(app.obtenerRespuestasDeEncuesta(mockEncuesta1).get(0))); 
		assertTrue(app.obtenerRespuestasDeEncuesta(mockEncuesta1).size()== 2);
	}
	
	@Test
	public void testObtenerEncuestasDeProyecto() {
		app.crearCuentaInvestigador(mockInv1);
		listaDeEncuesta.add(mockEncuesta1);
		when(mockInv1.obtenerEncuestasDeProyecto(mockProy1)).thenReturn(listaDeEncuesta);
 
		assertTrue(app.obtenerEncuestasDeProyecto(mockProy1).size()== 1);
		assertThat(app.obtenerEncuestasDeProyecto(mockProy1),is(listaDeEncuesta));
	}
	
	@Test
	public void testObtenerProyectosDeInvestigador() {
		app.crearCuentaInvestigador(mockInv1);
		listaDeProyecto.add(mockProy1);
		when(mockInv1.getProyectos()).thenReturn(listaDeProyecto);
		
		assertTrue(app.obtenerProyectosDeInvestigador(mockInv1).size()== 1);
		assertThat(app.obtenerProyectosDeInvestigador(mockInv1),is(listaDeProyecto));
	} 
	@Test
	public void testobtenerEncuestasFinalizadasConMayorCantidadDeRespuestas() {
		app.crearCuentaInvestigador(mockInv1);
		app.crearCuentaInvestigador(mockInv2);
		listaDeEncuesta.add(mockEncuesta1);
		listaDeEncuesta1.add(mockEncuesta2);
		when(app.obtenerMaximoCantDeRespuestas()).thenReturn(3);
		when(mockInv1.obtenerEncuestasConMayorCantDeRespuestas()).thenReturn(listaDeEncuesta);
		when(mockInv2.obtenerEncuestasConMayorCantDeRespuestas()).thenReturn(listaDeEncuesta1);
		when(mockInv1.obtenerCantDeRespuestasMaximo()).thenReturn(1);
		when(mockInv2.obtenerCantDeRespuestasMaximo()).thenReturn(3);
		assertTrue(app.obtenerEncuestasFinalizadasConMayorCantidadDeRespuestas().size()==1);
		assertThat(app.obtenerEncuestasFinalizadasConMayorCantidadDeRespuestas(),is(listaDeEncuesta1));
	}
}
