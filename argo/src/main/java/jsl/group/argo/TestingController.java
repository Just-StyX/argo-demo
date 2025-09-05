package jsl.group.argo;

import jsl.group.argo.config.ProductList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Map;

@Controller
public class TestingController {
    private static final Logger log = LoggerFactory.getLogger(TestingController.class);
    private final RestTemplate restTemplate;

    public TestingController(RestTemplateBuilder restTemplateBuilder, @Value("${app.catalog}") String catalogServiceUrl) {
        this.restTemplate = restTemplateBuilder.rootUri(catalogServiceUrl).build();
    }

    @GetMapping("/testing")
    public String index(Model model, @RequestParam(value = "x-dark-launch", required = false) String xDarkLaunch) {
        ProductList productList;
        if (xDarkLaunch != null) {
            productList = restTemplate.exchange(
                    "/products",
                    HttpMethod.GET,
                    httpEntityWithHeaders(MultiValueMap.fromSingleValue(Map.of("x-dark-launch", xDarkLaunch))),
                    ProductList.class
            ).getBody();
            assert productList != null;
            model.addAttribute("products", productList.products());
        } else {
            productList = restTemplate.getForObject("/products", ProductList.class);
            assert productList != null;
            model.addAttribute("products", productList.products().subList(0, productList.products().size() - 1));
        }
        log.info("Products: {}", productList);
        model.addAttribute("name", "J.S.L Group Inc");
        model.addAttribute("date", LocalDate.now());
        model.addAttribute("productListDateTime", productList.localDateTime());
        return "index";
    }

    private <T> HttpEntity<T> httpEntityWithHeaders(MultiValueMap<String, String> headers) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.addAll(headers);
        return new HttpEntity<>(httpHeaders);
    }
}
