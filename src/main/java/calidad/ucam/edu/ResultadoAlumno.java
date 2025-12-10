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

    /**
     * Devuelve el resultado inmediatamente inferior.
     * SOBRESALIENTE -> NOTABLE
     * NOTABLE       -> APROBADO
     * APROBADO      -> SUSPENSO
     * SUSPENSO/ERROR se quedan igual.
     */
    public ResultadoAlumno bajarNivel() {
        switch (this) {
            case SOBRESALIENTE:
                return NOTABLE;
            case NOTABLE:
                return APROBADO;
            case APROBADO:
                return SUSPENSO;
            default:
                return this;
        }
    }
}
