package dev.app.rentingCar_boot;

import dev.app.rentingCar_boot.model.InssuranceCia;
import dev.app.rentingCar_boot.repository.InssuranceCiaRepository;
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
    public CommandLineRunner demo(InssuranceCiaRepository repository) {
        return args -> {
            System.out.println("Iniciando CommandLineRunner");
            InssuranceCia cia1 = new InssuranceCia("Mateos");
            cia1.getDelegations().add("Barcelona Office\nCarrer de Balmes 123"); // Corrección de \n y formato
            InssuranceCia savedCia1 = repository.save(cia1);
            System.out.println("Datos de InssuranceCia insertados correctamente.");
            System.out.println("Guardado: " + savedCia1.getName() + " con delegations. ID: " + savedCia1.getId());
            System.out.println("Delegaciones guardadas: " + savedCia1.getDelegations());
        };
    }
}