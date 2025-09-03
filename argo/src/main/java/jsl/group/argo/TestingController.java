package jsl.group.argo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class TestingController {
    @GetMapping("/testing")
    public String index(Model model) {
        model.addAttribute("date", LocalDate.now());
        return "index";
    }
}
