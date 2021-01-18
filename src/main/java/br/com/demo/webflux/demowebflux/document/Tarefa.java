package br.com.demo.webflux.demowebflux.document;

import br.com.demo.webflux.demowebflux.enums.StatusHistorico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "tarefa")
public class Tarefa {

    @Id
    private UUID id;

    private String titulo;

    private String descricao;

    private LocalDateTime dataHoraCriacaoTarefa;

    private LocalDateTime dataHoraPrevisaoTermino;

    private boolean tarefaFinalizada;

    private List<Historico> historicoList;

    public void addHistorico(final StatusHistorico statusHistorico){
        if(Objects.nonNull(statusHistorico)){
            Historico novoHist = new Historico();
            novoHist.setDataInclusao(LocalDateTime.now());
            novoHist.setId(UUID.randomUUID());
            novoHist.setStatus(statusHistorico);

            if (Objects.isNull(historicoList)){
                historicoList = new ArrayList<>();
                historicoList.add(novoHist);
            }else{
                historicoList.add(novoHist);
            }
        }
    }
}
