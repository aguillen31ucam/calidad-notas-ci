package calidad.ucam.edu;

public enum ResultadoAlumno {
    ERROR(-1),
    SUSPENSO(0),
    APROBADO(1),
    NOTABLE(2),
    SOBRESALIENTE(3);

    private final int codigo;

    ResultadoAlumno(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }
}
