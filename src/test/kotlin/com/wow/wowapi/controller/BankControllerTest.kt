package com.wow.wowapi.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.wow.wowapi.DTO.Bank
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.patch
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {

    var basePath = "/api/banks"

    @Nested
    @DisplayName("GET /banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBanks {
        @Test
        fun `should return all banks`() {
            //when
            mockMvc.get(basePath)
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    jsonPath("$[0].accountNumber") {
                        value("1234")
                    }
                }
        }

        @Test
        fun `should return a bank`() {
            //Given
            val accountNumber = 1234

            //when then
            mockMvc.get("$basePath/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.trust") { value("0.0") }
                }
        }

        @Test
        fun `should not return a bank NOT FOUND`() {
            //Given
            val accountNumber = 0

            //when then
            mockMvc.get("$basePath/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }
    }

    @Nested
    @DisplayName("POST /bank")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostBank {
        @Test
        fun `should create a bank`() {
            //given
            val newBank = Bank("acc123", 1, 2.1)

            //when
            val response = mockMvc.post(basePath) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBank)
            }

            // then
            response.andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.accountNumber") { value("acc123") }
                }

        }

        @Test
        fun `should return BAD REQUEST if account number already exists`() {
            // given
            val newBank = Bank("1234", 1, 2.1)
            //when
            val response = mockMvc.post(basePath) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBank)
            }

            // then
            response.andDo { print() }.andExpect {
                status { isBadRequest() }

            }
        }
    }

    @Nested
    @DisplayName("PATCH /bank")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PatchBank {

        @Test
        fun `should update a bank`() {
            // Given
            val updatedBank = Bank("1234", 1, 2.1)

            // When
            val response = mockMvc.patch(basePath) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedBank)
            }

            // Then
            response
                .andDo { print() }.andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(updatedBank))
                    }
                }

            mockMvc.get("$basePath/${updatedBank.accountNumber}")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.trust") { value("0.0") }
                }// ? ensures that the response matches give body
        }
    }
}