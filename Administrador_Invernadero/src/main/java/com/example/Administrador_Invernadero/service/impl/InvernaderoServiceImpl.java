package com.example.Administrador_Invernadero.service.impl;

import com.example.Administrador_Invernadero.entity.Invernadero;
import com.example.Administrador_Invernadero.mapper.InvernaderoMapper;
import com.example.Administrador_Invernadero.repository.InvernaderoRepository;
import com.example.Administrador_Invernadero.service.InvernaderoService;
import com.mycompany.utilities.dto.InvernaderoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvernaderoServiceImpl implements InvernaderoService {

    @Autowired
    private InvernaderoRepository invernaderoRepository;

    @Autowired
    private InvernaderoMapper invernaderoMapper;

    @Override
    public InvernaderoDto createInvernadero(InvernaderoDto invernaderoDto) {
        Invernadero invernadero = invernaderoMapper.mapperToInvernadero(invernaderoDto);
        invernadero = invernaderoRepository.save(invernadero);
        return invernaderoMapper.mapperToInvernaderoDto(invernadero);
    }

    @Override
    public InvernaderoDto readInvernadero(Long id) {
        Invernadero invernadero = invernaderoRepository.findById(id).orElse(null);
        return invernaderoMapper.mapperToInvernaderoDto(invernadero);
    }

}
