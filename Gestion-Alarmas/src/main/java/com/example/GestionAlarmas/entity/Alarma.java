package com.example.GestionAlarmas.entity;

import com.mycompany.utilities.dto.TipoAlarmaEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alarmas")
public class Alarma {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_alarma;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private TipoAlarmaEnum tipo;

    @Column(name = "valor_excedido", nullable = false)
    private Float valor_excedido;

    @Column(name = "id_sensor", nullable = false)
    private Long id_sensor;

}
