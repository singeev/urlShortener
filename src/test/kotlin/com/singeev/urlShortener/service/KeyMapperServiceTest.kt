package com.singeev.urlShortener.service

import com.singeev.urlShortener.UrlShortenerApplication
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration

@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(classes = [(UrlShortenerApplication::class)])
@WebAppConfiguration
class KeyMapperServiceTest {

    @InjectMocks
    val service: KeyMapperService = KeyMapperServiceImpl()

    @Mock
    lateinit var converter: KeyConverterService

    private val key = "testKey"
    private val keyOne = "keyOne"
    private val keyTwo = "keyTwo"
    private val linkOne = "www.google.com"
    private val linkTwo = "www.yahoo.com"
    private val idOne = 10000000L
    private val idTwo = 10000001L

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(converter.keyToId(keyOne)).thenReturn(idOne)
        Mockito.`when`(converter.idToKey(idOne)).thenReturn(keyOne)
        Mockito.`when`(converter.keyToId(keyTwo)).thenReturn(idTwo)
        Mockito.`when`(converter.idToKey(idTwo)).thenReturn(keyTwo)
    }

    @Test
    fun shouldAddNewLink() {
        val keyOne = service.add(linkOne)
        assertEquals(KeyMapperService.Get.Link(linkOne), service.getLink(keyOne))
        val keyTwo = service.add(linkTwo)
        assertEquals(KeyMapperService.Get.Link(linkTwo), service.getLink(keyTwo))
    }


    @Test
    fun canNotGetLinkIfAKeyNotFound() {
        assertEquals(KeyMapperService.Get.NotFound(key), service.getLink(key))
    }
}