package com.example.models

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PersonTest
{
    @Test
    fun `should be able to create person`() {
        val person = Person("sanjeev")

        assertEquals(person.name, "sanjeev")
    }
}