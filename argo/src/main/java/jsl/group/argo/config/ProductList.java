package jsl.group.argo.config;

import java.time.LocalDateTime;
import java.util.List;

public record ProductList(
        List<Product> products, LocalDateTime localDateTime
) {
    public static ProductList getInstance() { return new ProductList(List.of(), LocalDateTime.now()); }
}
