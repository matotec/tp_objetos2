package investigador;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cientopolisApp.CientopolisApp;
import encuesta.Encuesta;
import pregunta.Pregunta;
import proyecto.Proyecto;

import static org.mockito.Mockito.*;

public class TestInvestigador {
	private Encuesta mockedEncuesta;
	private Investigador unInvestigador;

	private Pregunta mockedPregunta;
	private CientopolisApp mockedAplicacion;
	private Proyecto mockedProyecto;
	@BeforeEach 
	public void setUp() {
		unInvestigador= new Investigador("Luis");
		mockedEncuesta= mock (Encuesta.class);
		mockedPregunta= mock (Pregunta.class);
		mockedAplicacion= mock (CientopolisApp.class);
		mockedProyecto= mock (Proyecto.class);
	}
	@Test 
	public void testCreacionDeInvestigador() {
	
		assertTrue(unInvestigador.getNombre()=="Luis");
	
	}
@Test 
	public void creacionDeEncuesta() {
		unInvestigador.crearEncuesta(mockedPregunta,mockedAplicacion);
		verify(mockedAplicacion).crearEncuesta(mockedPregunta);
}
@Test
public void crearUnProyectoVacio() {
	unInvestigador.CrearProyecto(mockedAplicacion,"Soy una descripcion","Soy un proposito");
	verify(mockedAplicacion).CrearProyecto("Soy una descripcion","Soy un proposito");

}
@Test 
public void agregarEncuestaAUnProyecto() {
	unInvestigador.agregarEncuentaAProyecto(mockedAplicacion,mockedProyecto,mockedEncuesta);
	verify(mockedAplicacion).agregarEncuestaAProyecto(mockedProyecto,mockedEncuesta);

}


}
	
	
