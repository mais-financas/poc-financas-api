package com.neuralnet.pocfinancas.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.util.Date

@Entity
data class Objetivo(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val nome: String,

    @JsonIgnore
    @ManyToOne
    val gestor: Gestor,

    @Column(name = "prazo_estimado")
    val prazoEstimado: Date,
)
