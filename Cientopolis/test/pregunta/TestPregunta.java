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
import investigador.Investigador;
import pregunta.Pregunta;
import pregunta.PreguntaAbierta;
import pregunta.PreguntaMultiOpcionMultiSeleccion;
import pregunta.PreguntaMultiOpcionSimpleSeleccion;
import protocolo.Protocolo;
import proyecto.Proyecto;
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
	
	private ReferenciasNotificacion referenciasMockeadas;
	private ReferenciasNotificacion referenciasNotificacion;
	
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
		
		referenciasMockeadas = mock(ReferenciasNotificacion.class);
		referenciasNotificacion = new ReferenciasNotificacion(encuestaMockeada, spyPreguntaA, arregloDeRespuestasMockeadasA);
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
	
	@Test
	public void testReferenciasNotificacionGettersYSetters() {
		
		assertEquals(encuestaMockeada, referenciasNotificacion.getEncuesta());
		assertEquals(spyPreguntaA, referenciasNotificacion.getPregunta());
		assertEquals(arregloDeRespuestasMockeadasA, referenciasNotificacion.getRespuestas());
		
		Encuesta encuestaMockeada2 = mock(Encuesta.class);
		
		referenciasNotificacion.setEncuesta(encuestaMockeada2);
		referenciasNotificacion.setPregunta(spyPreguntaB);
		referenciasNotificacion.setRespuestas(arregloDeRespuestasMockeadasB);
		
		assertEquals(encuestaMockeada2, referenciasNotificacion.getEncuesta());
		assertEquals(spyPreguntaB, referenciasNotificacion.getPregunta());
		assertEquals(arregloDeRespuestasMockeadasB, referenciasNotificacion.getRespuestas());
		
	}
	
//	@Test
//	public void testGettersYSettersDireccionDePregunta() {
//		Investigador investigadorMockeado = mock(Investigador.class);
//		Proyecto proyectoMockeado = mock(Proyecto.class);
//		DireccionDePregunta direccionDePreg = new DireccionDePregunta(investigadorMockeado, proyectoMockeado, encuestaMockeada);
//		DireccionDePregunta direccionSpy = spy(direccionDePreg);
//		assertEquals(direccionSpy.getUnInvestigador(), investigadorMockeado);
//		assertEquals(direccionSpy.getUnProyecto(), proyectoMockeado);
//		assertEquals(direccionSpy.getUnaEncuesta(), encuestaMockeada);
//		
//		Encuesta encuestaMockeada2 = mock(Encuesta.class);
//		
//		direccionSpy.setUnaEncuesta(encuestaMockeada2);
//		assertEquals(direccionSpy.getUnaEncuesta(), encuestaMockeada2);
//		
//		direccionSpy.setRespuestas(arregloDeRespuestasMockeadasA);
//		assertEquals(direccionSpy.getRespuestas(), arregloDeRespuestasMockeadasA);
//		
//		direccionSpy.setPregunta(spyPreguntaA);
//		assertEquals(direccionSpy.getPregunta(), spyPreguntaA);
//		
//		
//	}
	
//	@Test
//	public void testRespondidaProximaPregunta() {
//		IEstadoPregunta respondida = new Respondida();
//		IEstadoPregunta spyRespondida = spy(respondida);
//		
//		IControlSiguiente noTieneSiguiente = new NoTieneSiguiente();
//		IControlSiguiente spyNoTieneSiguiente = spy(noTieneSiguiente);
//		
//		when(respuestaMockeadaA.getControlSiguiente()).thenReturn(spyNoTieneSiguiente);
//		
//		spyPreguntaB.setRespuestas(arregloDeRespuestasMockeadasA);
//		
//		spyRespondida.proximaPregunta(spyPreguntaB, encuestaMockeada);
//		verify(spyPreguntaB, times(2)).getRespuestas();
//		verify(respuestaMockeadaA, times(1)).getControlSiguiente();
//		verify(spyNoTieneSiguiente, times(1)).proximaPregunta(respuestaMockeadaA, encuestaMockeada);
//	}
	
//	@Test
//	public void testResponderPreguntaAbiertaSinSiguiente() {
//		List<Respuesta> arregloConRespuestaSinTexto = new ArrayList<Respuesta>();
//		arregloConRespuestaSinTexto.add(respuestaSinTexto);
//		
//		IControlSiguiente noTieneSiguiente = new NoTieneSiguiente();
//		IControlSiguiente spyNoTieneSiguiente = spy(noTieneSiguiente);
//		
//		doCallRealMethod().when(spyNoTieneSiguiente).responder(encuestaMockeada);
//		when(respuestaSinTexto.getControlSiguiente()).thenReturn(spyNoTieneSiguiente);
//		when(encuestadoMockeado.getTextoRespuesta()).thenReturn("Excelente");
//		doCallRealMethod().when(spyPreguntaA).responder(arregloConRespuestaSinTexto, encuestadoMockeado, encuestaMockeada);
//		
//		spyPreguntaA.responder(arregloConRespuestaSinTexto, encuestadoMockeado, encuestaMockeada);
//		
//		verify(respuestaSinTexto, times(1)).setTexto("Excelente");
//		
//		verify(spyPreguntaA, times(1)).setRespuestas(arregloConRespuestaSinTexto);
//		verify(encuestaMockeada, times(1)).agregarRespuesta(respuestaSinTexto);
//		verify(spyNoTieneSiguiente, times(1)).responder(encuestaMockeada);
//		verify(encuestaMockeada, times(1)).setEstado(isA(Finalizada.class));
//	}
	
