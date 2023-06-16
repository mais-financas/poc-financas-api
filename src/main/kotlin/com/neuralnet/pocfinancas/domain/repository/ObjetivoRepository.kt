package com.neuralnet.pocfinancas.domain.repository

import com.neuralnet.pocfinancas.domain.model.Objetivo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ObjetivoRepository : JpaRepository<Objetivo, Long>