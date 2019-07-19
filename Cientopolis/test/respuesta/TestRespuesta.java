package respuesta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import encuesta.Encuesta;
import encuesta.Finalizada;
import pregunta.Pregunta;
import protocolo.Finalizado;
import protocolo.IEstadoProtocolo;
import protocolo.Protocolo;
import respuesta.NoTieneSiguiente;
import respuesta.Respuesta;
import respuesta.TieneSiguiente;
 
public class TestRespuesta {
	
	private Respuesta respuestaA;
	private Respuesta respuestaB;
	private Respuesta respuestaC;
	
	private Pregunta preguntaMockeadaA;
	private Pregunta preguntaMockeadaB;
	
	private Encuesta encuestaMockeada;
	
	private Protocolo protocoloMockeado;
	
	private Respuesta spyRespuestaA;
	private Respuesta spyRespuestaB;
	private Respuesta spyRespuestaC;
	
	@BeforeEach
	public void setUp() {
		preguntaMockeadaA = mock(Pregunta.class);
		preguntaMockeadaB = mock(Pregunta.class);
		
		
		respuestaA = new Respuesta("");
		respuestaB = new Respuesta("2 ambientes", preguntaMockeadaA);
		respuestaC = new Respuesta("1 ambiente", preguntaMockeadaB);
		
		encuestaMockeada = mock(Encuesta.class);
		
		protocoloMockeado = mock(Protocolo.class);
		
		spyRespuestaA = spy(respuestaA);
		spyRespuestaB = spy(respuestaB);
		spyRespuestaC = spy(respuestaC);
	}
	
	@Test
	public void testGetYSetTextoRespuesta() {
		
		assertEquals("", respuestaA.getTexto());
		assertEquals("2 ambientes", respuestaB.getTexto());
		assertEquals("1 ambiente", respuestaC.getTexto());
		
		respuestaA.setTexto("Muy conforme");
		
		assertEquals("Muy conforme", respuestaA.getTexto());
		
	}

	
	
	@Test
	public void testGetYSetSiguientePreguntaConSiguientePregunta() {
		assertEquals(preguntaMockeadaA, respuestaB.getSiguientePregunta());
		assertEquals(preguntaMockeadaB, respuestaC.getSiguientePregunta());
		
		respuestaA.setSiguientePregunta(preguntaMockeadaA,preguntaMockeadaB);
		
		assertEquals(preguntaMockeadaA, respuestaA.getSiguientePregunta());
	}
	
	@Test
	public void testGetYSetControlSiguiente() { 
		assertTrue(respuestaA.getControlSiguiente() instanceof NoTieneSiguiente);
		assertTrue(respuestaB.getControlSiguiente() instanceof TieneSiguiente);
		assertTrue(respuestaC.getControlSiguiente() instanceof TieneSiguiente);
		
		respuestaA.setControlSiguiente(new TieneSiguiente());

		
		assertTrue(respuestaA.getControlSiguiente() instanceof TieneSiguiente);
		
	}
	
	@Test
	public void testTieneSiguienteProximaPregunta() {
		//test de "proximaPregunta" en respuestaB
		respuestaB.getControlSiguiente().proximaPregunta(respuestaB, protocoloMockeado);
		verify(protocoloMockeado, times(1)).setPreguntaActual(preguntaMockeadaA);
		
		//test de "proximaPregunta" en respuestaC
		respuestaC.getControlSiguiente().proximaPregunta(respuestaC, protocoloMockeado);
		verify(protocoloMockeado, times(1)).setPreguntaActual(preguntaMockeadaB);
	}
	
	@Test
	public void testTieneSiguienteResponder() {
		//test de "responder" en respuestaB
		respuestaB.getControlSiguiente().responder(protocoloMockeado);
		verifyZeroInteractions(protocoloMockeado);
		
		//test de "responder" en respuestaC
		respuestaC.getControlSiguiente().responder(protocoloMockeado);
		verifyZeroInteractions(protocoloMockeado);
	}
	
	@Test
	public void testTieneSiguienteSetAnteriorASiguiente() {
		//test de "setAnteriorASiguiente" en respuestaB		
		spyRespuestaB.getControlSiguiente().setAnteriorASiguiente(preguntaMockeadaB, spyRespuestaB);
		verify(spyRespuestaB, times(1)).getSiguientePregunta();
		verify(preguntaMockeadaA, times(1)).setAnterior(preguntaMockeadaB);
		
		//test de "setAnteriorASiguiente" en respuestaC
		spyRespuestaC.getControlSiguiente().setAnteriorASiguiente(preguntaMockeadaA, spyRespuestaC);
		verify(spyRespuestaC, times(1)).getSiguientePregunta();
		verify(preguntaMockeadaB, times(1)).setAnterior(preguntaMockeadaA);
	}
	
	@Test
	public void testTieneSiguienteSetearComoAnteriorDeLaSiguiente() {
		//test de "setearComoAnteriorDeLaSiguiente" en respuestaB
		spyRespuestaB.setearComoAnteriorDeLaSiguiente(preguntaMockeadaB);
		verify(spyRespuestaB, times(1)).getSiguientePregunta();
		verify(preguntaMockeadaA, times(1)).setAnterior(preguntaMockeadaB);
		
		//test de "setearComoAnteriorDeLaSiguiente" en respuestaC
		spyRespuestaC.setearComoAnteriorDeLaSiguiente(preguntaMockeadaA);
		verify(spyRespuestaC, times(1)).getSiguientePregunta();
		verify(preguntaMockeadaB, times(1)).setAnterior(preguntaMockeadaA);
	}
	
	@Test
	public void testNoTieneSiguienteProximaPregunta() {
		//test de "proximaPregunta" en respuestaA
		respuestaA.getControlSiguiente().proximaPregunta(respuestaA, protocoloMockeado);
		verifyZeroInteractions(protocoloMockeado);
	}
	
	@Test
	public void testNoTieneSiguienteResponder() {
		//test de "responder" en respuestaA
		respuestaA.getControlSiguiente().responder(protocoloMockeado);
		verify(protocoloMockeado, times(1)).finalizar();
	}
	
	@Test
	public void testNoTieneSiguienteSetAnteriorASiguiente() {
		//test de "setAnteriorASiguiente" en respuestaA
		spyRespuestaA.getControlSiguiente().setAnteriorASiguiente(preguntaMockeadaA, spyRespuestaA);
		verify(spyRespuestaA, times(0)).getSiguientePregunta();
	}
	 
	@Test
	public void testNoTieneSiguienteSetearComoAnteriorDeLaSiguiente() {
		//test de "setearComoAnteriorDeLaSiguiente" en respuestaA
		spyRespuestaA.setearComoAnteriorDeLaSiguiente(preguntaMockeadaA);
		verify(spyRespuestaA, times(0)).getSiguientePregunta();
	}
}
