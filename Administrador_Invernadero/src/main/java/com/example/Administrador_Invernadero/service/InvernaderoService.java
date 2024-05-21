package com.example.Administrador_Invernadero.service;

import com.example.Administrador_Invernadero.entity.Invernadero;
import com.mycompany.utilities.dto.InvernaderoDto;

public interface InvernaderoService {

    InvernaderoDto createInvernadero(InvernaderoDto invernaderoDto);

    InvernaderoDto readInvernadero(Long id);
}
