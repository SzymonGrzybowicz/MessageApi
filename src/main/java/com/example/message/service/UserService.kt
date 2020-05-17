package com.example.message.service

import com.example.message.domain.FirebaseAuthToken
import com.example.message.domain.dto.UserDto
import com.example.message.mapper.UserMapper
import com.example.message.repository.FirebaseAuthTokenRepository
import com.example.message.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class UserService(
        private val repository: UserRepository,
        private val mapper: UserMapper,
        private val firebaseRepository: FirebaseAuthTokenRepository
) {

    fun createUser(userDto: UserDto): Boolean {
        repository.findByMail(userDto.mail)?.let {
            return false
        }
        val user = mapper.mapToDomain(userDto)
        repository.save(user)
        return true
    }

    fun loginUser(userDto: UserDto): UserDto? {
        repository.findByMail(userDto.mail)?.let {
            if (it.password == userDto.password) {
                return mapper.mapToDto(it)
            }
        }
        return null
    }

    fun getUserByMail(mail: String): UserDto? {
        repository.findByMail(mail)?.let {
            mapper.mapToDto(it)
        }
        return null
    }

    fun addFirebaseAuthToken(userId: Long, authToken: String) {
        repository.findByIdOrNull(userId)?.let {user ->
            val contains = user.firebaseAuthTokens.any { a -> a.firebaseAuthToken == authToken }
            if (contains) {
                return
            }
            val tokens = firebaseRepository.findAll().stream().filter { a: FirebaseAuthToken -> a.firebaseAuthToken == authToken }.collect(Collectors.toList())
            for (token in tokens) {
                firebaseRepository.delete(token)
            }
            val token = firebaseRepository.save(FirebaseAuthToken(-1, authToken))
            user.firebaseAuthTokens.add(token)
            repository.save(user)
        }
    }

    val allUsers: List<UserDto>
        get() = mapper.mapToDtoList(repository.findAll())

}