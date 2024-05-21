package com.example.Administrador_Invernadero.mapper;

import com.example.Administrador_Invernadero.entity.Contacto;
import com.example.Administrador_Invernadero.entity.Invernadero_Contacto;
import com.example.Administrador_Invernadero.repository.Invernadero_ContactoRepository;
import com.mycompany.utilities.dto.ContactoDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContactoMapper {

    @Autowired
    private Invernadero_ContactoRepository invernadero_ContactoRepository;

    public ContactoDto mapperToContactoDto(Contacto contacto) {

        List<Long> ids_invernadero_contacto = contacto.getInvernadero_Contactos().stream()
                .map(Invernadero_Contacto::getId_invernadero_contacto)
                .collect(Collectors.toList());

        return new ContactoDto(
                contacto.getId_contacto(),
                contacto.getNombre(),
                contacto.getTelefono(),
                contacto.getEmail(),
                ids_invernadero_contacto);
    }

    public Contacto mapperToContacto(ContactoDto contactoDto) {
        return new Contacto(
                contactoDto.getId_conacto(),
                contactoDto.getNombre(),
                contactoDto.getEmail(),
                contactoDto.getNumero_telefonico(),
                invernadero_ContactoRepository.
                        findAllById(contactoDto.getIds_invernadero_contacto()));
    }

}
