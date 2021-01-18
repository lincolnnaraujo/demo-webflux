package br.com.demo.webflux.demowebflux.document;

import br.com.demo.webflux.demowebflux.enums.StatusHistorico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("historico")
public class Historico {

    @Id
    private UUID id;

    private LocalDateTime dataInclusao;

    private StatusHistorico status;
}
