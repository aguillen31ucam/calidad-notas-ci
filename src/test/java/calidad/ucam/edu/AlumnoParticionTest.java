package calidad.ucam.edu;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AlumnoParticionTest {

    private int[] notasBase;

    // ***** CICLO DE VIDA PRUEBAS PARTICIÓN *****

    @BeforeClass //se ejecuta una vez al iniciar las pruebas de partición
    public static void setUpBeforeClass() {
        System.out.println("@BeforeClass (partición): inicio pruebas de partición de Alumno.");
    }

    @AfterClass //se ejecuta una vez al finalizar las pruebas de partición
    public static void tearDownAfterClass() {
        System.out.println("@AfterClass (partición): fin pruebas de partición de Alumno.");
    }

    @Before //se ejecuta antes de cada prueba de partición
    public void setUp() {
        System.out.println("@Before (partición): preparando datos base.");
        //notas base que usaremos en varios casos
        notasBase = new int[] {7, 8, 6, 9, 5}; // media = 7.0 (aprobado alto/notable)
    }

    @After //se ejecuta después de cada prueba de partición
    public void tearDown() {
        System.out.println("@After (partición): limpiando notas base.");
        notasBase = null;
    }

    // ***** ATRIBUTO: NOTAS *****

    //En esta prueba probamos la partición del atributo notas:
    // - caso con notas válidas
    // - caso con notas fuera de rango que deben dar error
    @Test
    public void testParticionNotas() {
        //caso 1: notas válidas - debería calcular el resultado normal
        Alumno alumnoOk = new Alumno("Juan", notasBase, 80, 60);
        ResultadoAlumno resOk = alumnoOk.calcularResultadoFinal();
        //con media 7, asistencia 80 y entregas 60, el resultado esperado es NOTABLE
        assertEquals("Notas válidas con resultado incorrecto", ResultadoAlumno.NOTABLE, resOk);

        //caso 2: notas inválidas (nota fuera del rango 0-10) - error
        int[] notasInvalidas = new int[] {11, 5, 7}; //11 es una nota inválida
        Alumno alumnoErr = new Alumno("Ana", notasInvalidas, 80, 60); ResultadoAlumno resErr = alumnoErr.calcularResultadoFinal();

        assertEquals("Notas fuera de rango: ERROR", ResultadoAlumno.ERROR, resErr);
    }

    // ***** ATRIBUTO: ASISTENCIA *****

    //En esta prueba nos centramos solo en el atributo asistencia:
    // - misma media y mismas notas, pero cambiando solo la asistencia
    // - asistencia muy baja (<50) debe suspender aunque las notas sean buenas
    // - asistencia correcta mantiene la calificación alta
    @Test
    public void testParticionAsistencia() {
        int[] notasAltas = new int[] {9, 9, 9, 9, 9}; //media 9.0

        //caso 1: asistencia muy baja - suspenso directo
        Alumno alumnoAsistenciaBaja = new Alumno("Pedro", notasAltas, 40, 100);
        ResultadoAlumno resBaja = alumnoAsistenciaBaja.calcularResultadoFinal();
        assertEquals("Asistencia <50, debería ser SUSPENSO", ResultadoAlumno.SUSPENSO, resBaja);

        //caso 2: misma media pero asistencia buena - debería ser SOBRESALIENTE
        Alumno alumnoAsistenciaBien = new Alumno("Laura", notasAltas, 90, 100);
        ResultadoAlumno resBien = alumnoAsistenciaBien.calcularResultadoFinal();
        assertEquals("Buena asistencia y notas altas, debería ser SOBRESALIENTE", ResultadoAlumno.SOBRESALIENTE, resBien);
    }

    // ***** ATRIBUTO: ENTREGAS *****

    //En esta prueba probamos solo el atributo entregas:
    // - misma media y asistencia, pero cambiando solo el porcentaje de entregas
    // - si las entregas son bajas (<50) se baja un nivel la nota final
    @Test
    public void testParticionEntregas() {
        int[] notasBuenas = new int[] {8, 8, 8, 8, 8}; //media = 8.0 -> NOTABLE
        //caso base: entregas correctas
        Alumno alumnoEntregasOk = new Alumno("Marta", notasBuenas, 90, 100);
        ResultadoAlumno resOk = alumnoEntregasOk.calcularResultadoFinal();
        assertEquals("Entregas correctas, el resultado debería ser NOTABLE", ResultadoAlumno.NOTABLE, resOk);

        //caso entregas bajas: misma media y asistencia, pero entregas <50
        Alumno alumnoEntregasBajas = new Alumno("Sergio", notasBuenas, 90, 40);
        ResultadoAlumno resBajas = alumnoEntregasBajas.calcularResultadoFinal();
        //la regla dice que si entregas<50 y no estaba suspenso se baja un nivel
        //NOTABLE (2) baja a APROBADO (1)
        assertEquals("Pocas entregas, la nota debería bajar un nivel (NOTABLE -> APROBADO)", ResultadoAlumno.APROBADO, resBajas);
    }
}

