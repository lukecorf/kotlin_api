package br.com.alura.forum.repository

import br.com.alura.forum.domain.Users
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<Users, Long> {

    fun findByEmail(username: String?): Users?

}