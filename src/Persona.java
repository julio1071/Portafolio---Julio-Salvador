public abstract class Persona {

    // ENCAPSULAMIENTO: atributos privados
    private String nombre;
    private String id;

    public Persona(String nombre, String id) {
        this.nombre = nombre;
        this.id     = id;
    }

    public String getNombre() {
        return nombre; }

    public String getId()     {
        return id; }


    public abstract String getRol();

    @Override
    public String toString() {
        return "[" + getRol() + "] " + nombre + " (ID: " + id + ")";
    }
}
