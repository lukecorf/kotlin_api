package br.com.alura.forum.domain

import org.springframework.security.core.GrantedAuthority
import javax.persistence.*

@Entity
data class Roles (

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ROLES")
    @SequenceGenerator(name = "SEQ_ROLES", sequenceName = "SEQ_ROLES", allocationSize = 1)
    private val id: Long,

    private val name: String

) : GrantedAuthority {

    override fun getAuthority() = name

}