package calidad.ucam.edu;

public class ValidarNotas {

    public static final int MAX_NOTAS = 5;

    /*
     * Comprueba si las notas son válidas:
     *  - array no null
     *  - 1 <= longitud <= MAX_NOTAS
     *  - cada nota entre 0 y 10
     */
    
    public static boolean notasValidas(int[] notas) {

        if (notas == null) {
            return false;
        }

        if (notas.length == 0 || notas.length > MAX_NOTAS) {
            return false;
        }

        for (int nota : notas) {
            if (nota < 0 || nota > 10) {
                return false;
            }
        }

        return true;
    }
}