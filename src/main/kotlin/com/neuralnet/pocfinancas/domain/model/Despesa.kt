package com.neuralnet.pocfinancas.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.math.BigDecimal
import java.util.Date

@Entity
data class Despesa(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val nome: String,

    val valor: BigDecimal,

    val categoria: String,

    @JsonIgnore
    @ManyToOne
    val gestor: Gestor,

    val data: Date,
)
