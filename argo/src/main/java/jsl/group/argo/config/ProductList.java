package jsl.group.argo.config;

import java.time.LocalDateTime;
import java.util.List;

public record ProductList(
        List<Product> products, LocalDateTime localDateTime
) {
}
