package com.example.Administrador_Invernadero.facade;

import com.mycompany.utilities.dto.ContactoDto;
import com.mycompany.utilities.dto.InvernaderoContactoDto;
import com.mycompany.utilities.dto.InvernaderoDto;
import com.mycompany.utilities.intercambio.ResponseFormat;

public interface Facade {

    ResponseFormat createContacto(ContactoDto contactoDto);

    ResponseFormat readContacto(Long id);

    ResponseFormat createInvernadero(InvernaderoDto invernaderoDto);

    ResponseFormat readInvernadero(Long id);

    ResponseFormat createInvernaderoContacto(InvernaderoContactoDto invernaderoContactoDto);

    ResponseFormat readInvernaderoContacto(Long id);
}
