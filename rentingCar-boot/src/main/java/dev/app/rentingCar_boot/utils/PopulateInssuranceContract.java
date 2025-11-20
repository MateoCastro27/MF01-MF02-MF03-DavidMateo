package dev.app.rentingCar_boot.utils;

import dev.app.rentingCar_boot.model.Car;
import dev.app.rentingCar_boot.model.InssuranceCia;
import dev.app.rentingCar_boot.model.InssuranceContract;
import dev.app.rentingCar_boot.repository.CarRepository;
import dev.app.rentingCar_boot.repository.InssuranceCiaRepository;
import dev.app.rentingCar_boot.repository.InssuranceContractRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PopulateInssuranceContract {

    private final InssuranceContractRepository contractRepository;
    private final CarRepository carRepository;
    private final InssuranceCiaRepository ciaRepository;

    public PopulateInssuranceContract(InssuranceContractRepository contractRepository,
                                      CarRepository carRepository,
                                      InssuranceCiaRepository ciaRepository) {
        this.contractRepository = contractRepository;
        this.carRepository = carRepository;
        this.ciaRepository = ciaRepository;
    }

    public PopulateStatus populateInssuranceContract(int qty) {
        try {



            List<Car> cars = new ArrayList<>();
            carRepository.findAll().forEach(cars::add);
            List<InssuranceCia> cias = new ArrayList<>();
            ciaRepository.findAll().forEach(cias::add);

            if (cars.isEmpty() || cias.isEmpty()) {
                return new PopulateStatus(false, "No hay coches o compañías", 0);
            }

            // creamos contratos
            List<InssuranceContract> contratos = new ArrayList<>();

            contratos.add(createContract(cars.get(0), cias.get(0), LocalDate.of(2025, 1, 1), LocalDate.of(2025, 12, 31)));
            contratos.add(createContract(cars.get(1 % cars.size()), cias.get(1 % cias.size()), LocalDate.now(), LocalDate.now().plusYears(1)));
            contratos.add(createContract(cars.get(2 % cars.size()), cias.get(2 % cias.size()), LocalDate.of(2025, 6, 1), LocalDate.of(2026, 5, 31)));
            contratos.add(createContract(cars.get(0), cias.get(0), LocalDate.now().minusMonths(3), LocalDate.now().plusMonths(9)));
            contratos.add(createContract(cars.get(1 % cars.size()), cias.get(0), LocalDate.of(2024, 12, 1), LocalDate.of(2025, 11, 30)));

            contractRepository.saveAll(contratos);
            contractRepository.flush();

            return new PopulateStatus(true, "5 contratos de seguro creados correctamente", 5);

        } catch (Exception e) {
            e.printStackTrace();
            return new PopulateStatus(false, "Error: " + e.getMessage(), 0);
        }
    }


    private InssuranceContract createContract(Car car, InssuranceCia cia, LocalDate start, LocalDate end) {
        InssuranceContract c = new InssuranceContract();
        c.setCar(car);
        c.setInssuranceCia(cia);
        c.setStartDate(start);
        c.setEndDate(end);
        return c;
    }}