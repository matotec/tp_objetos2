package protocolo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import encuesta.Disponible;
import encuesta.Encuesta;
import encuesta.Finalizada;
import investigador.Investigador;
import pregunta.Pregunta;
import pregunta.ReferenciasNotificacion;
import proyecto.Proyecto;
import respuesta.IControlSiguiente;
import respuesta.NoTieneSiguiente;
import respuesta.Respuesta;
import respuesta.TieneSiguiente;

class TestProtocolo {
	
	private Protocolo protocolo1;
	private Protocolo protocolo2;
	private Protocolo protocolo3;
	
	private Protocolo spyProtocolo1;
	private Protocolo spyProtocolo2;
	private Protocolo spyProtocolo3;
	
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
	
	private ConectorPreguntaRespuestas conectorMockeado;

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
		
		conectorMockeado = mock(ConectorPreguntaRespuestas.class);
		
		conectorPreguntaRespuestas = new ConectorPreguntaRespuestas();
		
		protocolo1 = new Protocolo(preguntaMockeadaA, encuestaMockeada, conectorPreguntaRespuestas);
		protocolo2 = new Protocolo(preguntaMockeadaB, encuestaMockeada2, conectorPreguntaRespuestas);
		protocolo3 = new Protocolo(preguntaMockeadaA, encuestaMockeada2, conectorMockeado);
		
		spyProtocolo1 = spy(protocolo1);
		spyProtocolo2 = spy(protocolo2);
		spyProtocolo3 = spy(protocolo3);
	}
	
	@Test
	void testSetYGetPreguntaActual() {
		assertEquals(spyProtocolo1.getPreguntaActual(), preguntaMockeadaA);
		spyProtocolo1.setPreguntaActual(preguntaMockeadaB);
		assertEquals(spyProtocolo1.getPreguntaActual(), preguntaMockeadaB);
	}
	
	@Test
	void testGetEncuesta() {
		assertEquals(encuestaMockeada, spyProtocolo1.getEncuesta());
		assertNotEquals(encuestaMockeada2, spyProtocolo1.getEncuesta());
		
		assertEquals(encuestaMockeada2, spyProtocolo2.getEncuesta());
		assertNotEquals(encuestaMockeada, spyProtocolo2.getEncuesta());
	}

	@Test
	void testRegistrarPreguntaYRespuestasConGetRespuestasYConSetEstado() {
//		DireccionDePregunta direccionMockeada = mock(DireccionDePregunta.class);
		Investigador investigador = new Investigador("Sergio");
		Investigador spyInvestigador = spy(investigador);
		Proyecto proyectoMockeado = mock(Proyecto.class);
		
//		when(direccionMockeada.getUnInvestigador()).thenReturn(spyInvestigador);
//		when(direccionMockeada.getUnProyecto()).thenReturn(proyectoMockeado);
		
//		List<DireccionDePregunta> suscripciones = new ArrayList<DireccionDePregunta>();
//		suscripciones.add(direccionMockeada);
		
//		when(encuestaMockeada.getSubscripcionesPorProyecto()).thenReturn(suscripciones);
		
		spyProtocolo1.registrarPreguntaYRespuestas(preguntaMockeadaA, arregloDeRespuestasMockeadasA);
		
//		verify(spyInvestigador, times(1)).recibirNovedades(isA(DireccionDePregunta.class));
		
		assertTrue(spyProtocolo1.getRespuestas().isEmpty());
		
		spyProtocolo1.finalizar();
		
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
		
		verify(encuestaMockeada, times(1)).responderPreguntaProtocolo(
				spyProtocolo1,
				arregloDeRespuestasMockeadasB,
				conectorPreguntaRespuestas,
				respuestaMockeadaC,
				preguntaMockeadaA);
		
		verify(spyProtocolo1, times(0)).irAPreguntaSiguiente();
		
		
		//Testeo con encuesta Disponible
		when(encuestaMockeada2.getEstadoActual()).thenReturn(new Disponible(preguntaMockeadaB));
		when(respuestaMockeadaC.getControlSiguiente()).thenReturn(new TieneSiguiente());
		
		spyProtocolo2.responderPreguntaActual(arregloDeRespuestasMockeadasB);
		
		
		verify(encuestaMockeada2, times(1)).responderPreguntaProtocolo(
				spyProtocolo2,
				arregloDeRespuestasMockeadasB,
				conectorPreguntaRespuestas,
				respuestaMockeadaC,
				preguntaMockeadaB);
		
//		verify(encuestaMockeada2, times(1)).setReferenciasYNotificar(any(ReferenciasNotificacion.class));
//		verify(spyProtocolo2, times(1)).irAPreguntaSiguiente();
		
		
	}
	
	@Test
	void testIrAPreguntaSiguiente() {
		
		//Testeo con pregunta actual no respondida..
		when(conectorMockeado.estaRespondida(preguntaMockeadaA)).thenReturn(false);
		
		spyProtocolo3.irAPreguntaSiguiente();
		
		verify(conectorMockeado, times(1)).estaRespondida(preguntaMockeadaA);
		verify(conectorMockeado, times(0)).getRespuestasDePregunta(any(Pregunta.class));
		
		
		//Testeo con pregunta actual respondida
		IControlSiguiente tieneSiguienteMock = mock(TieneSiguiente.class);
		
		when(conectorMockeado.estaRespondida(preguntaMockeadaA)).thenReturn(true);
		when(conectorMockeado.getRespuestasDePregunta(preguntaMockeadaA)).thenReturn(arregloDeRespuestasMockeadasB);
		when(respuestaMockeadaC.getControlSiguiente()).thenReturn(tieneSiguienteMock);
		
		spyProtocolo3.irAPreguntaSiguiente();
		
		verify(conectorMockeado, times(2)).estaRespondida(preguntaMockeadaA);
		verify(conectorMockeado, times(2)).getRespuestasDePregunta(preguntaMockeadaA);
		verify(respuestaMockeadaC, times(1)).getControlSiguiente();
		verify(tieneSiguienteMock, times(1)).proximaPregunta(respuestaMockeadaC, spyProtocolo3);		
		
	}
	
	@Test
	void testIrAPreguntaAnterior() {
		spyProtocolo1.irAPreguntaAnterior();
		
		verify(preguntaMockeadaA, times(1)).interaccionAnteriorPregunta(spyProtocolo1);
	}
	
	@Test
	void testGetRespuestasDePreguntaAlConector() {
		
		//Testeo con una pregunta que no esta respondida
		ConectorPreguntaRespuestas conectorSpy = spy(conectorPreguntaRespuestas);
		
		when(conectorSpy.estaRespondida(preguntaMockeadaA)).thenReturn(false);
		
		List<Respuesta> respuestasTemp = conectorSpy.getRespuestasDePregunta(preguntaMockeadaA);
		
		verify(conectorSpy, times(1)).estaRespondida(preguntaMockeadaA);
		assertTrue(respuestasTemp.isEmpty());
		
		
		//Testo con una pregunta que si esta respondida
		conectorSpy.registrarPreguntaYRespuestas(preguntaMockeadaB, arregloDeRespuestasMockeadasB);
		
		assertEquals(arregloDeRespuestasMockeadasB, conectorSpy.getRespuestasDePregunta(preguntaMockeadaB));
		verify(conectorSpy, times(1)).estaRespondida(preguntaMockeadaB);
	}
}
