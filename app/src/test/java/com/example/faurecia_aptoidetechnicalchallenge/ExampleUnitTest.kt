package com.example.faurecia_aptoidetechnicalchallenge

import app.cash.turbine.test
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val flow = flow {
        emit(0)
        emit(1)
        emit(2)
        emit(3)
    }

    private lateinit var stateFlow: MutableStateFlow<Int>
    private suspend fun updateStateFlow() {
        stateFlow.emit(1)
        stateFlow.emit(2)
        stateFlow.emit(3)
    }

    @Before
    fun setup() {
        stateFlow = MutableStateFlow(0)
    }

    @Test
    fun `test flow`() = runBlocking {
        assertEquals(0, flow.first())
    }

    @Test
    fun `test stateflow`() = runBlocking {
        assertEquals(0, stateFlow.first())
        updateStateFlow()
        assertEquals(3, stateFlow.first())
    }

    //turbine
    @Test
    fun `test flow with turbine`() = runBlocking {
        flow.test {
            assertEquals(0, awaitItem())
            assertEquals(1, awaitItem())
            assertEquals(2, awaitItem())
            assertEquals(3, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `test stateflow with turbine`() = runBlocking {
        stateFlow.test {
            updateStateFlow()
            assertEquals(0, awaitItem())
            assertEquals(1, awaitItem())
            assertEquals(2, awaitItem())
            assertEquals(3, awaitItem())
        }
    }
}