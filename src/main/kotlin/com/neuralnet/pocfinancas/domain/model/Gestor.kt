package com.neuralnet.pocfinancas.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import java.util.UUID

@Entity
data class Gestor(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    val nome: String,

    val email: String,

    @JsonIgnore
    val password: String,

    @JsonIgnore
    @OneToMany(
        mappedBy = "gestor", cascade = [CascadeType.ALL]
    )
    val despesas: MutableList<Despesa> = mutableListOf(),

    @JsonIgnore
    @OneToMany(
        mappedBy = "gestor", cascade = [CascadeType.ALL]
    )
    val objetivos: MutableList<Objetivo> = mutableListOf()
)
