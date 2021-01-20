package br.com.demo.webflux.demowebflux.repository;

import br.com.demo.webflux.demowebflux.document.Tarefa;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface TarefaRepository extends ReactiveMongoRepository<Tarefa, UUID> {

    @Query("{ id: { $exists: true }}")
    Flux<Tarefa> buscarTarefasPaginado(final Pageable pageable);

}
