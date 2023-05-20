package com.neuralnet.pocfinancas.domain.repository

import com.neuralnet.pocfinancas.domain.model.Despesa
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DespesaRepository : JpaRepository<Despesa, Long>