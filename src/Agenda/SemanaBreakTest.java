package Agenda;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SemanaBreakTest {
	
	DiaBreak primer_dia,ultimo_dia,dia_promedio;
	Cita citaHora,citaDosHoras, citaGrande,citaTresHoras;
	SemanaBreak semanaPrimera,semanaUltima;
	
	@Before
	public void setUp() throws DatoException{
		//primer_dia = semanaPrimera.getDia(1);
		//ultimo_dia = new DiaBreak(365);//Recuerda Trabajar con los dias 
		//de la semana
		dia_promedio = new DiaBreak(168);
		citaHora = new Cita("citaHora",1);
		citaDosHoras = new Cita("citaDosHoras",2);
		citaTresHoras = new Cita("citaTresHoras",3);
		citaGrande = new Cita("citaDiezHoras",10);
		semanaPrimera = new SemanaBreak(0);
		semanaUltima = new SemanaBreak(52);		
	}
	
	@Test
	public void excepcionConstructoraNegativa(){
		try{
			new SemanaBreak(-1);
			fail();
		}
		catch(DatoException expected){
			assertEquals("La semana debe tomar valor entre 0 y 52" 
					, expected.getMessage());
		}
	}
	
	@Test
	public void testSemanaBreak() throws DatoException {
		new SemanaBreak(0);
		new SemanaBreak(52);
		for(int i =1 ; i < 52;i++){
			new SemanaBreak(i);
		}
	}

	@Test
	public void testMostrarCita() {//hecho
		semanaPrimera.getDia(1).asignarCita(10, citaHora);
		//"No existe";
		// "Hora no valida";
		//hora:00 Descripción de la Cita
		//assertEquals("“hora:00" + citaHora.getDescripcion(),semanaPrimera.mostrarCita(10, 1));
		assertEquals("Hora no valida",semanaUltima.mostrarCita(8,1));
		assertEquals("No existe",semanaUltima.mostrarCita(9,1));
		assertEquals( 10 + ":00 " + citaHora.getDescripcion() ,semanaPrimera.mostrarCita(10,1));
	}

	@Test
	public void testGetDia() throws DatoException {
		for(int i = 1;i<6;i++)
			assertNotEquals(null,semanaPrimera.getDia(i));
		assertEquals(null,semanaPrimera.getDia(6));
		assertEquals(null,semanaPrimera.getDia(7));
		assertEquals(null,semanaPrimera.getDia(0));
		assertEquals(null,semanaPrimera.getDia(-1));
	}

	@Test
	public void testGetNumeroSemana() {
		assertEquals(0,semanaPrimera.getNumeroSemana());
		assertEquals(52,semanaUltima.getNumeroSemana());
	}

	@Test
	public void testPrimerHueco() {
		fail("Not yet implemented");//hecho
	}

	@Test
	public void testDiaSemana() {
		assertEquals("Lunes",semanaPrimera.diaSemana(0));
		assertEquals("Martes",semanaPrimera.diaSemana(1));
		assertEquals("Miercoles",semanaPrimera.diaSemana(2));
		assertEquals("Jueves",semanaPrimera.diaSemana(3));
		assertEquals("Viernes",semanaPrimera.diaSemana(4));
		assertEquals("No citable",semanaPrimera.diaSemana(5));
		assertEquals("No citable",semanaPrimera.diaSemana(6));
		assertEquals("No citable",semanaPrimera.diaSemana(7));
		assertEquals("No citable",semanaPrimera.diaSemana(-1));
	}

}
