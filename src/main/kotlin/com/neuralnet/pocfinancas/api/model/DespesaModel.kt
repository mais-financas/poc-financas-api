package com.neuralnet.pocfinancas.api.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.neuralnet.pocfinancas.domain.model.Despesa
import java.math.BigDecimal
import java.util.*

data class DespesaModel(
    val id: Long?,
    val nome: String,
    val valor: BigDecimal,
    @JsonProperty(value = "gestor_id") val gestorId: UUID?
)

fun Despesa.toModel(): DespesaModel =
    DespesaModel(
        id = id,
        nome = nome,
        valor = valor,
        gestorId = gestor.id
    )
