package com.example.horoscapp.ui.data.providers

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class RandomCardProviderTest{
    @Test
    fun `getRandom should return a random card`(){
        val randomCard = RandomCardProvider()

        val card = randomCard.getLucky()

        assertNotNull(card)

    }
}