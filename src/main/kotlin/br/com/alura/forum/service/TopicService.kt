package br.com.alura.forum.service

import br.com.alura.forum.domain.Topic
import br.com.alura.forum.dto.DTopicRequest
import br.com.alura.forum.dto.DTopicResponse
import br.com.alura.forum.dto.DTopicUpdate
import br.com.alura.forum.enum.ErrorMessage
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicMapper
import br.com.alura.forum.repository.TopicRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicService(private val repository: TopicRepository,
                   private val topicMapper: TopicMapper ) {

    fun findAll(): List<DTopicResponse> {
        return repository.findAll().stream().map { t -> topicMapper.map(t) }.collect(Collectors.toList());
    }

    fun findById(id: Long): DTopicResponse {
        val topic =  repository.findById(id).orElseThrow{NotFoundException(ErrorMessage.NOT_FOUND.message)}
        return topicMapper.map(topic)
    }

    fun insert(topicRequest: DTopicRequest): DTopicResponse{
        var topic = topicMapper.mapEntity(topicRequest)
        topic = repository.save(topic)
        return topicMapper.map(topic)
    }

    fun update(topicRequest: DTopicUpdate): DTopicResponse {
        var topic =  repository.findById(topicRequest.id).orElseThrow{NotFoundException(ErrorMessage.NOT_FOUND.message)}
        topic.title = topicRequest.title
        topic.message = topicRequest.message
        topic = repository.save(topic)
        return topicMapper.map(topic)
    }

    fun delete(id: Long){
        repository.deleteById(id);
    }
}