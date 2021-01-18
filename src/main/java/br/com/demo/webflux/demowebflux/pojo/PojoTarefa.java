package br.com.demo.webflux.demowebflux.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PojoTarefa {

    private String id;

    private String titulo;

    private String descricao;

    private String codStatus;

    private Boolean flagFinalizado;

    private LocalDateTime dataPrevistaConclusao;
}
