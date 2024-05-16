package com.example.GestionAlarmas.controller;

import com.example.GestionAlarmas.facade.Facade;
import com.mycompany.utilities.dto.SensorDto;
import com.mycompany.utilities.dto.UmbralesDto;
import com.mycompany.utilities.intercambio.ResponseFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("api/alarma")
public class AlarmaController {

    private Facade facade;

    public AlarmaController(Facade facade) {
        this.facade = facade;
    }

    @PostMapping("/createUmbral")
    public ResponseFormat createUmbral(@RequestBody UmbralesDto umbralesDto) {
        return facade.createUmbral(umbralesDto);
    }

    @PostMapping("/readUmbral")
    public ResponseFormat readUmbral(@RequestBody Long id) {
        return facade.readUmbral(id);
    }

    @GetMapping("/readAllUmbral")
    public ResponseFormat readAllUmbral() {
        return facade.readAllUmbrales();
    }

    @PostMapping("/readAllAlarmas")
    public ResponseFormat readAllAlarmas() {
        return facade.readAllAlarmas();
    }

}
