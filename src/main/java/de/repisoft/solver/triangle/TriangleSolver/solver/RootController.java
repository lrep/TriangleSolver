package de.repisoft.solver.triangle.TriangleSolver.solver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {
    @RequestMapping("/")
    public String root() {
        return "forward:index.html";
    }
}