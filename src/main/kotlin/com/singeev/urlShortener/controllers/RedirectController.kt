package com.singeev.urlShortener.controllers

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletResponse

@Controller
class RedirectController {

    @RequestMapping("/{path}")
    fun redirect(@PathVariable("path") path: String, response: HttpServletResponse) {
        if (path == "testPath") {
            response.setHeader("Location", "http://www.somelocation.com")
            response.status = HttpStatus.FOUND.value()
        } else {
            response.status = HttpStatus.NOT_FOUND.value()
        }

    }
}