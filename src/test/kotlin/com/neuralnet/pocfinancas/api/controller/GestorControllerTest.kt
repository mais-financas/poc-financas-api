package com.neuralnet.pocfinancas.api.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.neuralnet.pocfinancas.api.model.input.GestorInput
import com.neuralnet.pocfinancas.domain.model.Gestor
import com.neuralnet.pocfinancas.domain.repository.GestorRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import java.util.Optional
import java.util.UUID

@WebMvcTest(GestorController::class)
class GestorControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper,
) {

    @MockBean
    private lateinit var gestorRepository: GestorRepository

    private val baseUrl = "/api/gestores"
    private val validId = UUID.randomUUID()

    @BeforeEach
    fun setUp() {
        val gestores = listOf(
            Gestor(nome = "Roberto", email = "roberto@email.com", password = "roberto123"),
            Gestor(nome = "Maria", email = "maria@email.com", password = "maria123"),
            Gestor(nome = "Rosana", email = "rosana@email.com", password = "rosana123")
        )

        val gestor = Gestor(
            id = validId,
            nome = "Carlos",
            email = "carlos@email.com",
            password = "carlos123"
        )

        // Conditions to mock the repository
        given(gestorRepository.findAll()).willReturn(gestores)
        given(gestorRepository.findById(validId)).willReturn(Optional.of(gestor))
        given(gestorRepository.save(any(Gestor::class.java))).willReturn(gestor)
        given(gestorRepository.existsById(validId)).willReturn(true)
    }

    @Nested
    @DisplayName("GET /api/banks")
    inner class GetBanks {

        @Test
        fun `Deve retornar uma lista com os gestores`() {
            mockMvc.get(baseUrl)
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[1].nome") { value("Maria") }
                }
        }

        @Test
        fun `Com id existente, deve retornar status 200 e o gestor`() {

            mockMvc.get("$baseUrl/$validId")
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.nome") { value("Carlos") }
                }
        }

        @Test
        fun `Com id inexistente, deve retornar status 404`() {
            val invalidId = UUID.randomUUID()
            mockMvc.get("$baseUrl/$invalidId")
                .andExpect { status { isNotFound() } }
        }
    }

    @Nested
    @DisplayName("POST /api/banks")
    inner class PostBanks {

        @Test
        fun `Deve criar novo gestor e retornar seus atributos, exceto da senha`() {
            val gestorInput = GestorInput("Carlos", "carlos@email.com", "carlos123")

            mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(gestorInput)
            }
                .andExpect {
                    status { isCreated() }
                        jsonPath("$.nome") { value("Carlos") }
                        jsonPath("$.email") { value("carlos@email.com") }
                        jsonPath("$.password") { doesNotExist() }
                }
        }

        @Test
        fun `Para request que nao contenha todos atributos, deve retornar status 400`() {

            mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = """
                    "nome": "Carlos",
                    "email": "carlos@email.com"
                """.trimIndent()
            }
                .andExpect {
                    status { isBadRequest() }
                }
        }
    }


    @Nested
    @DisplayName("DELETE /api/banks")
    inner class DeleteBanks {

        @Test
        fun `Com gestor com id existente, deve retornar status 204`() {
            mockMvc.delete("$baseUrl/$validId")
                .andExpect {
                    status { isNoContent() }
                }
        }

        @Test
        fun `Com id gestor com id inexistente, deve retornar status 404`() {
            val invalidId = UUID.randomUUID()
            mockMvc.delete("$baseUrl/$invalidId")
                .andExpect {
                    status { isNotFound() }
                }
        }
    }
}