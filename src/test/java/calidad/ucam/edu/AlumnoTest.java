package calidad.ucam.edu;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AlumnoTest {

    private Alumno alumno;
    private int[] notasValidas;

    // ***** CICLO DE VIDA DE LAS PRUEBAS *****

    @BeforeClass //se ejecuta una vez al iniciar las pruebas de la clase alumno
    public static void setUpBeforeClass() { 
        System.out.println("@BeforeClass: inicio pruebas de Alumno.");
    }

    @AfterClass //se ejecuta una vez al finalizar las pruebas de la clase alumno
    public static void tearDownAfterClass() {
        System.out.println("@AfterClass: fin pruebas de Alumno.");
    }

    @Before //se ejecuta antes de cada prueba del conjunto
    public void setUp() {
        System.out.println("@Before: creando alumno de ejemplo.");

        notasValidas = new int[] {7, 8, 6, 9, 5}; // media = 7.0
        alumno = new Alumno("Juan", notasValidas, 80, 60);
        //para cada prueba creamos un alumno con media = 7, asistencia = 80 y entregas = 60
        //el resutlado esperado para este alumno es notable
    }

    @After //se ejecuta después de cada prueba dle conjutno
    public void tearDown() {
        System.out.println("@After: limpiando referencias.");
        alumno = null;
        notasValidas = null;
        //se elimina el alumno y sus notas después de la prueba
    }

    // ***** PRUEBAS QUE DEBEN PASAR *****
    // 1) assertEquals - para comparar 2 valores iguales con una diferencia máx de 0.0001, en este caso, media esperada y la media del alumno deben ser iguales para que esta prueba pase
    @Test
    public void testMediaOK() {
        double mediaEsperada = 7.0;
        double mediaObtenida = alumno.getMedia();

        assertEquals("La media debería ser 7.0", mediaEsperada, mediaObtenida, 0.0001);
    }

    // 2) assertTrue - comprueba que una condición es verdadera, en este caso, al estar las notas incializadas correctamente en la función setUp(), está prueba pasa
    @Test
    public void testNotasValidasOK() {
        boolean sonValidas = ValidarNotas.notasValidas(notasValidas);

        assertTrue("Las notas deberían ser válidas", sonValidas);
    }

    // 3) assertNotNull - comprueba que el alumno ha sido inicializado correctamente antes de ejecutar los tests
    @Test
    public void testNoNuloOK() {
        assertNotNull("El alumno debería haber sido inicializado en el setUp", alumno);
    }

    // 4) assertFalse + assertNotSame OK
    @Test
    public void testResultadoReferenciasOK() {
        ResultadoAlumno resultado = alumno.calcularResultadoFinal();

        //el resultado no debe ser sobresaliente, debe ser notabla
        assertFalse("El resultado no debería ser SOBRESALIENTE", resultado == ResultadoAlumno.SOBRESALIENTE);

        //creamos otro alumno distinto
        Alumno otroAlumno = new Alumno("Ana", notasValidas, 80, 60);

        //los objetos de alumno deben tener distintas referencia
        assertNotSame("Los dos alumnos no deberían ser la misma referencia", alumno, otroAlumno);
    }

    // ***** PRUEBAS QUE VAN A FALLAR (A PROPÓSITO) *****

    // 5) assertEquals
    @Test
    public void testMediaErr() {
        double mediaEsperadaIncorrecta = 6.0; // media incorrecta
        double mediaObtenida = alumno.getMedia();

        // la prueba debe fallar porque la media real es 7.0
        assertEquals("Prueba pensada para FALLAR: la media no es 6.0", mediaEsperadaIncorrecta, mediaObtenida, 0.0001);
    }

    // 6) assertNull
    @Test
    public void testNoNuloErr() {
        Alumno otroAlumno = new Alumno("Pepe", notasValidas, 90, 90);

        //la prueba debe fallar porque otroAlumno está inicializado
        assertNull("Prueba pensada para FALLAR: el alumno no es null", otroAlumno);
    }

    // 7) assertSame
    @Test
    public void testReferenciasErr() {
        Alumno otroAlumno = new Alumno("Luis", notasValidas, 80, 60);

        //debe fallar porque otroAlumno y alumno no deben tener la misma referencia
        assertSame("Prueba pensada para FALLAR: no son la misma referencia", alumno, otroAlumno);
    }

    // 8) assertArrayEquals
    @Test
    public void testIntegridadNotasErr() {
        int[] notasEsperadas = {7, 8, 6, 9, 4}; // última nota distinta a propósito

        //falla porque las notas esperadas no coinciden con las definidas en el setup {7, 8, 6, 9, 5}
        assertArrayEquals("Prueba pensada para FALLAR: los arrays de notas no coinciden", notasEsperadas, alumno.getNotas());
    }
}

