package com.example.Administrador_Invernadero.service;

import com.mycompany.utilities.dto.InvernaderoContactoDto;

public interface Invernadero_ContactoService {

    InvernaderoContactoDto createInvernaderoContacto(InvernaderoContactoDto invernaderoContactoDto);

    InvernaderoContactoDto readInvernaderoContacto(Long id);
}
