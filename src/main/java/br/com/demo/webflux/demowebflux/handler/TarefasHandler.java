package br.com.demo.webflux.demowebflux.handler;

import br.com.demo.webflux.demowebflux.document.Tarefa;
import br.com.demo.webflux.demowebflux.pojo.PojoTarefa;
import br.com.demo.webflux.demowebflux.service.TarefaService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;

@Component
public class TarefasHandler {

    private final TarefaService tarefaService;

    TarefasHandler(final TarefaService tarefaService){
        this.tarefaService = tarefaService;
    }

    public Mono<ServerResponse> findAll(ServerRequest request){
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(tarefaService.findAll(), Tarefa.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request){
        String id = request.pathVariable("id");
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(tarefaService.findById(id), Tarefa.class);
    }

    public Mono<ServerResponse> save(ServerRequest request){
        final Mono<PojoTarefa> pojo = request.bodyToMono(PojoTarefa.class);
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(pojo.flatMap(tarefaService::save), Tarefa.class));
    }
}
