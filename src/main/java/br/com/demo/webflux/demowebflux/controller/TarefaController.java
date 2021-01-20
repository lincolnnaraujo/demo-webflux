package br.com.demo.webflux.demowebflux.controller;

import br.com.demo.webflux.demowebflux.document.Tarefa;
import br.com.demo.webflux.demowebflux.pojo.PojoTarefa;
import br.com.demo.webflux.demowebflux.service.TarefaService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping("/app")
public class TarefaController {

    private final TarefaService tarefaService;

    TarefaController(final TarefaService tarefaService){
        this.tarefaService = tarefaService;
    }

    @GetMapping("/tarefa")
    public Flux<Tarefa> getTarefas(){
        return tarefaService.findAll();
    }

    @GetMapping("/tarefa-paginado")
    public Flux<Tarefa> getTarefasPaginado(@RequestParam(name = "pagina") Optional<String> pagina, @RequestParam(name = "tamanho") Optional<String> tamanho){
        final Pageable page = PageRequest.of(Integer.parseInt(pagina.orElse("0")), Integer.parseInt(tamanho.orElse("1")));
        return tarefaService.buscarTarefasPaginado(page);
    }

    @GetMapping("/tarefa/{id}")
    public Mono<Tarefa> getTarefa(@PathVariable String id){
        return tarefaService.findById(id);
    }

    @PostMapping("/tarefa")
    public Mono<Tarefa> saveTarefa(@RequestBody PojoTarefa pojo){
        return tarefaService.save(pojo);
    }
}
