package com.singeev.urlShortener.model

import com.github.springtestdbunit.DbUnitTestExecutionListener
import com.singeev.urlShortener.UrlShortenerApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests

@TestExecutionListeners(DbUnitTestExecutionListener::class)
@SpringBootTest(classes = [(UrlShortenerApplication::class)])
@DirtiesContext
abstract class AbstractRepositoryTest: AbstractJUnit4SpringContextTests() {
}