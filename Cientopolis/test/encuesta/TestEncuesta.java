package encuesta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import encuesta.Disponible;
import encuesta.Encuesta;
import encuesta.Finalizada;
import investigador.Investigador;
import pregunta.Pregunta;
import pregunta.ReferenciasNotificacion;
import protocolo.ConectorPreguntaRespuestas;
import protocolo.Protocolo;
import proyecto.Proyecto;
import respuesta.IControlSiguiente;
import respuesta.Respuesta;
import respuesta.TieneSiguiente;

public class TestEncuesta {

	private Encuesta encuesta;
	
	private Encuesta encuestaSpy;
	
	private Pregunta preguntaMockeadaA;
	private Pregunta preguntaMockeadaB;
	
	private Respuesta respuestaMockeadaA;
	private Respuesta respuestaMockeadaB;
	
	private List<Respuesta> respuestasMockeadas;
	
	@BeforeEach
	public void setUp() {
		
		preguntaMockeadaA = mock(Pregunta.class); 
		preguntaMockeadaB = mock(Pregunta.class);
		
		respuestaMockeadaA = mock(Respuesta.class);
		respuestaMockeadaB = mock(Respuesta.class);
		
		respuestasMockeadas = new ArrayList<Respuesta>();
		respuestasMockeadas.add(respuestaMockeadaA);
		respuestasMockeadas.add(respuestaMockeadaB);
		
		encuesta = new Encuesta(preguntaMockeadaA);
		
		encuestaSpy = spy(encuesta);
		
	}
	
	
	@Test
	public void testConstructorYGettersEncuesta() {
		assertTrue(encuesta.getEstadoActual() instanceof EnEdicion);
		assertTrue(encuesta.getRespuestas().isEmpty());
		assertEquals(new Integer(0), encuesta.cantidadDeRespuestas());
		assertEquals(new Integer(0), encuesta.cantidadDeUsos());
		assertTrue(encuesta.getDateCreacion() instanceof Date);
		Date fechaCreacion = encuesta.getDateCreacion();
		Date nuevaFecha = new Date();
		assertTrue(fechaCreacion.getDay()==nuevaFecha.getDay());
		assertTrue(fechaCreacion.getMinutes()==nuevaFecha.getMinutes());
		assertTrue(fechaCreacion.getSeconds()==nuevaFecha.getSeconds());
	}
	
	
	@Test
	public void testSetPregunta() {

		encuestaSpy.setPregunta(preguntaMockeadaB);
		verify(encuestaSpy, times(1)).getEstadoActual();
		
		
	}
	
	@Test
	public void testPasarASiguienteEstado() {
		assertTrue(encuesta.getEstadoActual() instanceof EnEdicion);
		encuesta.pasarASiguienteEstado();

		assertTrue(encuesta.getEstadoActual() instanceof Disponible);
		encuesta.pasarASiguienteEstado();
		assertTrue(encuesta.getEstadoActual() instanceof Finalizada);
		encuesta.pasarASiguienteEstado();
		assertTrue(encuesta.getEstadoActual() instanceof Finalizada);
	}
	
	@Test
	public void testEstaCerrada() {
		assertFalse(encuesta.estaFinalizada());
		encuesta.pasarASiguienteEstado();
		assertFalse(encuesta.estaFinalizada());
		encuesta.pasarASiguienteEstado();
		assertTrue(encuesta.getEstadoActual() instanceof Finalizada);
		assertTrue(encuesta.estaFinalizada());
		encuesta.pasarASiguienteEstado();
		assertTrue(encuesta.estaFinalizada());
	}
	
	@Test
	public void testCrearProtocolo() {
		
		
		Protocolo nuevoProtocolo = encuestaSpy.crearProtocolo();
		verify(encuestaSpy, times(1)).getEstadoActual();

		
	 	//Testeo con estado Disponible
		encuestaSpy.pasarASiguienteEstado();
		verify(encuestaSpy, times(2)).getEstadoActual();
		
		Protocolo protocoloDeDisponible = encuestaSpy.crearProtocolo();
		verify(encuestaSpy, times(3)).getEstadoActual();
//		verify(estadoDisponibleSpy, times(1)).getPreguntaInicial();
		assertTrue(protocoloDeDisponible instanceof Protocolo);
		
		
		//Testeo con estado Finalizada
		encuestaSpy.pasarASiguienteEstado();
		verify(encuestaSpy, times(4)).getEstadoActual();
		
		Protocolo protocoloDeFinalizada = encuestaSpy.crearProtocolo();
		verify(encuestaSpy, times(5)).getEstadoActual();
	
		
	}
	
