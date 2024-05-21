package com.example.Administrador_Invernadero.service.impl;

import com.example.Administrador_Invernadero.entity.Invernadero_Contacto;
import com.example.Administrador_Invernadero.mapper.Invernadero_ContactoMapper;
import com.example.Administrador_Invernadero.repository.Invernadero_ContactoRepository;
import com.example.Administrador_Invernadero.service.Invernadero_ContactoService;
import com.mycompany.utilities.dto.InvernaderoContactoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Invernadero_ContactoServiceImpl implements Invernadero_ContactoService {

    @Autowired
    private Invernadero_ContactoRepository invernaderoContactoRepository;

    @Autowired
    private Invernadero_ContactoMapper invernadero_ContactoMapper;

    @Override
    public InvernaderoContactoDto createInvernaderoContacto(InvernaderoContactoDto invernaderoContactoDto) {
        Invernadero_Contacto invernadero_Contacto = invernadero_ContactoMapper.mapperToInvernadero_Contacto(invernaderoContactoDto);
        invernadero_Contacto = invernaderoContactoRepository.save(invernadero_Contacto);
        return invernadero_ContactoMapper.mapperToInvernaderoContactoDto(invernadero_Contacto);
    }

    @Override
    public InvernaderoContactoDto readInvernaderoContacto(Long id) {
        Invernadero_Contacto invernadero_Contacto = invernaderoContactoRepository.findById(id).orElse(null);
        return invernadero_ContactoMapper.mapperToInvernaderoContactoDto(invernadero_Contacto);
    }

}
