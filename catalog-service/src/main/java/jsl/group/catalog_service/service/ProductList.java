package jsl.group.catalog_service.service;

import java.time.LocalDateTime;
import java.util.List;

public record ProductList(
        List<Product> products, LocalDateTime localDateTime
) {
}
