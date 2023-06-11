package com.neuralnet.pocfinancas.api.controller

import com.neuralnet.pocfinancas.api.model.input.GestorInput
import com.neuralnet.pocfinancas.api.model.input.toGestorModel
import com.neuralnet.pocfinancas.domain.model.Gestor
import com.neuralnet.pocfinancas.domain.repository.GestorRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/gestores")
class GestorController(
    private val gestorRepository: GestorRepository
) {

    @GetMapping
    fun list(): List<Gestor> = gestorRepository.findAll()

    @GetMapping("/{gestorId}")
    fun findById(@PathVariable gestorId: UUID): ResponseEntity<Gestor> {
        return gestorRepository.findById(gestorId)
            .map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody gestorInput: GestorInput): Gestor {
        return gestorRepository.save(gestorInput.toGestorModel())
    }

    @DeleteMapping("/{gestorId}")
    fun delete(@PathVariable gestorId: UUID): ResponseEntity<Unit> {
        val gestorExiste = gestorRepository.existsById(gestorId)

        if (!gestorExiste) {
            return ResponseEntity.notFound().build()
        }

        gestorRepository.deleteById(gestorId)
        return ResponseEntity.noContent().build()
    }
}