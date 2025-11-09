package dev.app.rentingCar_boot.service;


import dev.app.rentingCar_boot.model.InssuranceCia;
import dev.app.rentingCar_boot.repository.InssuranceCiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InssuranceService {

    @Autowired
    private InssuranceCiaRepository inssuranceCiaRepository;

    public void populateInssuranceCias() {
        InssuranceCia cia1 = new InssuranceCia();
        cia1.setName("Seguros Barcelona");

        //delegacion con el nombre y direccion
        cia1.getDelegations().add("Barcelona Office/nCarrer de Balmes 123");

        inssuranceCiaRepository.save(cia1);


    }

}
