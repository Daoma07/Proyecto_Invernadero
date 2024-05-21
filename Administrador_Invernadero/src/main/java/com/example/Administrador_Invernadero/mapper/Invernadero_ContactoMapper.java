package com.example.Administrador_Invernadero.mapper;

import com.example.Administrador_Invernadero.entity.Invernadero_Contacto;
import com.mycompany.utilities.dto.InvernaderoContactoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Invernadero_ContactoMapper {

    @Autowired
    private InvernaderoMapper invernaderoMapper;

    @Autowired
    private ContactoMapper contactoMapper;

    public InvernaderoContactoDto mapperToInvernaderoContactoDto(Invernadero_Contacto invernadero_Contacto) {
        return new InvernaderoContactoDto(
                invernadero_Contacto.getId_invernadero_contacto(),
                invernaderoMapper.mapperToInvernaderoDto(invernadero_Contacto.getInvernadero()),
                contactoMapper.mapperToContactoDto(invernadero_Contacto.getContacto()));
    }

    public Invernadero_Contacto mapperToInvernadero_Contacto(InvernaderoContactoDto invernaderoContactoDto) {
        return new Invernadero_Contacto(
                invernaderoContactoDto.getId_invernadero_contacto(),
                contactoMapper.mapperToContacto(invernaderoContactoDto.getContactoDto()),
                invernaderoMapper.mapperToInvernadero(invernaderoContactoDto.getInvernaderoDto()));

    }
}
