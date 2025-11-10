package dev.app.rentingCar_boot;

import dev.app.rentingCar_boot.model.InssuranceCia;
import dev.app.rentingCar_boot.repository.InssuranceCiaRepository;
import dev.app.rentingCar_boot.utils.PopulateAllTables;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class RentingCarBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentingCarBootApplication.class, args);
    }

    @Bean
    @Transactional
    public CommandLineRunner demo(PopulateAllTables populateAllTables) {
        return args -> {
            System.out.println("Empezando a llenar TODAS las tablas...");
            String resultado = populateAllTables.populateAllTables(5); //el 5 es de cada entida
            System.out.println(resultado);
        };
    }
}