package com.neuralnet.pocfinancas.api.model.input

import java.math.BigDecimal
import java.util.Date

class DespesaInput(val nome: String, val categoria: String, val valor: BigDecimal, val data: Date)