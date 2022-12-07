package com.example.faurecia_aptoidetechnicalchallenge

import app.cash.turbine.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
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
        delay(10000)
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

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test flow`() = runTest {
//        assertEquals(1, flow.drop(1).first())
        assertEquals(1, flow.drop(1).first())

//        assertEquals(0 ,flow.toList())
//        assertEquals(4, flow.count())
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
            assertEquals(0, this.awaitItem())
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