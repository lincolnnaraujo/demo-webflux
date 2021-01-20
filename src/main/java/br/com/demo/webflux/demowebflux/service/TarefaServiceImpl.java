package br.com.demo.webflux.demowebflux.service;

import br.com.demo.webflux.demowebflux.document.Tarefa;
import br.com.demo.webflux.demowebflux.enums.StatusHistorico;
import br.com.demo.webflux.demowebflux.pojo.PojoTarefa;
import br.com.demo.webflux.demowebflux.repository.TarefaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
public class TarefaServiceImpl implements TarefaService{

    private final TarefaRepository tarefaRepository;

    TarefaServiceImpl(TarefaRepository tarefaRepository){
        this.tarefaRepository = tarefaRepository;
    }

    @Override
    public Flux<Tarefa> findAll() {
        return tarefaRepository.findAll();
    }

    @Override
    public Mono<Tarefa> findById(String id) {
        return tarefaRepository.findById(UUID.fromString(id));
    }

    @Override
    public Mono<Tarefa> save(PojoTarefa tarefa) {
        Tarefa novaTarefa = new Tarefa();

        if (tarefa.getId() == null
                || tarefa.getId().trim().isBlank()
                || tarefa.getId().trim().isEmpty()){

            novaTarefa.setId(UUID.randomUUID());
            novaTarefa.setTitulo(tarefa.getTitulo());
            novaTarefa.setDescricao(tarefa.getDescricao());
            novaTarefa.setTarefaFinalizada(tarefa.getFlagFinalizado());
            novaTarefa.setDataHoraCriacaoTarefa(LocalDateTime.now());
            novaTarefa.setDataHoraPrevisaoTermino(tarefa.getDataPrevistaConclusao());
            novaTarefa.addHistorico(StatusHistorico.entryOf(tarefa.getCodStatus()));

            return tarefaRepository.save(novaTarefa);
        }else {
            final Mono<Tarefa> tarefaAntiga = findById(tarefa.getId());

            if (Objects.isNull(tarefaAntiga)){
                return Mono.empty();
            }else {
                return tarefaAntiga.flatMap(value -> {
                    value.setTarefaFinalizada(tarefa.getFlagFinalizado());
                    value.setTitulo(tarefa.getTitulo());
                    value.setDescricao(tarefa.getDescricao());
                    value.setDataHoraPrevisaoTermino(tarefa.getDataPrevistaConclusao());
                    value.addHistorico(StatusHistorico.entryOf(tarefa.getCodStatus()));
                    return Mono.just(value);
                }).flatMap(tarefaRepository::save);
            }
        }
    }

    @Override
    public Flux<Tarefa> buscarTarefasPaginado(Pageable pageable) {
        Pageable page = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id"));
        return tarefaRepository.buscarTarefasPaginado(page);
    }
}
