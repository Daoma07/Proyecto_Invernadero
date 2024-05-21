package com.example.Administrador_Invernadero.service;

import com.mycompany.utilities.dto.ContactoDto;

public interface ContactoService {

    ContactoDto createContacto(ContactoDto contactoDto);

    ContactoDto readContacto(Long id);
}
