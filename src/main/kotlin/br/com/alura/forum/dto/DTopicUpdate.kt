package br.com.alura.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class DTopicUpdate (

    @field:NotNull val
    id: Long,

    @field:NotEmpty
    @field:Size(min =5, max = 100)
    val title: String,

    @field:NotEmpty
    val message: String
)