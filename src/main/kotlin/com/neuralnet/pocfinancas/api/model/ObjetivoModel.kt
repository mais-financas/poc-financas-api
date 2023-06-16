package com.neuralnet.pocfinancas.api.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.neuralnet.pocfinancas.domain.model.Objetivo
import java.util.Date
import java.util.UUID

data class ObjetivoModel(
    val id: Long?,
    val nome: String,
    val prazoEstimado: Date,
    @JsonProperty(value = "gestor_id") val gestorId: UUID?
)

fun Objetivo.toModel(): ObjetivoModel =
    ObjetivoModel(
        id = id,
        nome = nome,
        prazoEstimado = prazoEstimado,
        gestorId = gestor.id,
    )
