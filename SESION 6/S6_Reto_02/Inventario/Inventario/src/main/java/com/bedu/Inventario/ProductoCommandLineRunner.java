package com.bedu.Inventario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductoCommandLineRunner implements CommandLineRunner {

	private final ProductoRepository productoRepository;
	private final MarcaRepository marcaRepository;

	@Autowired
	public ProductoCommandLineRunner(ProductoRepository productoRepository, MarcaRepository marcaRepository) {
		this.productoRepository = productoRepository;
		this.marcaRepository = marcaRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		// Crea marcas
		Marca apple = new Marca("Apple");
		Marca samsung = new Marca("Samsung");
		marcaRepository.save(apple);
		marcaRepository.save(samsung);

		// Crea productos y los asocia a marcas
		productoRepository.save(new Producto("iPhone 15", "Teléfono de alta gama", 999.99, apple));
		productoRepository.save(new Producto("iPad Pro", "Tableta de alta gama", 799.99, apple));
		productoRepository.save(new Producto("Galaxy S23", "Teléfono de alta gama", 899.99, samsung));
		productoRepository.save(new Producto("Smart TV", "Televisor inteligente", 1299.99, samsung));

		// Muestra los productos agrupados por marca
		System.out.println("📚 Productos por marca:");
		marcaRepository.findAll().forEach(marca -> {
			System.out.println("🏷️ " + marca.getNombre() + ":");
			productoRepository.findAll().stream()
					.filter(p -> p.getMarca().getId().equals(marca.getId()))
					.forEach(p -> System.out.println("   - " + p.getNombre()));
		});
	}
}