package br.com.demo.webflux.demowebflux.service;

import br.com.demo.webflux.demowebflux.document.Tarefa;
import br.com.demo.webflux.demowebflux.pojo.PojoTarefa;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TarefaService {

    Flux<Tarefa> findAll();

    Mono<Tarefa> findById(String id);

    Mono<Tarefa> save(PojoTarefa tarefa);

    Flux<Tarefa> buscarTarefasPaginado(Pageable pageable);

}
