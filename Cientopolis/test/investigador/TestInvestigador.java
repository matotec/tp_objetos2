package investigador;
import static org.junit.Assert.assertTrue;

//import interfaces.Proyecto;
import proyecto.Proyecto;
import respuesta.Respuesta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import encuesta.Encuesta;
import pregunta.Pregunta;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

public class TestInvestigador {
	private Encuesta mockedEncuesta;
	private Encuesta mockedEncuesta2;
	private Investigador unInvestigador;

//	private Pregunta mockedPregunta;
	private Proyecto proyecto;
//	private Proyecto proyecto2;
	private Proyecto mockedProyecto;
	private Proyecto mockedProyecto2;
	@BeforeEach 
	public void setUp() {
		unInvestigador= new Investigador("Luis");
		mockedEncuesta= mock (Encuesta.class);
		mockedEncuesta2= mock (Encuesta.class);
//		mockedPregunta= mock (Pregunta.class);
//		proyecto2=new Proyecto("unaDescripcion","unProposito");
		proyecto=new Proyecto("unaDescripcion","unProposito");

		mockedProyecto= mock (Proyecto.class);
		mockedProyecto2= mock (Proyecto.class);
	}
	@Test 
	public void testCreacionDeInvestigador() {
	
		assertTrue(unInvestigador.getNombre()=="Luis");
	
	}
/*@Test 
	public void creacionDeEncuesta() {
		
		mockedEncuesta=unInvestigador.crearEncuesta(mockedPregunta);
		

		
}
*/
//este no va 	
//@Test
//public void crearUnProyectoVacio() {
//
//	unInvestigador.crearProyecto("Soy una descripcion","Soy un proposito");
//	
//	assertTrue(unInvestigador.getProyectos().size()==1);
//}
@Test 
public void agregarEncuestaAUnProyecto() {
	unInvestigador.agregarEncuestaAProyecto(proyecto, mockedEncuesta);;
	assertTrue(proyecto.obtenerEncuestas().size()==1);
	}


@Test
public void encuestaConMayorNumeroDePreguntas() {
	unInvestigador.agregarProyecto(mockedProyecto);
	unInvestigador.agregarProyecto(mockedProyecto2);
	when(mockedProyecto.encuestaConMayorNumeroDeRespuesta()).thenReturn(mockedEncuesta);
	when(mockedProyecto2.encuestaConMayorNumeroDeRespuesta()).thenReturn(mockedEncuesta2);
	when(mockedEncuesta.getCantDeRespuestas()).thenReturn(3);
	when(mockedEncuesta2.getCantDeRespuestas()).thenReturn(8);
	assertTrue(unInvestigador.ObtenerEncuenstaFinalizadaConMayorNumerosDeRespuestas().getCantDeRespuestas()==8);
	

}
@Test
public void obtenerEncuestasDeProyecto() {
	proyecto.agregarEncuesta(mockedEncuesta);
	unInvestigador.agregarProyecto(proyecto);
	
	

	assertTrue(unInvestigador.obtenerEncuestasDeProyecto(proyecto).getClass()==ArrayList.class);
	assertTrue(unInvestigador.obtenerEncuestasDeProyecto(proyecto).contains(mockedEncuesta));
}

	
}
	