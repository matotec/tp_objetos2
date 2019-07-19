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
	
}
