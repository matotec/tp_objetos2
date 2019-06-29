package pregunta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import encuesta.Encuesta;
import encuesta.Finalizada;
import pregunta.Pregunta;
import pregunta.PreguntaAbierta;
import pregunta.PreguntaMultiOpcionMultiSeleccion;
import pregunta.PreguntaMultiOpcionSimpleSeleccion;
import protocolo.Protocolo;
import respuesta.IControlSiguiente;
import respuesta.NoTieneSiguiente;
import respuesta.Respuesta;
import respuesta.TieneSiguiente;
 
public class TestPregunta {
	
	private Pregunta preguntaA;
	private Pregunta preguntaB;
	private Pregunta preguntaC;
	
	private Pregunta spyPreguntaA;
	private Pregunta spyPreguntaB;
	private Pregunta spyPreguntaC;
	
	private Respuesta respuestaMockeadaA;
	private Respuesta respuestaMockeadaB;
	private Respuesta respuestaMockeadaC;
	private Respuesta respuestaMockeadaD;
	
	private Respuesta respuestaSinTexto;	
	
	private Encuesta encuestaMockeada;
	
	private Protocolo protocoloMockeado;
	
	private List<Respuesta> arregloDeRespuestasMockeadasA;
	private List<Respuesta> arregloDeRespuestasMockeadasB;
	
	@BeforeEach
	public void setUp() {
		
		respuestaMockeadaA = mock(Respuesta.class);
		respuestaMockeadaB = mock(Respuesta.class);
		respuestaMockeadaC = mock(Respuesta.class);
		respuestaMockeadaD = mock(Respuesta.class);
		
		respuestaSinTexto = mock(Respuesta.class);
		
		arregloDeRespuestasMockeadasA = new ArrayList<Respuesta>();
		arregloDeRespuestasMockeadasA.add(respuestaMockeadaA);
		arregloDeRespuestasMockeadasA.add(respuestaMockeadaB);
		
		arregloDeRespuestasMockeadasB = new ArrayList<Respuesta>();
		arregloDeRespuestasMockeadasB.add(respuestaMockeadaC);
		arregloDeRespuestasMockeadasB.add(respuestaMockeadaD);
		
		preguntaA = new PreguntaAbierta("Que opinion tiene acerca del estado de salud publica actual?", respuestaSinTexto);
		preguntaB = new PreguntaMultiOpcionMultiSeleccion("Elija alguna/s de las opciones, segun su preferencia de horario laboral:", arregloDeRespuestasMockeadasA);
		preguntaC = new PreguntaMultiOpcionSimpleSeleccion("Elija de entre las opciones, su estado civil actual:", arregloDeRespuestasMockeadasB);
		
		spyPreguntaA = spy(preguntaA);
		spyPreguntaB = spy(preguntaB);
		spyPreguntaC = spy(preguntaC);
		
		encuestaMockeada = mock(Encuesta.class);
		
		protocoloMockeado = mock(Protocolo.class);
	}
	
	@Test
	public void testGetTextoPregunta() {
		assertEquals("Que opinion tiene acerca del estado de salud publica actual?", preguntaA.getTexto());
		assertEquals("Elija alguna/s de las opciones, segun su preferencia de horario laboral:", preguntaB.getTexto());
		assertEquals("Elija de entre las opciones, su estado civil actual:", preguntaC.getTexto());
	}
	
