package br.com.alura.forum.service

import br.com.alura.forum.domain.Topic
import br.com.alura.forum.dto.DTopicRequest
import br.com.alura.forum.dto.DTopicResponse
import br.com.alura.forum.dto.DTopicUpdate
import br.com.alura.forum.mapper.TopicMapper
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicService(private var topics: List<Topic> = ArrayList(),
                   private val topicMapper: TopicMapper ) {

    fun findAll(): List<DTopicResponse> {
        return topics.stream().map { t -> topicMapper.map(t) }.collect(Collectors.toList());
    }

    fun findById(id: Long): DTopicResponse {
        val topic =  topics.stream().filter { t -> t.id == id }.findFirst().get()
        return topicMapper.map(topic)
    }

    fun insert(topicRequest: DTopicRequest): DTopicResponse{
        val topic = topicMapper.mapEntity(topicRequest)
        topic.id = topics.size.toLong()+1
        topics = topics.plus(topic)
        return topicMapper.map(topic)
    }

    fun update(topicRequest: DTopicUpdate): DTopicResponse {
        val topic =  topics.stream().filter { t -> t.id == topicRequest.id }.findFirst().get()
        val newTopic = Topic(
            id = topicRequest.id,
            title = topicRequest.title,
            message = topicRequest.message,
            author = topic.author,
            course = topic.course,
            answers = topic.answers,
            status = topic.status,
            creationDate = topic.creationDate
        )
        topics = topics.minus(topic).plus(newTopic)
        return topicMapper.map(newTopic)
    }

    fun delete(id: Long){
        val topic =  topics.stream().filter { t -> t.id == id }.findFirst().get()
        topics = topics.minus(topic)
    }
}