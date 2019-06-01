package encuestado;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import encuesta.Encuesta;
//import pregunta.Pregunta;
import respuesta.Respuesta;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
public class TestEncuestado {

	private Encuestado encuestado1;
	private Encuesta mockencuesta1;
	private Respuesta mockResp1;
	private Respuesta mockResp2;
	private Respuesta mockResp3;
	private Respuesta mockResp4;
	private List<Integer> unaSeleccion;
	private List<Integer> variasSelecciones;
	private List<Integer> seleccionTexto;
	private List<Respuesta> opcionesPreg1;
	private List<Respuesta> seleccion1;

	
	
	@BeforeEach
	public void setUp() {
		opcionesPreg1= new ArrayList<Respuesta>();
		mockResp1=mock(Respuesta.class);
		mockResp2=mock(Respuesta.class); 
		mockResp3=mock(Respuesta.class);
		mockResp4=mock(Respuesta.class);
		mockencuesta1= mock(Encuesta.class);
		encuestado1=new Encuestado(mockencuesta1);
		unaSeleccion=new ArrayList<Integer>();
		variasSelecciones=new ArrayList<Integer>();
		seleccionTexto=new ArrayList<Integer>();
		seleccion1= new ArrayList<Respuesta>();
	}
	
	@Test
	public void testOpcionesDePregunta() {
		opcionesPreg1.add(mockResp1);
		opcionesPreg1.add(mockResp2);
		opcionesPreg1.add(mockResp3); 
		opcionesPreg1.add(mockResp4);
		when(mockencuesta1.getOpcionesDePreguntaActual()).thenReturn(opcionesPreg1);
		
		assertTrue(encuestado1.obtenerOpcionesDePregunta().size()==4);
		assertThat(encuestado1.obtenerOpcionesDePregunta(),is(opcionesPreg1));
	}
	
	@Test
	public void testSeleccionTexto() {
		seleccionTexto.add(0);
		opcionesPreg1.add(mockResp1);
		seleccion1.add(mockResp1);
		when(mockencuesta1.getOpcionesDePreguntaActual()).thenReturn(opcionesPreg1);
		encuestado1.seleccionarOpciones(seleccionTexto);
		verify(mockencuesta1).responderPreguntaActual(seleccion1, encuestado1);;
	}
	
	@Test
	public void testSeSeleccionanVariasOpciones() {
		variasSelecciones.add(1);
		variasSelecciones.add(3);
		opcionesPreg1.add(mockResp1);
		opcionesPreg1.add(mockResp2);
		opcionesPreg1.add(mockResp3);
		opcionesPreg1.add(mockResp4);
		seleccion1.add(mockResp2);
		seleccion1.add(mockResp4);
	
		
		when(mockencuesta1.getOpcionesDePreguntaActual()).thenReturn(opcionesPreg1);
		encuestado1.seleccionarOpciones(variasSelecciones);
		verify(mockencuesta1).responderPreguntaActual(seleccion1, encuestado1);;
	}
	
	@Test
	public void testSeSeleccionaUnaOpcion() {
		unaSeleccion.add(3);
		opcionesPreg1.add(mockResp1);
		opcionesPreg1.add(mockResp2);
		opcionesPreg1.add(mockResp3);
		opcionesPreg1.add(mockResp4);
		seleccion1.add(mockResp4);
		
		when(mockencuesta1.getOpcionesDePreguntaActual()).thenReturn(opcionesPreg1);
		encuestado1.seleccionarOpciones(unaSeleccion);
		verify(mockencuesta1).responderPreguntaActual(seleccion1, encuestado1);;
	}
	
	@Test
	public void testVerQueSeEnviaLaRespuestaALaEncuesta() {
		opcionesPreg1.add(mockResp1);
		opcionesPreg1.add(mockResp2);
		encuestado1.responderPreguntaActual(opcionesPreg1);
		verify(mockencuesta1).responderPreguntaActual(opcionesPreg1, encuestado1);
	}
	
	@Test
	public void testVerQueSeRespondePreguntaConUnicaSeleccion() {
		encuestado1.escribirTextoRespuesta("vivo en Don Bosco");
 		assertThat (encuestado1.getTextoRespuesta(),is( "vivo en Don Bosco"));		
	}
		
}
 