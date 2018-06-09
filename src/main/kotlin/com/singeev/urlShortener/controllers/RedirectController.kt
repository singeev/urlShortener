package com.singeev.urlShortener.controllers

import com.singeev.urlShortener.service.KeyMapperService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletResponse

@Controller
class RedirectController {

    @Autowired
    private lateinit var service: KeyMapperService

    @RequestMapping("/{key}")
    fun redirect(@PathVariable("key") key: String, response: HttpServletResponse) {
        val result = service.getLink(key)
        when (result) {
            is KeyMapperService.Get.Link -> {
                response.setHeader(LOCATION_HEADER, result.link)
                response.status = HttpStatus.FOUND.value()
            }
            is KeyMapperService.Get.NotFound -> {
                response.status = HttpStatus.NOT_FOUND.value()
            }
        }
    }

    companion object {
        private val LOCATION_HEADER = "Location"
    }
}