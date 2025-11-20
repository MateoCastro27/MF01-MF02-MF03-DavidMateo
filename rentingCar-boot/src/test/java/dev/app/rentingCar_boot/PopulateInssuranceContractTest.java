package dev.app.rentingCar_boot;

import dev.app.rentingCar_boot.repository.InssuranceContractRepository;
import dev.app.rentingCar_boot.utils.PopulateInssuranceContract;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;



@SpringBootTest
@Transactional
class PopulateInssuranceContractTest {

    @Autowired private PopulateInssuranceContract populateInssuranceContract;
    @Autowired private InssuranceContractRepository contractRepository;

    @Test
    void CreateContracts() {
        populateInssuranceContract.populateInssuranceContract(100);

        long total = contractRepository.count();
        System.out.println("CONTRATOS CREADOS: " + total);


    }}