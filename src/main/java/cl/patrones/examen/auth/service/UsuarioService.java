package cl.patrones.examen.auth.service;

import cl.patrones.examen.auth.domain.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    // Simula una base de datos con usuarios
    private final List<Usuario> usuarios = List.of(
        new Usuario("juan", "CLIENTE"),
        new Usuario("andrea", "EMPLEADA")
    );

    // Busca un usuario por su username
    public Usuario buscarPorUsername(String username) {
        return usuarios.stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElse(null);
    }
}
