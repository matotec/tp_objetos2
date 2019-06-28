package cientopolisApp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import encuesta.Encuesta;
import investigador.Investigador;
import proyecto.Proyecto;

public class TestOrdenamientoPorUso {
	private OrdenamientoPorUso ordPorUso;
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
	void setUp() {
		ordPorUso= new OrdenamientoPorUso();
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
	void testEncuestasOrdenadasPorCantidadDeUsos() {
		List<Proyecto> proyMockInv1= new ArrayList<>();
		List<Encuesta> encuestasProy1=new ArrayList<>();
		List<Encuesta> encuestasProy2=new ArrayList<>();
		List<Integer> usosDeEncProy1=new ArrayList<>();
		List<Integer> usosDeEncProy2=new ArrayList<>();
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
		usosDeEncProy1.add(10);
		usosDeEncProy1.add(30); 
		usosDeEncProy2.add(40);
		usosDeEncProy2.add(40);
		app.crearCuentaInvestigador(mockInv1);
		when(mockInv1.getProyectos()).thenReturn(proyMockInv1);
		when(mockProy1.obtenerEncuestas()).thenReturn(encuestasProy1);
		when(mockProy2.obtenerEncuestas()).thenReturn(encuestasProy2);
		when(mockProy1.usosDeEncuestas()).thenReturn(usosDeEncProy1);
		when(mockProy2.usosDeEncuestas()).thenReturn(usosDeEncProy2);
		when(mockEnc1.cantidadDeUsos()).thenReturn(30);
		when(mockEnc2.cantidadDeUsos()).thenReturn(30);
		when(mockEnc3.cantidadDeUsos()).thenReturn(40);
		when(mockEnc4.cantidadDeUsos()).thenReturn(40);
		assertThat(ordPorUso.ordenar(app),is(encuestasOrdenadas));
	}

}
