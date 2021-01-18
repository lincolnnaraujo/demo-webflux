package br.com.demo.webflux.demowebflux.repository;

import br.com.demo.webflux.demowebflux.document.Tarefa;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

public interface TarefaRepository extends ReactiveMongoRepository<Tarefa, UUID> {
}
