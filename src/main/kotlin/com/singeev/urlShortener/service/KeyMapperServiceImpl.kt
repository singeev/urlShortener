package com.singeev.urlShortener.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

@Component
class KeyMapperServiceImpl: KeyMapperService {

    @Autowired
    lateinit var converter: KeyConverterService

    private val sequence = AtomicLong(10000000L)

    private val storage: MutableMap<Long, String> = ConcurrentHashMap()

    override fun add(link: String): String {
        val id = sequence.getAndIncrement()
        val key = converter.idToKey(id)
        storage[id] = link
        return key
    }

    override fun getLink(key: String): KeyMapperService.Get {
        val id = converter.keyToId(key)
        val result = storage[id]
        return if(result == null) {
            KeyMapperService.Get.NotFound(key)
        } else {
            KeyMapperService.Get.Link(result)
        }
    }
}