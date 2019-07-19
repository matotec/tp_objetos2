package observer;

import pregunta.Pregunta;
import pregunta.ReferenciasNotificacion;
import respuesta.Respuesta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import encuesta.Encuesta;
import investigador.Investigador;

public class TestObserver {
	
	private Observado observableRespuesta;
	private Observado observableEncuesta;
	
	private Observador observer1;
	private Observador observer2;
	
	private ReferenciasNotificacion referenciasMockeadas;
	
	private Pregunta preguntaMockeadaA;
	private Pregunta preguntaMockeadaB;
	
	private Observado spyObservableRespuesta;
	private Observado spyObservableEncuesta;
	
	private Observador spyObserver1;
	private Observador spyObserver2;
	
	@BeforeEach
	public void setUp() {
				
		preguntaMockeadaA = mock(Pregunta.class);
		preguntaMockeadaB = mock(Pregunta.class);
		
		referenciasMockeadas = mock(ReferenciasNotificacion.class);
		
		observableRespuesta = new Respuesta("Mayor de edad", preguntaMockeadaB);
		
		spyObservableRespuesta = spy(observableRespuesta);
		 
		observableEncuesta = new Encuesta(preguntaMockeadaA);
		spyObservableEncuesta = spy(observableEncuesta);
		
		observer1 = new Investigador("Jose");
		observer2 = new Investigador("Juana");
		
		spyObserver1 = spy(observer1);
		spyObserver2 = spy(observer2);
		
		
	}
	
	@Test
	public void testGetYSetReferencias() {
		assertNull(spyObservableRespuesta.getReferencias());
		
		spyObservableRespuesta.setReferenciasYNotificar(referenciasMockeadas);
		
		assertEquals(referenciasMockeadas, spyObservableRespuesta.getReferencias());
	}
	
	
	@Test
	public void testAgregarObservador() {
		spyObservableRespuesta.agregar(spyObserver1);
		
		verifyZeroInteractions(spyObserver1);
		verifyZeroInteractions(spyObserver2);
		
		spyObservableRespuesta.setReferenciasYNotificar(referenciasMockeadas);
		
		verify(spyObserver1, times(1)).actualizar(spyObservableRespuesta);
		verify(spyObserver2, times(0)).actualizar(spyObservableRespuesta);
		
		spyObservableRespuesta.agregar(spyObserver2);
		
		spyObservableRespuesta.setReferenciasYNotificar(referenciasMockeadas);
		
		verify(spyObserver1, times(2)).actualizar(spyObservableRespuesta);
		verify(spyObserver2, times(1)).actualizar(spyObservableRespuesta);
		
		spyObservableRespuesta.agregar(spyObserver2);
		
		spyObservableRespuesta.setReferenciasYNotificar(referenciasMockeadas);
		
		verify(spyObserver1, times(3)).actualizar(spyObservableRespuesta);
		verify(spyObserver2, times(2)).actualizar(spyObservableRespuesta);
		
	}
	
	@Test
	public void testRemoverObservador() {
		
		spyObservableEncuesta.setReferenciasYNotificar(referenciasMockeadas);
		
		verifyZeroInteractions(spyObserver1);
		verifyZeroInteractions(spyObserver2);
		
		observableEncuesta.remover(spyObserver1);
		observableEncuesta.remover(spyObserver2);
		
		verifyZeroInteractions(spyObserver1);
		verifyZeroInteractions(spyObserver2);
		
		spyObservableEncuesta.agregar(spyObserver1);
		
		spyObservableEncuesta.setReferenciasYNotificar(referenciasMockeadas);
		
		observableEncuesta.remover(spyObserver1);
		
		spyObservableEncuesta.setReferenciasYNotificar(referenciasMockeadas);
		
		verify(spyObserver1, times(1)).actualizar(spyObservableEncuesta);
		verify(spyObserver2, times(0)).actualizar(spyObservableEncuesta);
		
		
		
	}
	
}