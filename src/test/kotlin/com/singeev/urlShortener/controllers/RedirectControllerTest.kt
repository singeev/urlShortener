package com.singeev.urlShortener.controllers

import com.singeev.urlShortener.UrlShortenerApplication
import com.singeev.urlShortener.service.KeyMapperService
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(classes = [(UrlShortenerApplication::class)])
@WebAppConfiguration
class RedirectControllerTest {

    @Autowired
    lateinit var webAppContext: WebApplicationContext

    lateinit var mockMvc: MockMvc

    @Mock
    lateinit var service: KeyMapperService

    @Autowired
    @InjectMocks
    lateinit var controller: RedirectController

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webAppContext)
                .build()
        Mockito.`when`(service.getLink(KEY)).thenReturn(KeyMapperService.Get.Link(HEADER_VALUE))
        Mockito.`when`(service.getLink(BAD_KEY)).thenReturn(KeyMapperService.Get.NotFound(BAD_KEY))
    }

    private val KEY = "testKey"
    private val HEADER_NAME = "Location"
    private val HEADER_VALUE = "http://www.somelocation.com"

    @Test fun shouldRedirectSuccessfulRequest() {
        mockMvc.perform(get("/$KEY"))
                .andExpect(status().`is`(HttpStatus.FOUND.value()))
                .andExpect(header().string(HEADER_NAME, HEADER_VALUE))
    }

    private val BAD_KEY = "badKey"

    @Test fun shouldReturtNotFound() {
        mockMvc.perform(get("/$BAD_KEY"))
                .andExpect(status().`is`(HttpStatus.NOT_FOUND.value()))
    }
}