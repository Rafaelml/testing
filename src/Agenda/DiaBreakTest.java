package Agenda;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DiaBreakTest {

	DiaBreak dia, diaAsignado;//crear dia con horas ocupadas
	Cita citaHora,citaDosHoras, citaGrande,citaTresHoras;
	
	@Before
	public void setUp() throws Exception {
		dia = new DiaBreak(5);
		diaAsignado = new DiaBreak(10);
		citaHora = new Cita("citaHora",1);
		citaDosHoras = new Cita("citaDosHoras",2);
		citaTresHoras = new Cita("citaTresHoras",3);
		citaGrande = new Cita("citaDiezHoras",10);	
	}
	
	@Test
	public void ExcepcionConstructoraCeroTest() {
		try {
			new DiaBreak(0);
			fail();
		}
		catch (DatoException expected) {
			assertEquals("El dia debe tomar valor entre 1 y 365" 
				, expected.getMessage());
		}
	}
	
	@Test
	public void ExcepcionConstructoraGrandeTest(){
		try{
			new DiaBreak(366);
			fail();
		}
		catch(DatoException expected){
			assertEquals("El dia debe tomar valor entre 1 y 365" 
					, expected.getMessage());
		}
	}
	
	@Test
	public void testDiaBreak() throws DatoException{
		for(int i = 1; i < 366;i++){
			new DiaBreak(i);
		}
	}
	
	@Test
	public void buscaSlotTest() throws DatoException{//probar casos con horas ocupadas
		assertEquals(-1,dia.buscaSlot(0));
		assertEquals(-1, dia.buscaSlot(10));
		assertEquals(9,dia.buscaSlot(9));
		assertEquals(9,dia.buscaSlot(3));
		dia.asignarCita(9, citaTresHoras);
		dia.asignarCita(17, citaHora);
		assertEquals(12,dia.buscaSlot(4));
		assertEquals(12,dia.buscaSlot(1));
		assertEquals(-1,dia.buscaSlot(7));
		assertEquals(-1,dia.buscaSlot(-5));
		assertEquals(12,dia.buscaSlot(1));
		dia.asignarCita(12, citaTresHoras);
		dia.asignarCita(15, citaDosHoras);
		assertEquals(-1, dia.buscaSlot(1));
		for(int i = 9; i< 18;i++){
			assertEquals(i,diaAsignado.buscaSlot(1));
			diaAsignado.asignarCita(i, citaHora);
			
	}
		
	}
	
	@Test
	public void asignarCitaTest(){
		assertEquals(true,diaAsignado.asignarCita(9, citaHora));
		assertEquals(false,diaAsignado.asignarCita(9, citaHora));
		assertEquals(true,diaAsignado.asignarCita(10, citaHora));
		assertEquals(true,diaAsignado.asignarCita(11, citaDosHoras));
		assertEquals(false,diaAsignado.asignarCita(19, citaGrande));
		assertEquals(true,diaAsignado.asignarCita(13, citaTresHoras));
		assertEquals(false,diaAsignado.asignarCita(14, citaHora));
		assertEquals(false,diaAsignado.asignarCita(16, citaTresHoras));
		assertEquals(true,diaAsignado.asignarCita(16, citaHora));
		assertEquals(false,diaAsignado.asignarCita(17, citaDosHoras));//encontraba error
		assertEquals(true,diaAsignado.asignarCita(17, citaHora));
		assertEquals(false,diaAsignado.asignarCita(1, citaHora));
		assertEquals(false,diaAsignado.asignarCita(-1, citaHora));
		assertEquals(false,diaAsignado.asignarCita(9, citaDosHoras));
	}
	
	@Test
	public void getCitaTest(){		
		assertEquals(null,dia.getCita(0));
		assertEquals(null,dia.getCita(22));
		assertEquals(null,dia.getCita(-5));
		diaAsignado.asignarCita(16, citaDosHoras);
		assertEquals(citaDosHoras,diaAsignado.getCita(16));
		diaAsignado.asignarCita(11, citaDosHoras);
		assertEquals(citaDosHoras,diaAsignado.getCita(11));
		diaAsignado.asignarCita(9, citaDosHoras);
		assertEquals(citaDosHoras, diaAsignado.getCita(9));
	}
	
	@Test
	public void muestraCitaTest() {
		assertEquals("No existe",dia.muestraCita(9));
		assertEquals("Hora no valida",dia.muestraCita(5));
		assertEquals("No existe", dia.muestraCita(17));
		diaAsignado.asignarCita(9, citaTresHoras);
		assertEquals("9:00 " + citaTresHoras.getDescripcion(),diaAsignado.muestraCita(9));
		assertEquals("10:00 " + citaTresHoras.getDescripcion(),diaAsignado.muestraCita(10));
		diaAsignado.asignarCita(10, citaHora);
		assertEquals("10:00 " + citaTresHoras.getDescripcion(),diaAsignado.muestraCita(10));
		diaAsignado.asignarCita(17, citaHora);
		assertEquals("17:00 " + citaHora.getDescripcion(),diaAsignado.muestraCita(17));
		
	}
	
	@Test
	public void getDiaNumeroTest() {
		assertEquals(5,dia.getDiaNumero());
		assertEquals(10,diaAsignado.getDiaNumero());
	}
	
	@Test
	public void validaHoraTest() {
		for(int i = 9;i<18;i++)
			assertEquals(true,dia.validaHora(i));
		assertEquals(false,dia.validaHora(8));
		assertEquals(false,dia.validaHora(18));
	}
	
	@Test
	public void huecoLibreTest() {
		assertEquals(false,dia.huecoLibre(8, 1));
		assertEquals(false,dia.huecoLibre(8, 2));
		assertEquals(false,dia.huecoLibre(16, 3));
		assertEquals(false,dia.huecoLibre(18, 1));
		for(int i = 9; i< 18;i++)
			assertEquals(true,dia.huecoLibre(i, 1));
	}
}
