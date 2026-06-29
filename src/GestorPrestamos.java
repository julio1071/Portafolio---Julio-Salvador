public class GestorPrestamos {

    private Catalogo catalogo;       // composición
    private Prestamo[] prestamos;    // agregación
    private int        numPrestamos;

    public GestorPrestamos(int capacidadCatalogo, int capacidadPrestamos) {
        this.catalogo      = new Catalogo(capacidadCatalogo);
        this.prestamos     = new Prestamo[capacidadPrestamos];
        this.numPrestamos  = 0;
    }

    public void registrarLibro(Libro libro) {
        catalogo.agregarLibro(libro);
    }


    public void prestarLibro(String isbn, Usuario usuario, String fecha) {
        Libro libro = catalogo.buscarPorIsbn(isbn);

        if (libro == null) {
            System.out.println("  ERROR: no existe libro con ISBN " + isbn);
            return;
        }
        if (!libro.isDisponible()) {
            System.out.println("  ERROR: \"" + libro.getTitulo() + "\" ya está prestado.");
            return;
        }
        if (numPrestamos >= prestamos.length) {
            System.out.println("  ERROR: límite de préstamos alcanzado.");
            return;
        }

        Prestamo p = new Prestamo(libro, usuario, fecha);
        prestamos[numPrestamos] = p;
        numPrestamos++;
        System.out.println("  OK: " + p);
    }

    public void devolverLibro(String isbn) {
        for (int i = 0; i < numPrestamos; i++) {
            Libro l = prestamos[i].getLibro();
            if (l.getIsbn().equals(isbn) && !l.isDisponible()) {
                l.setDisponible(true);
                System.out.println("  Devuelto: \"" + l.getTitulo()
                        + "\" por " + prestamos[i].getUsuario().getNombre());
                return;
            }
        }
        System.out.println("  No se encontró préstamo activo para ISBN: " + isbn);
    }

    public void mostrarCatalogo() {
        catalogo.mostrarLibros();
    }

    public void mostrarPrestamos() {
        if (numPrestamos == 0) {
            System.out.println("  No hay préstamos registrados.");
            return;
        }
        for (int i = 0; i < numPrestamos; i++) {
            System.out.println("  " + (i + 1) + ". " + prestamos[i]);
        }
    }
}
