package com.example.Administrador_Invernadero.facade.impl;

import com.example.Administrador_Invernadero.facade.Facade;
import com.example.Administrador_Invernadero.service.ContactoService;
import com.example.Administrador_Invernadero.service.InvernaderoService;
import com.example.Administrador_Invernadero.service.Invernadero_ContactoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.utilities.dto.ContactoDto;
import com.mycompany.utilities.dto.InvernaderoContactoDto;
import com.mycompany.utilities.dto.InvernaderoDto;
import com.mycompany.utilities.intercambio.ResponseFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacadeImpl implements Facade {

    private ContactoService contactoService;
    private InvernaderoService invernaderoService;
    private Invernadero_ContactoService invernadero_ContactoService;
    private ObjectMapper objectMapper;

    @Autowired
    public FacadeImpl(ContactoService contactoService, InvernaderoService invernaderoService, Invernadero_ContactoService invernadero_ContactoService, ObjectMapper objectMapper) {
        this.contactoService = contactoService;
        this.invernaderoService = invernaderoService;
        this.invernadero_ContactoService = invernadero_ContactoService;
        this.objectMapper = objectMapper;
    }

    @Override
    public ResponseFormat createContacto(ContactoDto contactoDto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseFormat readContacto(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseFormat createInvernadero(InvernaderoDto invernaderoDto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseFormat readInvernadero(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseFormat createInvernaderoContacto(InvernaderoContactoDto invernaderoContactoDto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResponseFormat readInvernaderoContacto(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
