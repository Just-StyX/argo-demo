package jsl.group.catalog_service.service;

import java.math.BigDecimal;

public record Product(
        String productName, BigDecimal productPrice
) {
}
