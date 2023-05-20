package com.neuralnet.pocfinancas.api.model.input

import com.neuralnet.pocfinancas.domain.model.Gestor

class GestorInput(
    val nome: String,
    val email: String,
    val senha: String,
)

fun GestorInput.toGestorModel(): Gestor =
    Gestor(
        nome = nome,
        email = email,
        password = senha,
    )