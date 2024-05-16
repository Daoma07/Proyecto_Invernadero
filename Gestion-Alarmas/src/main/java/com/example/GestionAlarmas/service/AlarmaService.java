package com.example.GestionAlarmas.service;

import com.mycompany.utilities.dto.AlarmaDto;
import java.util.List;

public interface AlarmaService {

    AlarmaDto createAlarma(AlarmaDto alarmaDto);

    AlarmaDto readAlarma(Long id_alarma);

    List<AlarmaDto> readAllAlarmas();

    boolean deleteAlarma(AlarmaDto alarmaDto);

    AlarmaDto updateAlarma(AlarmaDto alarmaDto);

}
