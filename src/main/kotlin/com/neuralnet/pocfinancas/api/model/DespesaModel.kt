package com.neuralnet.pocfinancas.api.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.neuralnet.pocfinancas.domain.model.Despesa
import java.math.BigDecimal
import java.util.Date
import java.util.UUID

data class DespesaModel(
    val id: Long?,
    val nome: String,
    val categoria: String,
    val valor: BigDecimal,
    val data: Date,
    @JsonProperty(value = "gestor_id") val gestorId: UUID?
)

fun Despesa.toModel(): DespesaModel =
    DespesaModel(
        id = id,
        nome = nome,
        valor = valor,
        categoria = categoria,
        data = data,
        gestorId = gestor.id,
    )
