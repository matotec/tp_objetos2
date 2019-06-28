package protocolo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import encuesta.Disponible;
import encuesta.Encuesta;
import encuesta.Finalizada;
import pregunta.Pregunta;
import respuesta.NoTieneSiguiente;
import respuesta.Respuesta;
import respuesta.TieneSiguiente;

class TestProtocolo {
	
	private Protocolo protocolo1;
	private Protocolo protocolo2;
	
	private Protocolo spyProtocolo1;
	private Protocolo spyProtocolo2;
	
	private Pregunta preguntaMockeadaA;
	private Pregunta preguntaMockeadaB;
	
	private Respuesta respuestaMockeadaA;
	private Respuesta respuestaMockeadaB;
	private Respuesta respuestaMockeadaC;
	private Respuesta respuestaMockeadaD;
	
	private List<Respuesta> arregloDeRespuestasMockeadasA;
	private List<Respuesta> arregloDeRespuestasMockeadasB;
	
	private Encuesta encuestaMockeada;
	private Encuesta encuestaMockeada2;
	
	private ConectorPreguntaRespuestas conectorPreguntaRespuestas;

	@BeforeEach
	public void setUp() {
		
		preguntaMockeadaA = mock(Pregunta.class);
		preguntaMockeadaB = mock(Pregunta.class);
		
		respuestaMockeadaA = mock(Respuesta.class);
		respuestaMockeadaB = mock(Respuesta.class);
		respuestaMockeadaC = mock(Respuesta.class);
		respuestaMockeadaD = mock(Respuesta.class);
		
		arregloDeRespuestasMockeadasA = new ArrayList<Respuesta>();
		arregloDeRespuestasMockeadasA.add(respuestaMockeadaA);
		arregloDeRespuestasMockeadasA.add(respuestaMockeadaB);
		
		arregloDeRespuestasMockeadasB = new ArrayList<Respuesta>();
		arregloDeRespuestasMockeadasB.add(respuestaMockeadaC);
		arregloDeRespuestasMockeadasB.add(respuestaMockeadaD);
		
		encuestaMockeada = mock(Encuesta.class);
		encuestaMockeada2 = mock(Encuesta.class);
		
		protocolo1 = new Protocolo(preguntaMockeadaA, encuestaMockeada);
		protocolo2 = new Protocolo(preguntaMockeadaB, encuestaMockeada2);
		
		spyProtocolo1 = spy(protocolo1);
		spyProtocolo2 = spy(protocolo2);
		
		conectorPreguntaRespuestas = new ConectorPreguntaRespuestas();
	}
	
	@Test
	void testSetYGetPreguntaActual() {
		assertEquals(spyProtocolo1.getPreguntaActual(), preguntaMockeadaA);
		spyProtocolo1.setPreguntaActual(preguntaMockeadaB);
		assertEquals(spyProtocolo1.getPreguntaActual(), preguntaMockeadaB);
	}

	@Test
	void testRegistrarPreguntaYRespuestasConGetRespuestasYConSetEstado() {
		spyProtocolo1.registrarPreguntaYRespuestas(preguntaMockeadaA, arregloDeRespuestasMockeadasA);
		
		assertTrue(spyProtocolo1.getRespuestas().isEmpty());
		
		spyProtocolo1.setEstado(new Finalizado());
		
		assertFalse(spyProtocolo1.getRespuestas().isEmpty());
		assertEquals(spyProtocolo1.getRespuestas(), arregloDeRespuestasMockeadasA);
	}
	
//	@Test
//	void testIrAPreguntaSiguiente() {
//		spyProtocolo1.irAPreguntaSiguiente();
//		
////		verifyZeroInteractions(spyProtocolo1);
//	}
	
	@Test
	void testResponderPreguntaActualConIrAPreguntaSiguiente() {
//		when(encuestaMockeada.getEstadoActual()).thenReturn(new Disponible(preguntaMockeadaA));
		
		
		//Testeo con encuesta Finalizada
		when(encuestaMockeada.getEstadoActual()).thenReturn(new Finalizada(preguntaMockeadaA));

		spyProtocolo1.responderPreguntaActual(arregloDeRespuestasMockeadasB);
		
		verify(encuestaMockeada, times(1)).getEstadoActual();
		
		verify(spyProtocolo1, times(0)).irAPreguntaSiguiente();
		
		
		//Testeo con encuesta Disponible
		when(encuestaMockeada2.getEstadoActual()).thenReturn(new Disponible(preguntaMockeadaB));
		when(respuestaMockeadaC.getControlSiguiente()).thenReturn(new TieneSiguiente());
		
		spyProtocolo2.responderPreguntaActual(arregloDeRespuestasMockeadasB);
		
		
		verify(encuestaMockeada2, times(1)).getEstadoActual();
		
		verify(spyProtocolo2, times(1)).irAPreguntaSiguiente();
		
		
	}
	
	@Test
	void testIrAPreguntaAnterior() {
		spyProtocolo1.irAPreguntaAnterior();
		
		verify(preguntaMockeadaA, times(1)).interaccionAnteriorPregunta(spyProtocolo1);
	}
	
	@Test
	void testGetRespuestasDePreguntaAlConector() {
		ConectorPreguntaRespuestas conectorSpy = spy(conectorPreguntaRespuestas);
		
		when(conectorSpy.estaRespondida(preguntaMockeadaA)).thenReturn(false);
		
		List<Respuesta> respuestasTemp = conectorSpy.getRespuestasDePregunta(preguntaMockeadaA);
		
		verify(conectorSpy, times(1)).estaRespondida(preguntaMockeadaA);
		assertTrue(respuestasTemp.isEmpty());
	}
}
