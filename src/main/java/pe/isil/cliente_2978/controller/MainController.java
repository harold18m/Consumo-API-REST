package pe.isil.cliente_2978.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String redictToLogin(){
        return "redirect:/login";
    }
}
