package com.example.GestionAlarmas.service.impl;

import com.example.GestionAlarmas.entity.Alarma;
import com.example.GestionAlarmas.mapper.AlarmaMapper;
import com.example.GestionAlarmas.repository.AlarmaRepository;
import com.example.GestionAlarmas.service.AlarmaService;
import com.mycompany.utilities.dto.AlarmaDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlarmaServiceImpl implements AlarmaService {

    @Autowired
    private AlarmaRepository alarmaRepository;

    @Autowired
    private AlarmaMapper alarmaMapper;

    @Override
    public AlarmaDto createAlarma(AlarmaDto alarmaDto) {
        Alarma alarma = alarmaMapper.mapperToAlarma(alarmaDto);
        alarma = alarmaRepository.save(alarma);
        return alarmaMapper.mapperToAlarmaDto(alarma);
    }

    @Override
    public AlarmaDto readAlarma(Long id_alarma) {
        Alarma alarma = alarmaRepository.findById(id_alarma).orElse(null);
        if (alarma == null) {
            return null;
        }
        return alarmaMapper.mapperToAlarmaDto(alarma);
    }

    @Override
    public List<AlarmaDto> readAllAlarmas() {
        List<Alarma> alarmas = alarmaRepository.findAll();
        List<AlarmaDto> alarmasDtos = new ArrayList<>();
        for (Alarma alarma : alarmas) {
            alarmasDtos.add(alarmaMapper.mapperToAlarmaDto(alarma));
        }

        return alarmasDtos;
    }

    @Override
    public boolean deleteAlarma(AlarmaDto alarmaDto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AlarmaDto updateAlarma(AlarmaDto alarmaDto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
