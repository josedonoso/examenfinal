package cl.patrones.examen.auth.domain;

public class Usuario {

    private String username;
    private String rol; // ejemplo: "CLIENTE", "EMPLEADO", "ADMIN"

    public Usuario() {
    }

    public Usuario(String username, String rol) {
        this.username = username;
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    // Método para saber si es empleado
    public boolean esEmpleado() {
        return "EMPLEADO".equalsIgnoreCase(rol);
    }

    // También puedes agregar si es administrador
    public boolean esAdmin() {
        return "ADMIN".equalsIgnoreCase(rol);
    }
}
