public class Main {

    public static void main(String[] args) {

        System.out.println("=========================================");
        System.out.println("       Biblioteca Salvador S.A          ");
        System.out.println("=========================================\n");

        // 2 Usuarios de ejemplo
        Bibliotecario bibliotecario = new Bibliotecario("Ana López", "B-01");
        Usuario u1 = new Usuario("Carlos Pérez", "U-01");
        Usuario u2 = new Usuario("María Gómez",  "U-02");

        System.out.println("Personal: " + bibliotecario);
        System.out.println("Usuarios: " + u1 + " | " + u2 + "\n");

        // Libros a registrar
        Libro l1 = new Libro("El Principito",           "Antoine de Saint-Exupéry", "978-1");
        Libro l2 = new Libro("Cien Años de Soledad",    "Gabriel García Márquez",   "978-2");
        Libro l3 = new Libro("Don Quijote de la Mancha","Miguel de Cervantes",      "978-3");

        // Revision del catalogo
        GestorPrestamos gestor = new GestorPrestamos(10, 10);

        // REgistro de libros
        System.out.println("--- Registro de libros ---");
        gestor.registrarLibro(l1);
        gestor.registrarLibro(l2);
        gestor.registrarLibro(l3);

        // Monstrar el catalogo
        System.out.println("\n--- Catálogo ---");
        gestor.mostrarCatalogo();

        // Préstamos de libros
        System.out.println("\n--- Solicitudes realizadas ---");
        gestor.prestarLibro("978-1", u1, "2025-06-01");
        gestor.prestarLibro("978-2", u2, "2025-06-02");
        gestor.prestarLibro("978-1", u2, "2025-06-03");

        // Catálogo despues de las solicitudes
        System.out.println("\n--- Catálogo (con solicitudes) ---");
        gestor.mostrarCatalogo();

        // Solicitudes activas
        System.out.println("\n--- Solicitudes registradas ---");
        gestor.mostrarPrestamos();

        // Devolución de libros
        System.out.println("\n--- Devolución de libros ---");
        gestor.devolverLibro("978-1");

        // Catalogo final
        System.out.println("\n--- Catálogo final ---");
        gestor.mostrarCatalogo();

    }
}