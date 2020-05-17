package com.example.message.controller

import com.example.message.domain.dto.UserDto
import com.example.message.service.UserService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
class UserController(private val service: UserService) {

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun registerUser(@RequestBody userDto: UserDto, response: HttpServletResponse) {
        if (!service.createUser(userDto)) {
            response.status = HttpServletResponse.SC_CONFLICT
        }
    }

    @PostMapping(value = ["/login"])
    fun loginUser(@RequestBody userDto: UserDto, response: HttpServletResponse): UserDto? {
        val loggedUser = service.loginUser(userDto)
        if (loggedUser == null) {
            response.status = HttpServletResponse.SC_BAD_REQUEST
        }
        return loggedUser
    }

    @GetMapping(value = ["/{mail}"])
    fun getByMail(@PathVariable mail: String, response: HttpServletResponse): UserDto? {
        val userDto = service.getUserByMail(mail)
        if (userDto == null) {
            response.status = HttpServletResponse.SC_BAD_REQUEST
        }
        return userDto
    }

    @GetMapping(value = ["/all"])
    fun getAllUsers(): List<UserDto> {
        return service.allUsers
    }

    @PostMapping(value = ["/firebase/{userId}/{authToken}"])
    fun addAuthToken(@PathVariable userId: Long, @PathVariable authToken: String) {
        service.addFirebaseAuthToken(userId, authToken)
    }
}