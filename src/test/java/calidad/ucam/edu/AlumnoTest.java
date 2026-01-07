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
        double mediaEsperada = 6.0; //MAL PORQUE MEDIA ESPERADO = 7. CAMBIADO PARA UE FALLE.
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

    // 5) assertEquals - segunda comprobación de media con otro alumno
    @Test
    public void testMediaOtroAlumnoOK() {
        int[] notas = {6, 6, 6, 6, 6}; // media = 6.0
        Alumno alumno2 = new Alumno("Pepe", notas, 80, 60);

        double mediaEsperada = 8.0;
        double mediaObtenida = alumno2.getMedia();

        assertEquals("La media del segundo alumno debería ser 6.0", mediaEsperada, mediaObtenida, 0.0001);
    }


    // 6) assertNotNull - comprobamos que un alumno creado no es null
    @Test
    public void testAlumnoCreadoNoEsNull() {
        Alumno otroAlumno = new Alumno("Pepe", notasValidas, 90, 90);

        assertNotNull("El alumno creado no debería ser null", otroAlumno);
    }

    // 7) assertNotSame - comprobamos que son objetos distintos
    @Test
    public void testReferenciasDistintasOK() {
        Alumno otroAlumno = new Alumno("Luis", notasValidas, 80, 60);

        assertNotSame("Los dos alumnos deberían ser objetos distintos", alumno, otroAlumno);
    }

    // 8) assertArrayEquals - comprobamos que el array de notas es el esperado
    @Test
    public void testIntegridadNotasOK() {
        int[] notasEsperadas = {7, 8, 6, 9, 5}; // coincide con las notas del setUp

        assertArrayEquals("El array de notas del alumno debería coincidir con el esperado",
                notasEsperadas, alumno.getNotas());
    }
}

