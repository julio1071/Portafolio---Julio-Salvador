public class Usuario extends Persona {

    public Usuario(String nombre, String id) {
        super(nombre, id);
    }

    @Override
    public String getRol() {
        return "Usuario";
    }
}