	@Test
	public void testResponderAlEstado() {
		Protocolo protocoloMockeado = mock(Protocolo.class);
		ConectorPreguntaRespuestas conectorMockeado = mock(ConectorPreguntaRespuestas.class);
		
		
		//Testeo con estado EnEdicion
		IEstadoEncuesta estadoEnEdicion = encuesta.getEstadoActual();
		estadoEnEdicion.responderPreguntaProtocolo(protocoloMockeado, respuestasMockeadas, conectorMockeado, respuestaMockeadaA, preguntaMockeadaA);
		verify(conectorMockeado, times(0)).registrarPreguntaYRespuestas(preguntaMockeadaA, respuestasMockeadas);
		
		//Testeo con estado Disponible
		encuesta.pasarASiguienteEstado();
		
		IEstadoEncuesta estadoDisponible = encuesta.getEstadoActual();
		
		IControlSiguiente tieneSiguiente = new TieneSiguiente();
		IControlSiguiente spyTieneSiguiente = spy(tieneSiguiente);
		when(respuestaMockeadaA.getControlSiguiente()).thenReturn(spyTieneSiguiente);
		
		when(protocoloMockeado.getEncuesta()).thenReturn(encuesta);
		
		estadoDisponible.responderPreguntaProtocolo(protocoloMockeado, respuestasMockeadas, conectorMockeado, respuestaMockeadaA, preguntaMockeadaA);
		verify(conectorMockeado, times(1)).registrarPreguntaYRespuestas(preguntaMockeadaA, respuestasMockeadas);
		
		//Testeo con estado Finalizada
		encuesta.pasarASiguienteEstado();
		
		IEstadoEncuesta estadoFinalizada = encuesta.getEstadoActual();
		estadoFinalizada.responderPreguntaProtocolo(protocoloMockeado, respuestasMockeadas, conectorMockeado, respuestaMockeadaA, preguntaMockeadaA);
		verify(conectorMockeado, times(1)).registrarPreguntaYRespuestas(preguntaMockeadaA, respuestasMockeadas);
	}
	
	@Test
	public void testGetPreguntaInicialAlEstado() {
		encuestaSpy.pasarASiguienteEstado();
		encuestaSpy.pasarASiguienteEstado();
		
		IEstadoEncuesta estadoFinalizada = encuestaSpy.getEstadoActual();
		Pregunta preguntaInicial = estadoFinalizada.getPreguntaInicial();
		assertEquals(preguntaInicial, preguntaMockeadaA);
	}
	
	@Test
	public void testGetRespuestasAlEstado() {
		List<Protocolo> protocolosMockeados = new ArrayList<Protocolo>();
		Protocolo protocoloMockeado1 = mock(Protocolo.class);
		Protocolo protocoloMockeado2 = mock(Protocolo.class);
		protocolosMockeados.add(protocoloMockeado1);
		protocolosMockeados.add(protocoloMockeado2);
		when(protocoloMockeado1.getRespuestas()).thenReturn(respuestasMockeadas);
		when(protocoloMockeado2.getRespuestas()).thenReturn(new ArrayList<Respuesta>());
		
		//Testeo con estado Disponible
		encuesta.pasarASiguienteEstado();
		
		IEstadoEncuesta estadoDisponible = encuesta.getEstadoActual();
		IEstadoEncuesta estadoDisponibleSpy = spy(estadoDisponible);
		
		List<Respuesta> respuestasDisponible = estadoDisponibleSpy.getRespuestas(protocolosMockeados);
		assertTrue(respuestasDisponible.isEmpty());
		verify(protocoloMockeado1, times(0)).getRespuestas();
		verify(protocoloMockeado2, times(0)).getRespuestas();
		
		//Testeo con estado Finalizada
		encuesta.pasarASiguienteEstado();
		IEstadoEncuesta estadoFinalizada = encuesta.getEstadoActual();
		IEstadoEncuesta estadoFinalizadaSpy = spy(estadoFinalizada);
		
		List<Respuesta> respuestasFinalizada = estadoFinalizadaSpy.getRespuestas(protocolosMockeados);
		assertFalse(respuestasFinalizada.isEmpty());
		verify(protocoloMockeado1, times(1)).getRespuestas();
		verify(protocoloMockeado2, times(1)).getRespuestas();
		assertEquals(respuestasFinalizada, respuestasMockeadas);
		
	}
		
	@Test
	public void testRecibirSubscripcionAProyecto() {
		
		
		
	}
	
	@Test
	public void testResponderPreguntaProtocolo() {
		Protocolo protocoloMockeado = mock(Protocolo.class);
		ConectorPreguntaRespuestas conectorMockeado = mock(ConectorPreguntaRespuestas.class);
		

		IEstadoEncuesta estadoMockeado = mock(Finalizada.class);
		when(encuestaSpy.getEstadoActual()).thenReturn(estadoMockeado);
		
		encuestaSpy.responderPreguntaProtocolo(protocoloMockeado, respuestasMockeadas, conectorMockeado, respuestaMockeadaA, preguntaMockeadaA);
		
		verify(encuestaSpy, times(1)).getEstadoActual();
		verify(estadoMockeado, times(1)).responderPreguntaProtocolo(protocoloMockeado, respuestasMockeadas, conectorMockeado, respuestaMockeadaA, preguntaMockeadaA);
	}
}