	@Test
	public void testGetOpcionesDeRespuestas() {
		//test pregunta A
		assertEquals(1, preguntaA.getOpciones().size());
		assertTrue(preguntaA.getOpciones().contains(respuestaSinTexto));
		
		//tests pregunta B
		assertEquals(2, preguntaB.getOpciones().size());
		assertTrue(preguntaB.getOpciones().contains(respuestaMockeadaA));
		assertTrue(preguntaB.getOpciones().contains(respuestaMockeadaB));
		
		//tests pregunta C
		assertEquals(2, preguntaC.getOpciones().size());
		assertTrue(preguntaC.getOpciones().contains(respuestaMockeadaC));
		assertTrue(preguntaC.getOpciones().contains(respuestaMockeadaD));
	}
	
//	@Test
//	public void testGetYSetRespuestas() {
//		//test pregunta A
//		List<Respuesta> arregloConRespuestaSinTexto = new ArrayList<Respuesta>();
//		arregloConRespuestaSinTexto.add(respuestaSinTexto);
//		
//		assertTrue(spyPreguntaA.getRespuestas().isEmpty());
//		spyPreguntaA.setRespuestas(arregloConRespuestaSinTexto);
//		assertFalse(spyPreguntaA.getRespuestas().isEmpty());
//		assertEquals(arregloConRespuestaSinTexto, spyPreguntaA.getRespuestas());
//		
//		//test pregunta B
//		assertTrue(spyPreguntaB.getRespuestas().isEmpty());
//		spyPreguntaB.setRespuestas(arregloDeRespuestasMockeadasA);
//		assertFalse(spyPreguntaB.getRespuestas().isEmpty());
//		assertEquals(arregloDeRespuestasMockeadasA, spyPreguntaB.getRespuestas());
//		
//		//test pregunta C
//		assertTrue(spyPreguntaC.getRespuestas().isEmpty());
//		spyPreguntaC.setRespuestas(arregloDeRespuestasMockeadasB);
//		assertFalse(spyPreguntaC.getRespuestas().isEmpty());
//		assertEquals(arregloDeRespuestasMockeadasB, spyPreguntaC.getRespuestas());
//	}
	
	@Test
	public void testGetYSetAnteriorPregunta() {
		spyPreguntaB.setAnterior(preguntaA);
		spyPreguntaC.setAnterior(preguntaB);
		
		spyPreguntaA.setAnterior(preguntaB);
		
		verify(spyPreguntaB, times(1)).setAnterior(preguntaA);
		verify(spyPreguntaC, times(1)).setAnterior(preguntaB);
		
		verify(spyPreguntaA, times(1)).setAnterior(preguntaB);
		
		assertEquals(preguntaA, spyPreguntaB.getAnterior());
		assertEquals(preguntaB, spyPreguntaC.getAnterior());
		
		assertEquals(preguntaB, spyPreguntaA.getAnterior());
	}
	
//	@Test
//	public void testSiguientePreguntaSinEstarContestada() {
//		
//		spyPreguntaB.interaccionSiguientePregunta(encuestaMockeada);
//		verifyZeroInteractions(encuestaMockeada);
//		
//	}
	
//	@Test
//	public void testSiguientePreguntaSinSiguientePregunta() {
//		IControlSiguiente noTieneSiguiente = new NoTieneSiguiente();
//		IControlSiguiente spyNoTieneSiguiente = spy(noTieneSiguiente);
//		
//		when(respuestaMockeadaA.getControlSiguiente()).thenReturn(spyNoTieneSiguiente);
//		
//		spyPreguntaB.setRespuestas(arregloDeRespuestasMockeadasA);
//		spyPreguntaB.interaccionSiguientePregunta(encuestaMockeada);
//		verifyZeroInteractions(encuestaMockeada);
//	}
	
//	@Test
//	public void testSiguientePreguntaEstandoContestadaYConSiguientePregunta() {
//		IControlSiguiente tieneSiguiente = new TieneSiguiente();
//		IControlSiguiente spyTieneSiguiente = spy(tieneSiguiente);
//		
//		when(respuestaMockeadaA.getControlSiguiente()).thenReturn(spyTieneSiguiente);
//		when(respuestaMockeadaA.getSiguientePregunta()).thenReturn(preguntaA);
//		
//		spyPreguntaB.setRespuestas(arregloDeRespuestasMockeadasA);
//		spyPreguntaB.interaccionSiguientePregunta(encuestaMockeada);
//		verify(encuestaMockeada, times(1)).setPregunta(preguntaA);
//	}
	
	@Test
	public void testAnteriorPreguntaSinAnteriorPregunta() {
		
		spyPreguntaA.interaccionAnteriorPregunta(protocoloMockeado);
		verifyZeroInteractions(protocoloMockeado);
		
	}
	
	@Test
	public void testAnteriorPreguntaConAnteriorPregunta() {
		
		spyPreguntaA.setAnterior(spyPreguntaB);
		spyPreguntaA.interaccionAnteriorPregunta(protocoloMockeado);
		verify(protocoloMockeado, times(1)).setPreguntaActual(spyPreguntaB);
		
	}
}
