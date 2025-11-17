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
            if (contractRepository.count() >= 4) {
                return new PopulateStatus(true, "Ya existen 4 contratos de seguro (no se crearon más)", 0);
            }

            List<Car> cars = new ArrayList<>();
            carRepository.findAll().forEach(cars::add);

            List<InssuranceCia> cias = new ArrayList<>();
            ciaRepository.findAll().forEach(cias::add);

            if (cars.isEmpty() || cias.isEmpty()) {
                return new PopulateStatus(false, "No hay coches o compañías para crear contratos", 0);
            }

            InssuranceContract c1 = new InssuranceContract();
            c1.setCar(cars.get(0));
            c1.setInssuranceCia(cias.get(0));
            c1.setStartDate(LocalDate.of(2025, 1, 1));
            c1.setEndDate(LocalDate.of(2025, 12, 31));

            InssuranceContract c2 = new InssuranceContract();
            c2.setCar(cars.size() > 1 ? cars.get(1) : cars.get(0));
            c2.setInssuranceCia(cias.size() > 1 ? cias.get(1) : cias.get(0));
            c2.setStartDate(LocalDate.now());
            c2.setEndDate(LocalDate.now().plusYears(1));

            InssuranceContract c3 = new InssuranceContract();
            c3.setCar(cars.get(0));
            c3.setInssuranceCia(cias.get(0));
            c3.setStartDate(LocalDate.of(2025, 6, 1));
            c3.setEndDate(LocalDate.of(2026, 5, 31));

            InssuranceContract c4 = new InssuranceContract();
            c4.setCar(cars.size() > 2 ? cars.get(2) : cars.get(0));
            c4.setInssuranceCia(cias.size() > 2 ? cias.get(2) : cias.get(0));
            c4.setStartDate(LocalDate.now().minusMonths(3));
            c4.setEndDate(LocalDate.now().plusMonths(9));

            contractRepository.save(c1);
            contractRepository.save(c2);
            contractRepository.save(c3);
            contractRepository.save(c4);

            return new PopulateStatus(true, "4 contratos de seguro creados correctamente", 4);

        } catch (Exception e) {
            return new PopulateStatus(false, "Error al crear contratos : " + e.getMessage(), 0);
        }
    }

    public void populate() {
    }
}