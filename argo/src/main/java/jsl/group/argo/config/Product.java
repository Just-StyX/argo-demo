package jsl.group.argo.config;

import java.math.BigDecimal;

public record Product(
        String productName, BigDecimal productPrice
) {
}
