package com.example.faurecia_aptoidetechnicalchallenge.ui

import app.cash.turbine.test
import com.example.faurecia_aptoidetechnicalchallenge.repo.FakeRepository
import com.example.faurecia_aptoidetechnicalchallenge.repo.getDummyResponse
import com.example.faurecia_aptoidetechnicalchallenge.util.CoroutineDispatcherProvider
import com.example.faurecia_aptoidetechnicalchallenge.util.Resource
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private lateinit var viewModel: MainViewModel
    private lateinit var fakeRepo: FakeRepository
    private val dispatcherProvider = CoroutineDispatcherProvider(
        mainThreadSurrogate,
        mainThreadSurrogate,
        mainThreadSurrogate
    )

    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
        fakeRepo = FakeRepository()
        viewModel = MainViewModel(fakeRepo, dispatcherProvider)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

//    @Test
//    fun `success network response`() = runBlocking {
//        assertTrue(viewModel.appList.first() is Resource.Loading)
//        assertEquals(getDummyResponse(), viewModel.appList.drop(2).first().data)
//    }

    @Test
    fun `success network response with turbine`() = runBlocking {
        viewModel.appList.test {
            assertTrue(awaitItem() is Resource.Loading)
            assertTrue(awaitItem() is Resource.Loading)
            assertEquals(getDummyResponse(), awaitItem().data)
        }
    }

    @Test
    fun `error network response with turbine`() = runBlocking {
        fakeRepo.shouldEmitError = true
        viewModel.appList.test {
            assertTrue(awaitItem() is Resource.Loading)
            assertTrue(awaitItem() is Resource.Loading)
            assertEquals("Error message", awaitItem().message)
        }
    }
}