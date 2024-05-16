package com.example.GestionAlarmas.service;

import com.mycompany.utilities.dto.AlarmaDto;
import com.mycompany.utilities.dto.UmbralesDto;
import java.util.List;

public interface UmbralService {

    UmbralesDto createUmbral(UmbralesDto umbralesDto);

    UmbralesDto readUmbral(Long id_umbral);

    List<UmbralesDto> readAllUmbrales();

}
