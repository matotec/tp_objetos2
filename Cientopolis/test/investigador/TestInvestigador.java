package investigador;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


import proyecto.Proyecto;
import proyecto.ProyectoSimple;
import respuesta.Respuesta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import encuesta.Encuesta;
import pregunta.Pregunta;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class TestInvestigador {
	private Encuesta mockedEncuesta;
	private Investigador unInvestigador;
	private List<Encuesta> listaDeEncuestas;
	private Proyecto proyecto;
	private Proyecto mockedProyecto;
	private Proyecto mockedProyecto2;
	private Pregunta mockedPregunta;
	private Respuesta mockedRespuesta;
	private List<Respuesta> listaDeRespuesta;
	@BeforeEach 
	public void setUp() {
		unInvestigador= new Investigador("Luis");
		mockedEncuesta= mock (Encuesta.class);
		proyecto=new ProyectoSimple("unaDescripcion","unProposito","unNombre");
		mockedProyecto= mock (Proyecto.class);
		mockedProyecto2= mock (Proyecto.class);
		mockedPregunta= mock(Pregunta.class);
		mockedRespuesta=mock(Respuesta.class);
		listaDeEncuestas= new ArrayList<Encuesta>();
		this.listaDeRespuesta= new ArrayList<Respuesta>();
	}
	@Test 
	public void testCreacionDeInvestigador() {
	
		assertTrue(unInvestigador.getNombre()=="Luis");
	
	}

@Test 
public void agregarEncuestaAUnProyecto() {
	unInvestigador.agregarEncuestaAProyecto(proyecto, mockedEncuesta);;
	assertTrue(proyecto.obtenerEncuestas().size()==1);
	}



@Test
public void obtenerEncuestasDeProyecto() {
	proyecto.agregarEncuesta(mockedEncuesta);
	unInvestigador.agregarProyecto(proyecto);
	
	

	assertTrue(unInvestigador.obtenerEncuestasDeProyecto(proyecto).getClass()==ArrayList.class);
	assertTrue(unInvestigador.obtenerEncuestasDeProyecto(proyecto).contains(mockedEncuesta));
}
 
@Test
public void testObtenerMaximoCantidadDeRespuestas() {
	unInvestigador.agregarProyecto(mockedProyecto);
	unInvestigador.agregarProyecto(mockedProyecto2);
	when(mockedProyecto.obtenerMaximoCantDeRespuestas()).thenReturn(2);
	when(mockedProyecto2.obtenerMaximoCantDeRespuestas()).thenReturn(1);
	assertTrue(unInvestigador.obtenerCantDeRespuestasMaximo()==2);
}
	
@Test
public void testobtenerEncuestasFinalizadasConMayorCantidadDeRespuestas() {
	unInvestigador.agregarProyecto(mockedProyecto);
	unInvestigador.agregarProyecto(mockedProyecto2);
	unInvestigador.agregarEncuestaAProyecto(mockedProyecto, mockedEncuesta);
	listaDeEncuestas.add(mockedEncuesta);
	
	when(mockedProyecto.obtenerMaximoCantDeRespuestas()).thenReturn(2);
	when(mockedProyecto2.obtenerMaximoCantDeRespuestas()).thenReturn(1);
	when(mockedProyecto.obtenerEncuestasFinalizadasConMayorCantidadDeRespuestas() ).thenReturn(listaDeEncuestas);
	
	assertTrue(unInvestigador.obtenerCantDeRespuestasMaximo()==2); 
	assertThat(unInvestigador.obtenerEncuestasConMayorCantDeRespuestas(),is(listaDeEncuestas));
}
 

@Test
public void testGetProyectos() {
	unInvestigador.agregarProyecto(mockedProyecto);
	assertTrue(unInvestigador.getProyectos().size()==1);
}



@Test
public void testCrearUnProyecto() {
	this.unInvestigador.crearProyecto(mockedProyecto);
	assertTrue(unInvestigador.getProyectos().size()==1);
}

@Test 
public void obtenerRespuestasDeEncuesta() {
	this.unInvestigador.agregarEncuestaAProyecto(mockedProyecto, mockedEncuesta);
	when(mockedEncuesta.getRespuestas()).thenReturn(new ArrayList<Respuesta>());
	
	assertTrue(this.unInvestigador.obtenerRespuestasDeEncuesta(mockedEncuesta) instanceof ArrayList);
}

@Test
public void testCrearEncuesta() {	
	this.unInvestigador.crearEncuesta(mockedEncuesta, mockedProyecto);
	verify(mockedProyecto,times(1)).agregarEncuesta(mockedEncuesta);
}

@Test
public void testSuscribirAProyecto() {
	
	Investigador unInvestigadorSpy = spy(unInvestigador);
	unInvestigadorSpy.crearProyecto(mockedProyecto);
	
	//Test con proyecto que tiene el investigador
	unInvestigadorSpy.suscribirAProyecto(mockedProyecto);
	verify(mockedProyecto, times(1)).suscribir(unInvestigadorSpy);
	
	//Test con proyecto que NO tiene el investigador
	verifyZeroInteractions(mockedProyecto2);
	
}

@Test
public void testSuscribirARespuesta() {
	Investigador unInvestigadorSpy = spy(unInvestigador);
	Respuesta mockedRespuesta2 = mock(Respuesta.class);
	
	unInvestigadorSpy.suscribirARespuesta(mockedRespuesta);
	verifyZeroInteractions(mockedRespuesta2);
	verify(mockedRespuesta, times(1)).agregar(unInvestigadorSpy);
	
	unInvestigadorSpy.suscribirARespuesta(mockedRespuesta2);
	verify(mockedRespuesta2, times(1)).agregar(unInvestigadorSpy);
	verify(mockedRespuesta, times(1)).agregar(unInvestigadorSpy);
	
}

}
	

