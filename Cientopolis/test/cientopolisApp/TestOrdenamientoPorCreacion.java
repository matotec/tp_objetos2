package cientopolisApp;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import static org.junit.Assert.assertTrue;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import encuesta.Encuesta;
import investigador.Investigador;
import proyecto.Proyecto;


public class TestOrdenamientoPorCreacion {
	private OrdenamientoPorCreacion ordPorCreacion;
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
		ordPorCreacion= new OrdenamientoPorCreacion();
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
	void testEncuestasOrdenadasPorCreacion() {
		List<Proyecto> proyMockInv1= new ArrayList<>();
		List<Encuesta> encuestasProy1=new ArrayList<>();
		List<Encuesta> encuestasProy2=new ArrayList<>();
		List<Date> fechasProy1= new ArrayList<>();
		List<Date> fechasProy2= new ArrayList<>();
		Date date1=new Date(1,1,1);
		Date date2=new Date(2,2,2);
		Date date3=new Date(3,3,3); 
		Date date4=new Date(4,4,4);
		proyMockInv1.add(mockProy1);
		proyMockInv1.add(mockProy2);
		encuestasProy1.add(mockEnc1);
		encuestasProy1.add(mockEnc2);
		encuestasProy2.add(mockEnc3);
		encuestasProy2.add(mockEnc4);
		encuestasOrdenadas.add(mockEnc4);
		encuestasOrdenadas.add(mockEnc3);
		encuestasOrdenadas.add(mockEnc2);
		encuestasOrdenadas.add(mockEnc1);
		fechasProy1.add(date1);
		fechasProy1.add(date2); 
		fechasProy2.add(date3);
		fechasProy2.add(date4); 
		app.crearCuentaInvestigador(mockInv1);
		when(mockInv1.getProyectos()).thenReturn(proyMockInv1);
		when(mockProy1.obtenerEncuestas()).thenReturn(encuestasProy1);
		when(mockProy2.obtenerEncuestas()).thenReturn(encuestasProy2);
		when(mockProy1.fechaDeEncuestas()).thenReturn(fechasProy1);
		when(mockProy2.fechaDeEncuestas()).thenReturn(fechasProy2);
		when(mockEnc1.getDateCreacion()).thenReturn(date1);
		when(mockEnc2.getDateCreacion()).thenReturn(date2);
		when(mockEnc3.getDateCreacion()).thenReturn(date3);
		when(mockEnc4.getDateCreacion()).thenReturn(date4);
		assertThat(ordPorCreacion.ordenar(app),is(encuestasOrdenadas));
	} 

}
