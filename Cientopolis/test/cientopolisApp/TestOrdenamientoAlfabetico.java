package cientopolisApp;


import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;



import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import encuesta.Encuesta;
import investigador.Investigador;
import proyecto.Proyecto;

public class TestOrdenamientoAlfabetico {
	private OrdenamientoAlfabeticamente OrdAlf;
	private CientopolisApp app;
	private Investigador mockInv1;
	private Proyecto mockProy1;
	private Proyecto mockProy2;
	private Encuesta mockEnc1;
	private Encuesta mockEnc2;
	private Encuesta mockEnc3;
	private Encuesta mockEnc4;
	List<Encuesta> encuestasOrdenadas;
	
	@BeforeEach 
	public void setUp(){
		OrdAlf= new OrdenamientoAlfabeticamente();
		app= new CientopolisApp();
		mockInv1= mock (Investigador.class);
		mockProy1= mock (Proyecto.class);
		mockProy2= mock (Proyecto.class);
		mockEnc1= mock (Encuesta.class);
		mockEnc2= mock (Encuesta.class);
		mockEnc3= mock (Encuesta.class);
		mockEnc4= mock (Encuesta.class);
		encuestasOrdenadas= new ArrayList<>();
	}

	
	
	@Test
	public void testEncuestasOrdenadasPorProyectosOrdenadosAlfabeticamente() {
		List<Proyecto> proyMockInv1= new ArrayList<>();
		List<Encuesta> encuestasProy1=new ArrayList<>();
		List<Encuesta> encuestasProy2=new ArrayList<>();
		proyMockInv1.add(mockProy1);
		proyMockInv1.add(mockProy2);
		encuestasProy1.add(mockEnc1);
		encuestasProy1.add(mockEnc2);
		encuestasProy2.add(mockEnc3);
		encuestasProy2.add(mockEnc4);
		encuestasOrdenadas.add(mockEnc3);
		encuestasOrdenadas.add(mockEnc4);
		encuestasOrdenadas.add(mockEnc1);
		encuestasOrdenadas.add(mockEnc2);
		app.crearCuentaInvestigador(mockInv1);
		when(mockInv1.getProyectos()).thenReturn(proyMockInv1);
		when(mockProy1.getNombre()).thenReturn("ProyUno");
		when(mockProy2.getNombre()).thenReturn("ProyDos");
		when(mockProy1.obtenerEncuestas()).thenReturn(encuestasProy1);
		when(mockProy2.obtenerEncuestas()).thenReturn(encuestasProy2);
		assertThat(OrdAlf.ordenar(app),is(encuestasOrdenadas));
	} 
	 
}
