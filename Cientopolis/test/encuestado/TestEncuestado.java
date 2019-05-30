package encuestado;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import encuesta.Encuesta;
import pregunta.Pregunta;
import respuesta.Respuesta;

import static org.mockito.Mockito.*;
public class TestEncuestado {

//	@Test
//	public void testObtenerLaPrimerPreguntaDeLaEncuesta() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testObtenerLaSegundaPreguntaDeLaEncuesta() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	public void testObtenerLasRespuestasPosibles() {
//		fail("Not yet implemented");
//	}
	private Encuestado encuestado1;
	private Encuestado encuestado2;
	private Encuesta mockencuesta1;
	private Pregunta mockPreg1;
	private Pregunta mockPreg2;
	private Respuesta mockResp1;
	private Respuesta mockResp2;
	private Respuesta mockResp3;
	private Respuesta mockResp4;
	private List<Integer> unaSeleccion;
	private List<Integer> variasSelecciones;
	private List<Integer> seleccionTexto;
	private List<Respuesta> opcionesPreg1;
	private List<Respuesta> opcionesPreg2;
	private List<Respuesta> seleccion1;
	
	@BeforeEach
	public void setUp() {
		encuestado1=new Encuestado(mockencuesta1);
		mockPreg1=mock(Pregunta.class);
		mockPreg2=mock(Pregunta.class);
		mockResp1=mock(Respuesta.class);
		mockResp2=mock(Respuesta.class);
		mockResp3=mock(Respuesta.class);
		mockResp4=mock(Respuesta.class);
		mockencuesta1= mock(Encuesta.class);
		unaSeleccion=new ArrayList<Integer>();
		variasSelecciones=new ArrayList<Integer>();
		seleccionTexto=new ArrayList<Integer>();
		opcionesPreg1= new ArrayList<Respuesta>();
		opcionesPreg2= new ArrayList<Respuesta>();
		seleccion1= new ArrayList<Respuesta>();
	}
	
	@Test
	public void testOpcionesDePregunta() {
		when(mockencuesta1.getOpcionesDePreguntaActual())thenReturn
		
		encuestado1.obtenerOpcionesDePregunta();
		verify(spyEncuesta).getOpcionesDePreguntaActual();
		
	}
	
//	@Test
//	public void testSeSeleccionanVariasOpciones() {
//		variasSelecciones.add(2);
//		variasSelecciones.add(4);
//		opcionesPreg1.add(mockResp1);
//		opcionesPreg1.add(mockResp2);
//		opcionesPreg1.add(mockResp3);
//		opcionesPreg1.add(mockResp4);
//		seleccion1.add(mockResp2);
//		seleccion1.add(mockResp4);
//		encuestado1.seleccionarOpciones(variasSelecciones);
//		
//		when(mockencuesta1.getOpcionesDePreguntaActual()).thenReturn(opcionesPreg1);
//		
//		verify(mockencuesta1).responderPreguntaActual(seleccion1, encuestado1);;
//	}
	
	@Test
	public void testSeSeleccionaUnaOpcion() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testVerQueSeCargaElStringEnLaPreguntaAbierta() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testVerQueSeRespondePreguntaConMultiplesOpciones() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testVerQueSeRespondePreguntaConUnicaSeleccion() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testObtenerPreguntaAnterior() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testObtenerPreguntaSiguiente() {
		fail("Not yet implemented");
	}
	
}
