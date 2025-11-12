package dev.app.rentingCar_boot.repository;

import dev.app.rentingCar_boot.model.InssuranceContract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InssuranceContractRepository extends JpaRepository<InssuranceContract, String> {
}
