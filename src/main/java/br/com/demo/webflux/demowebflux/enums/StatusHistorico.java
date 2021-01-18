package br.com.demo.webflux.demowebflux.enums;

import lombok.Getter;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
public enum StatusHistorico {

    INCLUSAO("01", "incluído"),
    ALTERACAO("02", "alterado"),
    EXCLUSAO("03", "excluído"),
    CANCELAMENTO("04", "cancelado");

    private static final Map<String, StatusHistorico> mapString = new HashMap<>();

    static {
        for (final StatusHistorico status : EnumSet.allOf(StatusHistorico.class)) {
            mapString.put(status.getCodigo(), status);
        }
    }

    private final String codigo;
    private final String descricao;

    StatusHistorico(String cod, String desc){
        this.codigo = cod;
        this.descricao = desc;
    }

    public static StatusHistorico entryOf(final String cod){
        return Objects.nonNull(cod) ? mapString.get(cod) : null;
    }

    public static StatusHistorico entryOf(final Integer cod){
        return Objects.nonNull(cod) ? mapString.get(cod.toString()) : null;
    }

    public static boolean exists(final String cod){
        return Objects.nonNull(cod) && mapString.containsKey(cod);
    }

}
