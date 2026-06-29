public class Prestamo {

    private Libro   libro;
    private Usuario usuario;
    private String  fecha;

    public Prestamo(Libro libro, Usuario usuario, String fecha) {
        this.libro   = libro;
        this.usuario = usuario;
        this.fecha   = fecha;


        libro.setDisponible(false);
    }

    public Libro   getLibro()   {
        return libro; }

    public Usuario getUsuario() {
        return usuario; }

    public String  getFecha()   {
        return fecha; }

    @Override
    public String toString() {
        return libro.getTitulo() + " → " + usuario.getNombre() + " (fecha: " + fecha + ")";
    }
}
