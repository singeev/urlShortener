package com.singeev.urlShortener.service

import org.springframework.stereotype.Component

@Component
class KeyConverterServiceImpl: KeyConverterService {

    private final val chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890-_".toCharArray()
    private final val charsSize = chars.size
    val charsMap = (0 until charsSize)
            .map { i -> Pair(chars[i], i.toLong())}
            .toMap()

    override fun idToKey(id: Long): String {
        var n = id
        val builder = StringBuilder()
        while(n != 0L) {
            builder.append(chars[(n % charsSize).toInt()])
            n /= charsSize
        }
        return builder.reverse().toString()
    }

    override fun keyToId(key: String) = key.map{ c -> charsMap[c]!!}.fold(0L, {a, b -> a * charsSize + b})
}