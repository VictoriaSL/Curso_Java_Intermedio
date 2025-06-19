package com.bedu.Inventario;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventarioApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ProductoRepository productoRepo) {
		return args -> {
			productoRepo.save(new Producto("Laptop Lenovo", "AMD Ryzen 7, 16GB RAM", 18500.0));
			productoRepo.save(new Producto("Mouse inalámbrico", "Marca Logitech, sensor óptico", 350.0));
			productoRepo.save(new Producto("Monitor LG", "27 pulgadas, Full HD", 4300.0));

			System.out.println("📦 Productos cargados:");
			productoRepo.findAll().forEach(System.out::println);
		};
	}
}