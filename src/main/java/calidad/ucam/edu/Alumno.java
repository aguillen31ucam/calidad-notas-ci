package calidad.ucam.edu;

public class Alumno {

    private String nombre;
    private int[] notas;
    private int asistencia; // 0-100
    private int entregas;   // 0-100

    public Alumno(String nombre, int[] notas, int asistencia, int entregas) {
        this.nombre = nombre;
        this.notas = notas;
        this.asistencia = asistencia;
        this.entregas = entregas;
    }

    public String getNombre() {
        return nombre;
    }

    public int[] getNotas() {
        return notas;
    }

    public int getAsistencia() {
        return asistencia;
    }

    public int getEntregas() {
        return entregas;
    }

    /**
     * Calcula la media de las notas.
     * Si las notas no son válidas, devuelve -1.
     */
    public double getMedia() {
        if (!ValidarNotas.notasValidas(notas)) {
            return -1;
        }

        double suma = 0;
        for (int nota : notas) {
            suma += nota;
        }
        return suma / notas.length;
    }

    /**
     * Equivalente a calcular_resultado_final() del código en C.
     */
    public ResultadoAlumno calcularResultadoFinal() {

        // Validación de notas
        if (!ValidarNotas.notasValidas(notas)) {
            return ResultadoAlumno.ERROR;
        }

        // Validación de asistencia y entregas
        if (asistencia < 0 || asistencia > 100 ||
            entregas < 0 || entregas > 100) {
            return ResultadoAlumno.ERROR;
        }

        double media = getMedia();
        ResultadoAlumno resultado;

        // Clasificación según la media
        if (media < 5.0) {
            resultado = ResultadoAlumno.SUSPENSO;
        } else if (media < 7.0) {
            resultado = ResultadoAlumno.APROBADO;
        } else if (media < 9.0) {
            resultado = ResultadoAlumno.NOTABLE;
        } else {
            resultado = ResultadoAlumno.SOBRESALIENTE;
        }

     // Reglas según asistencia
        if (asistencia < 50) {
            // Asistencia muy baja - suspenso directo
            resultado = ResultadoAlumno.SUSPENSO;
        } else if (asistencia < 75) {
            // Asistencia justa - baja un nivel si no está suspenso
            if (resultado != ResultadoAlumno.SUSPENSO && resultado != ResultadoAlumno.ERROR) {
                resultado = resultado.bajarNivel();
            }
        }

        // Reglas según entregas de tareas
        if (entregas < 50) {
            // Si entrega pocas tareas y no estaba suspenso, baja un nivel
            if (resultado != ResultadoAlumno.SUSPENSO && resultado != ResultadoAlumno.ERROR) {
                resultado = resultado.bajarNivel();
            }
        }
        return resultado;
    }
}

