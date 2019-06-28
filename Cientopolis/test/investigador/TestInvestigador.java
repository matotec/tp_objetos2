package investigador;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

//import interfaces.Proyecto;
import proyecto.Proyecto;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import encuesta.Encuesta;


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
	@BeforeEach 
	public void setUp() {
		unInvestigador= new Investigador("Luis");
		mockedEncuesta= mock (Encuesta.class);
		proyecto=new Proyecto("unaDescripcion","unProposito","proyectoUno");
		mockedProyecto= mock (Proyecto.class);
		mockedProyecto2= mock (Proyecto.class);
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
 
}
	