package com.example.Administrador_Invernadero.cotroller;

import com.example.Administrador_Invernadero.facade.Facade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("api/invernadero")
public class InvernaderoController {

    private Facade facade;

    @Autowired
    public InvernaderoController(Facade facade) {
        this.facade = facade;
    }

}
