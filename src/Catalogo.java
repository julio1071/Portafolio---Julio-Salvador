public class Catalogo {

    private Libro[] libros;
    private int     cantidad;

    public Catalogo(int capacidad) {
        this.libros   = new Libro[capacidad];
        this.cantidad = 0;
    }

    public void agregarLibro(Libro libro) {
        if (cantidad < libros.length) {
            libros[cantidad] = libro;
            cantidad++;
            System.out.println("  + Agregado: " + libro.getTitulo());
        } else {
            System.out.println("  Catálogo lleno, no se puede agregar más libros.");
        }
    }

    public Libro buscarPorIsbn(String isbn) {
        for (int i = 0; i < cantidad; i++) {
            if (libros[i].getIsbn().equals(isbn)) {
                return libros[i];
            }
        }
        return null;
    }

    public void mostrarLibros() {
        if (cantidad == 0) {
            System.out.println("  El catálogo está vacío.");
            return;
        }
        for (int i = 0; i < cantidad; i++) {
            System.out.println("  " + (i + 1) + ". " + libros[i]);
        }
    }
}
