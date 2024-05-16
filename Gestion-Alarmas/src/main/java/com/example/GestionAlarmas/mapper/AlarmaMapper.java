package com.example.GestionAlarmas.mapper;

import com.example.GestionAlarmas.entity.Alarma;
import com.mycompany.utilities.dto.AlarmaDto;
import com.mycompany.utilities.dto.TipoAlarmaEnum;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class AlarmaMapper {

    public AlarmaDto mapperToAlarmaDto(Alarma alarma) {
        return new AlarmaDto(
                alarma.getId_alarma(),
                alarma.getTipo(),
                alarma.getFechaHora(),
                alarma.getValor_excedido(),
                alarma.getId_sensor());
    }

    public Alarma mapperToAlarma(AlarmaDto alarmaDto) {
        return new Alarma(
                alarmaDto.getId_alarma(),
                alarmaDto.getFechaHora(),
                alarmaDto.getTipoAlarmaEnum(),
                alarmaDto.getValor_excedido(),
                alarmaDto.getId_sensor());
    }
}
