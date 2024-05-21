package com.example.Administrador_Invernadero.mapper;

import com.example.Administrador_Invernadero.entity.Invernadero;
import com.example.Administrador_Invernadero.entity.Invernadero_Contacto;
import com.example.Administrador_Invernadero.repository.InvernaderoRepository;
import com.example.Administrador_Invernadero.repository.Invernadero_ContactoRepository;
import com.mycompany.utilities.dto.InvernaderoDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvernaderoMapper {

    @Autowired
    private Invernadero_ContactoRepository invernadero_ContactoRepository;

    public InvernaderoDto mapperToInvernaderoDto(Invernadero invernadero) {

        List<Long> ids_invernadero_contacto = invernadero.getInvernadero_Contactos().stream()
                .map(Invernadero_Contacto::getId_invernadero_contacto)
                .collect(Collectors.toList());

        return new InvernaderoDto(
                invernadero.getId_invernadero(),
                invernadero.getName(),
                invernadero.getLocalizacion(),
                invernadero.getDescripcion(),
                ids_invernadero_contacto);
    }

    public Invernadero mapperToInvernadero(InvernaderoDto invernaderoDto) {
        return new Invernadero(
                invernaderoDto.getId_invernadero(),
                invernaderoDto.getDescripcion(),
                invernaderoDto.getNombre(),
                invernaderoDto.getLocalizacion(),
                invernadero_ContactoRepository.
                        findAllById(invernaderoDto.getIds_invernadero_contacto()));
    }
}
