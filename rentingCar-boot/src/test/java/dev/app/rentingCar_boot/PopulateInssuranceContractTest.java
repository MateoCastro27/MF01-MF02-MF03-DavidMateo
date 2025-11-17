package dev.app.rentingCar_boot;

import dev.app.rentingCar_boot.repository.InssuranceContractRepository;
import dev.app.rentingCar_boot.utils.PopulateInssuranceContract;
import dev.app.rentingCar_boot.utils.PopulateStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PopulateInssuranceContractTest {

    @Autowired private PopulateInssuranceContract populateInssuranceContract;
    @Autowired private InssuranceContractRepository contractRepository;

    @Test
    void creaExactamenteCuatroContratos() {

        // LIMPIAMOS LOS CONTRATOS ANTES DE EMPEZAR (¡ESTO ES LA CLAVE!)
        contractRepository.deleteAll();
        // (No borramos coches ni compañías → ya existen)

        // Ejecutamos el populate
        PopulateStatus status = populateInssuranceContract.populateInssuranceContract(5);

        // Ahora SÍ: exactamente 4
        assertTrue(status.isStatus(), "Debe ejecutarse con éxito");
        assertEquals(4, status.getQty(), "Debe indicar que SE han creado 4");
        assertEquals(4, contractRepository.count(), "En la base deben haber exactamente 4 contratos");

        System.out.println(" 4 contratos creados correctamente");
    }
}