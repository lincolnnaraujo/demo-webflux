package br.com.demo.webflux.demowebflux.config;

import br.com.demo.webflux.demowebflux.handler.TarefasHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class TarefasRouter {

    @Bean
    public RouterFunction<ServerResponse> route(TarefasHandler handler){
        return RouterFunctions
                .route(GET("/app/function/tarefa").and(accept(MediaType.APPLICATION_JSON)), handler::findAll)
                .andRoute(GET("/app/function/tarefa/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::findById)
                .andRoute(POST("/app/function/tarefa").and(accept(MediaType.APPLICATION_JSON)), handler::save);

    }

}
