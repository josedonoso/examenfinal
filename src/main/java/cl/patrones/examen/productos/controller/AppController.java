package cl.patrones.examen.productos.controller;

import cl.patrones.examen.descuentos.DescuentoService;
import cl.patrones.examen.productos.domain.*;
import cl.patrones.examen.auth.domain.Usuario;
import cl.patrones.examen.productos.service.ProductoService;
import cl.patrones.examen.auth.service.UsuarioService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AppController {

	private final ProductoService productoService;
	private final DescuentoService descuentoService;
	private final UsuarioService usuarioService;

	public AppController(ProductoService productoService,
			DescuentoService descuentoService,
			UsuarioService usuarioService) {
		this.productoService = productoService;
		this.descuentoService = descuentoService;
		this.usuarioService = usuarioService;
	}

	@GetMapping({ "/", "/inicio" })
	public String mostrarInicio(Model model,
			@AuthenticationPrincipal UserDetails userDetails) {

		Usuario usuario = obtenerUsuarioActual(userDetails);
		System.out.println("Usuario logueado: " + usuario.getUsername());
		System.out.println("Rol del usuario: " + usuario.getRol());
		List<Producto> productosOriginales = new ArrayList<>(productoService.getProductos());

		List<ProductoConDescuento> productos = productosOriginales.stream()
				.map(p -> {
					DescuentoService.ResultadoDescuento resultado = descuentoService.calcularDescuentoConDetalle(p,
							usuario);
					return new ProductoConDescuento(p, resultado.getPrecioFinal(), resultado.getPorcentajeDescuento());
				})
				.toList();

		model.addAttribute("productos", productos);
		System.out.println("============/ CONTROLADOR /INICIO ==================");
		System.out.println("Usuario: " + usuario.getUsername() + " - Rol: " + usuario.getRol());
		productos.forEach(p -> System.out.println(p.getNombre() + ": $" + p.getPrecioFinal()));
		System.out.println("============/ CONTROLADOR /INICIO ==================");
		return "includes/avisos";
	}

	private Usuario obtenerUsuarioActual(UserDetails userDetails) {
		if (userDetails == null) {
			return new Usuario("anonimo", "CLIENTE");
		}
		return usuarioService.buscarPorUsername(userDetails.getUsername());
	}
}
