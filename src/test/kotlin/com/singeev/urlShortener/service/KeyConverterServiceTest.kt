package com.singeev.urlShortener.service

import com.singeev.urlShortener.UrlShortenerApplication
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import java.util.*

@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(classes = [(UrlShortenerApplication::class)])
@WebAppConfiguration
class KeyConverterServiceTest {

    val converter: KeyConverterService = KeyConverterServiceImpl()

    @Test
    fun shouldConvertKeyToIdAndViceVersa() {
        val rand = Random()
        for(i in 0..1000) {
            val initId = Math.abs(rand.nextLong())
            val key = converter.idToKey(initId)
            val id = converter.keyToId(key)
            assertEquals(initId, id)
        }
    }
}