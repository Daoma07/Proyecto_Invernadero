package com.example.Administrador_Sensores.service;

import com.mycompany.utilities.dto.MuestraDto;
import java.util.List;

public interface MuestarService {

    MuestraDto createMuestra(MuestraDto muestraDto);

    List<MuestraDto> readAllMuestra();

}
