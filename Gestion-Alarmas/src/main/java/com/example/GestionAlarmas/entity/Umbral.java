package com.example.GestionAlarmas.entity;

import com.mycompany.utilities.dto.CondicionEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "umbrales")
public class Umbral {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_umbral;

    @Column(name = "condicion")
    @Enumerated(EnumType.STRING)
    private CondicionEnum condicion;

    @Column(name = "max")
    private float max;

    @Column(name = "min")
    private float min;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "umbral")
    private List<SensorUmbral> sensorUmbrales;

}
