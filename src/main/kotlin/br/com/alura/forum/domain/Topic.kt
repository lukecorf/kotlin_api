package br.com.alura.forum.domain

import br.com.alura.forum.enum.StatusTopic
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Topic (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TOPIC")
    @SequenceGenerator(name = "SEQ_TOPIC", sequenceName = "SEQ_TOPIC", allocationSize = 1)
    var id: Long? = null,

    var title: String,

    var message: String,

    val creationDate: LocalDateTime = LocalDateTime.now(),

    @ManyToOne
    val course: Course,

    @ManyToOne
    val author: Users,

    @Enumerated(value = EnumType.STRING)
    val status: StatusTopic = StatusTopic.WITHOUT_RESPONSE,

    @OneToMany(mappedBy = "topic")
    val answers: List<Answers> = ArrayList()
)