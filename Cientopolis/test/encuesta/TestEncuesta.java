package encuesta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import encuesta.Disponible;
import encuesta.Encuesta;
import encuesta.Finalizada;
import encuestado.Encuestado;
import pregunta.Pregunta;
import respuesta.Respuesta;

public class TestEncuesta {

	private Encuesta encuesta;
	
	private Pregunta preguntaMockeadaA;
	private Pregunta preguntaMockeadaB;
	
	private Respuesta respuestaMockeadaA;
	private Respuesta respuestaMockeadaB;
	
	private Encuestado encuestadoMockeado;
	
	@BeforeEach
	public void setUp() {
		
		preguntaMockeadaA = mock(Pregunta.class);
		preguntaMockeadaB = mock(Pregunta.class);
		
		encuestadoMockeado = mock(Encuestado.class);
		
		encuesta = new Encuesta(preguntaMockeadaA);
		
	}
	
	@Test
	public void testConstructorYGettersEncuesta() {
		assertEquals(preguntaMockeadaA, encuesta.getPreguntaActual());
		assertTrue(encuesta.getEstadoActual() instanceof Disponible);
		assertTrue(encuesta.getRespuestas().isEmpty());
		assertTrue(encuesta.getRespuestasPrivado().isEmpty());
		assertEquals(new Integer(0), encuesta.cantidadDeRespuestas());
		assertEquals(new Integer(0), encuesta.cantidadDeRespuestasPrivado());
	}
	
	@Test
	public void testSetters() {
		assertEquals(preguntaMockeadaA, encuesta.getPreguntaActual());
		encuesta.setPregunta(preguntaMockeadaB);
		assertEquals(preguntaMockeadaB, encuesta.getPreguntaActual());
		
		assertTrue(encuesta.getEstadoActual() instanceof Disponible);
		encuesta.setEstado(new Finalizada());
		assertTrue(encuesta.getEstadoActual() instanceof Finalizada);
	}
	
	@Test
	public void testAgregarRespuesta() {
		assertTrue(encuesta.getRespuestas().isEmpty());
		assertEquals(new Integer(0), encuesta.cantidadDeRespuestas());
		
		assertTrue(encuesta.getRespuestasPrivado().isEmpty());
		assertEquals(new Integer(0), encuesta.cantidadDeRespuestasPrivado());
		
		encuesta.agregarRespuesta(respuestaMockeadaA);
		
		assertTrue(encuesta.getRespuestas().isEmpty());
		assertEquals(new Integer(0), encuesta.cantidadDeRespuestas());
		
		assertFalse(encuesta.getRespuestasPrivado().isEmpty());
		assertEquals(new Integer(1), encuesta.cantidadDeRespuestasPrivado());
	}
	
	@Test
	public void testEstadoFinalizada() {
		assertTrue(encuesta.getRespuestas().isEmpty());
		assertEquals(new Integer(0), encuesta.cantidadDeRespuestas());
		
		encuesta.agregarRespuesta(respuestaMockeadaA);
		encuesta.agregarRespuesta(respuestaMockeadaB);
		encuesta.setEstado(new Finalizada());
		
		assertFalse(encuesta.getRespuestas().isEmpty());
		assertEquals(new Integer(2), encuesta.cantidadDeRespuestas());
	}
	
	@Test
	public void testIrAPreguntaAnterior() {
		encuesta.irAPreguntaAnterior();
		verify(preguntaMockeadaA, times(1)).interaccionAnteriorPregunta(encuesta);
	}
	
	@Test
	public void testIrAPreguntaSiguiente() {
		encuesta.irAPreguntaSiguiente();
		verify(preguntaMockeadaA, times(1)).interaccionSiguientePregunta(encuesta);
	}
	
	@Test
	public void testResponderPreguntaActual() {
		List<Respuesta> respuestasMockeadas = new ArrayList<Respuesta>();
		respuestasMockeadas.add(respuestaMockeadaA);
		respuestasMockeadas.add(respuestaMockeadaB);
		
		encuesta.responderPreguntaActual(respuestasMockeadas, encuestadoMockeado);
		
		verify(preguntaMockeadaA, times(1)).responder(respuestasMockeadas, encuestadoMockeado, encuesta);
	}
	
	@Test
	public void testGetOpcionesDePreguntaActual() {
		encuesta.getOpcionesDePreguntaActual();
		verify(preguntaMockeadaA, times(1)).getOpciones();
	}
}