//	@Test
//	public void testResponderPreguntaAbiertaConSiguiente() {
//		List<Respuesta> arregloConRespuestaSinTexto = new ArrayList<Respuesta>();
//		arregloConRespuestaSinTexto.add(respuestaSinTexto);
//		
//		IControlSiguiente tieneSiguiente = new TieneSiguiente();
//		IControlSiguiente spyTieneSiguiente = spy(tieneSiguiente);
//		
//		when(respuestaSinTexto.getControlSiguiente()).thenReturn(spyTieneSiguiente);
//		when(encuestadoMockeado.getTextoRespuesta()).thenReturn("Excelente");
//		doCallRealMethod().when(spyPreguntaA).responder(arregloConRespuestaSinTexto, encuestadoMockeado, encuestaMockeada);
//		
//		spyPreguntaA.responder(arregloConRespuestaSinTexto, encuestadoMockeado, encuestaMockeada);
//		
//		verify(respuestaSinTexto, times(1)).setTexto("Excelente");
//		
//		verify(spyPreguntaA, times(1)).setRespuestas(arregloConRespuestaSinTexto);
//		verify(encuestaMockeada, times(1)).agregarRespuesta(respuestaSinTexto);
//		verify(spyTieneSiguiente, times(1)).responder(encuestaMockeada);
//		verify(encuestaMockeada, times(0)).setEstado(isA(Finalizada.class));
//	}
	
//	@Test
//	public void testResponderPreguntaMultiOpcMultiSelSinSiguiente() {
//		
//		IControlSiguiente noTieneSiguiente = new NoTieneSiguiente();
//		IControlSiguiente spyNoTieneSiguiente = spy(noTieneSiguiente);
//		
//		doCallRealMethod().when(spyNoTieneSiguiente).responder(encuestaMockeada);
//		when(respuestaMockeadaA.getControlSiguiente()).thenReturn(spyNoTieneSiguiente);
//		when(respuestaMockeadaB.getControlSiguiente()).thenReturn(spyNoTieneSiguiente);
//		doCallRealMethod().when(spyPreguntaB).responder(arregloDeRespuestasMockeadasA, encuestadoMockeado, encuestaMockeada);
//		
//		spyPreguntaB.responder(arregloDeRespuestasMockeadasA, encuestadoMockeado, encuestaMockeada);
//		
//		verify(respuestaMockeadaA, times(0)).setTexto(any(String.class));
//		verify(encuestadoMockeado, times(0)).getTextoRespuesta();
//		
//		verify(spyPreguntaB, times(1)).setRespuestas(arregloDeRespuestasMockeadasA);
//		verify(encuestaMockeada, times(1)).agregarRespuesta(respuestaMockeadaA);
//		verify(encuestaMockeada, times(1)).agregarRespuesta(respuestaMockeadaB);
//		verify(spyNoTieneSiguiente, times(1)).responder(encuestaMockeada);
//		verify(encuestaMockeada, times(1)).setEstado(isA(Finalizada.class));
//		
//	}
	
//	@Test
//	public void testResponderPreguntaMultiOpcSimpleSelConSiguiente() {
//		List<Respuesta> arregloConRtaMockA = new ArrayList<Respuesta>();
//		arregloConRtaMockA.add(respuestaMockeadaC);
//		
//		IControlSiguiente tieneSiguiente = new TieneSiguiente();
//		IControlSiguiente spyTieneSiguiente = spy(tieneSiguiente);
//		
//		when(respuestaMockeadaC.getControlSiguiente()).thenReturn(spyTieneSiguiente);
//		doCallRealMethod().when(spyPreguntaC).responder(arregloConRtaMockA, encuestadoMockeado, encuestaMockeada);
//		
//		spyPreguntaC.responder(arregloConRtaMockA, encuestadoMockeado, encuestaMockeada);
//		
//		verify(respuestaMockeadaC, times(0)).setTexto(any(String.class));
//		verify(encuestadoMockeado, times(0)).getTextoRespuesta();
//		
//		verify(spyPreguntaC, times(1)).setRespuestas(arregloConRtaMockA);
//		verify(encuestaMockeada, times(1)).agregarRespuesta(respuestaMockeadaC);
//		verify(spyTieneSiguiente, times(1)).responder(encuestaMockeada);
//		verify(encuestaMockeada, times(0)).setEstado(isA(Finalizada.class));
//		
//	}
}
