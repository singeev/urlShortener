package com.singeev.urlShortener.service

interface KeyConverterService {
    fun idToKey(id: Long): String
    fun keyToId(key: String): Long
}