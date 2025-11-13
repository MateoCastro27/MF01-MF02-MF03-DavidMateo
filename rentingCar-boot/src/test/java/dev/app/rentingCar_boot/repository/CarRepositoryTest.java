package dev.app.rentingCar_boot.repository;

import dev.app.rentingCar_boot.model.Car;
import dev.app.rentingCar_boot.model.InssuranceCia;
import dev.app.rentingCar_boot.model.InssuranceContract;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private InssuranceContractRepository insuranceContractRepository;
    @Autowired
    private InssuranceCiaRepository inssuranceCiaRepository;

    private Car car;
    private InssuranceCia cia;

    @BeforeEach
    void setUp() {
        car = new Car();
        car.setBrand("Toyota");
        car.setModel("Corolla");
        car.setPlate("ABC123");
        car.setYear(2020);
        car.setPrice(30000.0);
        car = carRepository.save(car);

        cia = new InssuranceCia();
        cia.setName("Allianz");
        cia = inssuranceCiaRepository.save(cia);
    }

    @Test
    void testSaveCar() {
        Car savedCar = carRepository.save(car);
        assertNotNull(savedCar.getId(), "El ID del coche debe generarse");
        assertEquals("Toyota", savedCar.getBrand(), "La marca debe coincidir");
    }

    @Test
    void testFindAllCars() {
        Car car2 = new Car();
        car2.setBrand("Honda");
        car2.setModel("Civic");
        car2.setPlate("XYZ789");
        car2.setYear(2021);
        car2.setPrice(32000.0);
        carRepository.save(car2);

        List<Car> cars = (List<Car>) carRepository.findAll();
        assertEquals(2, cars.size(), "Deben haber 2 coches guardados");
    }

    @Test
    void testUpdateCar() {
        car.setPrice(35000.0);
        Car updatedCar = carRepository.save(car);
        assertEquals(35000.0, updatedCar.getPrice(), "El precio debe actualizarse");
    }

    @Test
    void testDeleteCar() {
        carRepository.deleteById(car.getId());
        Car found = carRepository.findById(car.getId()).orElse(null);
        assertNull(found, "El coche debe eliminarse");
    }

    @Test
    void testManyToManyRelationship() {
        InssuranceContract contract = new InssuranceContract();
        contract.setCar(car);
        contract.setInssuranceCia(cia);
        contract.setStartDate(LocalDate.now());
        contract.setEndDate(LocalDate.now().plusMonths(6));
        insuranceContractRepository.save(contract);

        Car foundCar = carRepository.findById(car.getId()).orElse(null);
        assertNotNull(foundCar, "El coche tiene encontrarse");
        assertEquals(1, foundCar.getContracts().size(), "Debe tener 1 contrato asociado");
    }
}