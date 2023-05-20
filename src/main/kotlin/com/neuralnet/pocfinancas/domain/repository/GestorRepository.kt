package com.neuralnet.pocfinancas.domain.repository

import com.neuralnet.pocfinancas.domain.model.Gestor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface GestorRepository : JpaRepository<Gestor, UUID>