package com.neuralnet.pocfinancas.api.controller

import com.neuralnet.pocfinancas.api.model.DespesaModel
import com.neuralnet.pocfinancas.api.model.input.DespesaInput
import com.neuralnet.pocfinancas.api.model.toModel
import com.neuralnet.pocfinancas.domain.model.Despesa
import com.neuralnet.pocfinancas.domain.repository.DespesaRepository
import com.neuralnet.pocfinancas.domain.repository.GestorRepository
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
@RequestMapping("/api/gestores/{gestorId}/despesas")
class DespesaController(
    private val despesaRepository: DespesaRepository,
    private val gestorRepository: GestorRepository
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@PathVariable gestorId: UUID, @RequestBody despesaInput: DespesaInput): ResponseEntity<DespesaModel> {
        return gestorRepository.findById(gestorId)
            .map { gestor ->
                val despesa = Despesa(
                    nome = despesaInput.nome,
                    valor = despesaInput.valor,
                    gestor = gestor
                ).let { despesaRepository.save(it) }

                ResponseEntity.ok(despesa.toModel())
            }
            .orElse(ResponseEntity.notFound().build())
    }

    @GetMapping
    fun list(@PathVariable gestorId: UUID): ResponseEntity<List<DespesaModel>> {
        return gestorRepository.findById(gestorId)
            .map { gestor ->
                val despesas = gestor.despesas.map(Despesa::toModel)
                    ResponseEntity.ok(despesas)
            }.orElse(ResponseEntity.notFound().build())
    }
}
