package dev.app.rentingCar_boot;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
            Iterable<InssuranceCia> ciasIterable = inssuranceCiaRepository.findAll();
            Iterable<Car> carsIterable = carRepository.findAll();
            List<InssuranceCia> cias = new ArrayList<>();
            List<Car> cars = new ArrayList<>();
            ciasIterable.forEach(cias::add);
            carsIterable.forEach(cars::add);

            if (!cias.isEmpty() && !cars.isEmpty()) {
                InssuranceCia cia = cias.get(0); // Usa la primera compañia
                Car car = cars.get(0); // Usa el primer coche

                InssuranceContract contract1 = new InssuranceContract();
                contract1.setCar(car);
                contract1.setInssuranceCia(cia);
                contract1.setStartDate(LocalDate.now());
                contract1.setEndDate(LocalDate.now().plusMonths(6));
                inssuranceContractRepository.save(contract1);
                System.out.println("Guardado InsuranceContract 1: ID " + contract1.getContractId());




            };
        };
    }}