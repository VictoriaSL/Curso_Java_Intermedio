package com.bedu.Inventario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductoCommandLineRunner implements CommandLineRunner {

	private final ProductoRepository productoRepository;

	@Autowired
	public ProductoCommandLineRunner(ProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		// Guarda al menos 4 productos
		productoRepository.save(new Producto("Laptop Lenovo", "Laptop de alta gama", 12500.00));
		productoRepository.save(new Producto("Mouse Logitech", "Mouse óptico", 350.00));
		productoRepository.save(new Producto("Teclado Mecánico", "Teclado mecánico para gaming", 950.00));
		productoRepository.save(new Producto("Monitor", "Monitor 4K", 3200.00));

		// Imprime todos los productos con precio mayor a 500
		System.out.println("📦 Productos con precio mayor a 500:");
		List<Producto> productosCaros = productoRepository.findByPrecioGreaterThan(500);
		productosCaros.forEach(System.out::println);

		// Imprime todos los productos que contengan "lap" en su nombre
		System.out.println("\n🔍 Productos que contienen 'lap':");
		List<Producto> productosLap = productoRepository.findByNombreContainingIgnoreCase("lap");
		productosLap.forEach(System.out::println);

		// Imprime productos con precio entre 400 y 1000
		System.out.println("\n🎯 Productos con precio entre 400 y 1000:");
		List<Producto> productosPrecioMedio = productoRepository.findByPrecioBetween(400, 1000);
		productosPrecioMedio.forEach(System.out::println);

		// Imprime productos cuyo nombre comience con "m" o "M"
		System.out.println("\n📘 Productos cuyo nombre empieza con 'm':");
		List<Producto> productosNombreM = productoRepository.findByNombreStartingWithIgnoreCase("m");
		productosNombreM.forEach(System.out::println);
	}
}