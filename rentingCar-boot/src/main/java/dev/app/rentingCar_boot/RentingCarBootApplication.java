package dev.app.rentingCar_boot;

import dev.app.rentingCar_boot.model.Car;
import dev.app.rentingCar_boot.model.InssuranceCia;
import dev.app.rentingCar_boot.model.InssuranceContract;
import dev.app.rentingCar_boot.repository.CarRepository;
import dev.app.rentingCar_boot.repository.InssuranceCiaRepository;
import dev.app.rentingCar_boot.repository.InssuranceContractRepository;
import dev.app.rentingCar_boot.utils.PopulateAllTables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class RentingCarBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentingCarBootApplication.class, args);
    }

    @Bean// sirve para que springboot sepa gestionar ese objeto
    @Transactional
    public CommandLineRunner demo(PopulateAllTables populateAllTables, @Autowired CarRepository carRepository, @Autowired InssuranceCiaRepository inssuranceCiaRepository, @Autowired InssuranceContractRepository inssuranceContractRepository) {
        return args -> {
            System.out.println("Empezando a llenar TODAS las tablas...");
            String resultado = populateAllTables.populateAllTables(5); //el 5 es de cada entida
            System.out.println(resultado);

            // Población de InsuranceContract (PRA03)
            List<InssuranceCia> cias = inssuranceCiaRepository.findAll();
            List<Car> cars = carRepository.findAll();

            if (!cias.isEmpty() && !cars.isEmpty()) {
                InssuranceCia cia = cias.get(0); // primera compañía
                Car car = cars.get(0); //  primer coche





        };
    };
}
