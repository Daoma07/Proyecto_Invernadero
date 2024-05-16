package com.example.GestionAlarmas.service.impl;

import com.example.GestionAlarmas.entity.Umbral;
import com.example.GestionAlarmas.mapper.UmbralMapper;
import com.example.GestionAlarmas.repository.UmbralRepository;
import com.example.GestionAlarmas.service.UmbralService;
import com.mycompany.utilities.dto.UmbralesDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UmbralServiceImpl implements UmbralService {

    @Autowired
    private UmbralRepository umbralRepository;

    @Autowired
    private UmbralMapper umbralMapper;

    @Override
    public UmbralesDto createUmbral(UmbralesDto umbralesDto) {
        Umbral umbral = umbralMapper.mapperToUmbral(umbralesDto);
        umbral = umbralRepository.save(umbral);
        return umbralMapper.mapperToUmbralDto(umbral);
    }

    @Override
    public UmbralesDto readUmbral(Long id_umbral) {
        Umbral umbral = umbralRepository.findById(id_umbral).orElse(null);
        return umbralMapper.mapperToUmbralDto(umbral);
    }

    @Override
    public List<UmbralesDto> readAllUmbrales() {
        List<Umbral> umbrales = umbralRepository.findAll();
        List<UmbralesDto> umbralesDto = new ArrayList<>();
        for (Umbral umbral : umbrales) {
            umbralesDto.add(umbralMapper.mapperToUmbralDto(umbral));
        }
        return umbralesDto;
    }

}
