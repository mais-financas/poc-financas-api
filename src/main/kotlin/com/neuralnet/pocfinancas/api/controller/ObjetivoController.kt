package com.neuralnet.pocfinancas.api.controller

import com.neuralnet.pocfinancas.api.model.ObjetivoModel
import com.neuralnet.pocfinancas.api.model.input.ObjetivoInput
import com.neuralnet.pocfinancas.api.model.toModel
import com.neuralnet.pocfinancas.domain.model.Objetivo
import com.neuralnet.pocfinancas.domain.repository.GestorRepository
import com.neuralnet.pocfinancas.domain.repository.ObjetivoRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/gestores/{gestorId}/objetivos")
class ObjetivoController(
    private val objetivoRepository: ObjetivoRepository,
    private val gestorRepository: GestorRepository
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@PathVariable gestorId: UUID, @RequestBody objetivoInput: ObjetivoInput): ResponseEntity<ObjetivoModel> {
        return gestorRepository.findById(gestorId)
            .map { gestor ->
                val objetivo = Objetivo(
                    nome = objetivoInput.nome,
                    gestor = gestor,
                    prazoEstimado = objetivoInput.prazoEstimado
                ).let { objetivoRepository.save(it) }

                ResponseEntity.ok(objetivo.toModel())
            }
            .orElse(ResponseEntity.notFound().build())
    }

    @GetMapping
    fun list(@PathVariable gestorId: UUID): ResponseEntity<List<ObjetivoModel>> {
        return gestorRepository.findById(gestorId)
            .map { gestor ->
                val despesas = gestor.objetivos.map(Objetivo::toModel)
                    ResponseEntity.ok(despesas)
            }.orElse(ResponseEntity.notFound().build())
    }
}
