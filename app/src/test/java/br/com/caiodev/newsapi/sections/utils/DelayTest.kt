package br.com.caiodev.newsapi.sections.utils

import base.TestSteps
import br.com.caiodev.newsapi.sections.utils.delay.Delay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.lang.Thread.sleep

class DelayTest : TestSteps {

    private lateinit var delay: Delay

    @BeforeEach
    fun setupDependencies() {
        delay = Delay
    }

    @Test
    fun delay_receivesTimeAndActionToExecute_executesAGivenActionInAGivenTime() {

        var finalNumber = 0

        doWhen {
            delay.delay(3000) { finalNumber = 7 }
        }

        then {
            sleep(4000)
            assertEquals(7, finalNumber)
        }
    }
}