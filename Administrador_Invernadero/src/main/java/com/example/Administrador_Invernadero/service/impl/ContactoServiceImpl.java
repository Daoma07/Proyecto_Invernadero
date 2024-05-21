package com.example.Administrador_Invernadero.service.impl;

import com.example.Administrador_Invernadero.entity.Contacto;
import com.example.Administrador_Invernadero.mapper.ContactoMapper;
import com.example.Administrador_Invernadero.repository.ContactoRepository;
import com.example.Administrador_Invernadero.service.ContactoService;
import com.mycompany.utilities.dto.ContactoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactoServiceImpl implements ContactoService {

    @Autowired
    private ContactoRepository contactoRepository;

    @Autowired
    private ContactoMapper contactoMapper;

    @Override
    public ContactoDto createContacto(ContactoDto contactoDto) {
        Contacto contacto = contactoMapper.mapperToContacto(contactoDto);
        contacto = contactoRepository.save(contacto);
        return contactoMapper.mapperToContactoDto(contacto);
    }

    @Override
    public ContactoDto readContacto(Long id) {
        Contacto contacto = contactoRepository.findById(id).orElse(null);
        return contactoMapper.mapperToContactoDto(contacto);
    }

}
