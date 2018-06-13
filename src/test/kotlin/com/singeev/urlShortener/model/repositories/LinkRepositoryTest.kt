package com.singeev.urlShortener.model.repositories

import com.github.springtestdbunit.annotation.DatabaseOperation
import com.github.springtestdbunit.annotation.DatabaseSetup
import com.github.springtestdbunit.annotation.DatabaseTearDown
import com.singeev.urlShortener.entity.Link
import com.singeev.urlShortener.entity.repositories.LinkRepository
import com.singeev.urlShortener.model.AbstractRepositoryTest
import junit.framework.Assert.*
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

@DatabaseSetup(LinkRepositoryTest.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = [(LinkRepositoryTest.DATASET)])
open class LinkRepositoryTest : AbstractRepositoryTest() {

    @Autowired
    lateinit var repository: LinkRepository

    @Test
    fun shouldFindExistingLink() {
        val result: Optional<Link> = repository.findById(LINK_ONE_ID)
        assertTrue(result.isPresent)
        assertEquals(result.get(), Link(LINK_ONE_TEXT, LINK_ONE_ID))
    }

    @Test
    fun shouldNotFindNonExisting() {
        val result: Optional<Link> = repository.findById(NON_EXISTING_ID)
        assertFalse(result.isPresent)
    }

    @Test
    fun shouldSaveNewAndFindIt() {
        val link = Link(LINK_TBS_TEXT)
        val retrieved: Link = repository.save(link)
        val allFromDb = repository.findAll()
        assertTrue(allFromDb.size == 4)
        assertEquals(retrieved.text, LINK_TBS_TEXT)
    }

    companion object {
        const val DATASET = "classpath:datasets/link-table.xml"
        private val LINK_ONE_ID = 1001L
        private val LINK_ONE_TEXT = "http://www.google.com"
        private val LINK_TBS_TEXT = "http://www.facebook.com"
        private val NON_EXISTING_ID = 1L
    }
}