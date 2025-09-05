package jsl.group.argo;

import jsl.group.argo.config.ProductList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Controller
public class TestingController {
    private static final Logger log = LoggerFactory.getLogger(TestingController.class);
    private final RestTemplate restTemplate;

    public TestingController(RestTemplateBuilder restTemplateBuilder, @Value("${app.catalog}") String catalogServiceUrl) {
        this.restTemplate = restTemplateBuilder.rootUri(catalogServiceUrl).build();
    }

    @GetMapping("/testing")
    public String index(Model model) {
        ProductList productList = restTemplate.getForObject("/products", ProductList.class);
        log.info("Products: {}", productList);
        model.addAttribute("name", "J.S.L Group Inc");
        model.addAttribute("date", LocalDate.now());
        assert productList != null;
        model.addAttribute("products", productList.products());
        model.addAttribute("productListDateTime", productList.localDateTime());
        return "index";
    }
}
