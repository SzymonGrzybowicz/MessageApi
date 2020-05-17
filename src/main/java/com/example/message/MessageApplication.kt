package com.example.message

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class MessageApplication

    fun main(args: Array<String>) {
        SpringApplication.run(MessageApplication::class.java, *args)
    }
