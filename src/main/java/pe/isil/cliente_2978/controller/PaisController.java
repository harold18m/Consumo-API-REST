package pe.isil.cliente_2978.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.isil.cliente_2978.model.Pais;

@Controller
@RequestMapping("/paises")
public class PaisController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String GET_ALL_PAISES_API = "http://localhost:8090/api_2978/paises/"; // GET
    private static final String GET_BY_ID_PAISES_API = "http://localhost:8090/api_2978/paises/search/{id}"; // GET
    private static final String POST_PAISES_API = "http://localhost:8090/api_2978/paises/"; // POST
    private static final String PUT_PAISES_API = "http://localhost:8090/api_2978/paises/update/{id}"; // PUT
    private static final String DELETE_PAISES_API = "http://localhost:8090/api_2978/paises/delete/{id}"; // DELETE

    @GetMapping("")
    public String index(Model model) {
        ResponseEntity<Pais[]> responseEntity = restTemplate.getForEntity(GET_ALL_PAISES_API, Pais[].class);
        Pais[] paises = responseEntity.getBody();
        model.addAttribute("paises", paises);
        return "paises/index";
    }

    @GetMapping("/new")
    public String nuevo(Model model) {
        model.addAttribute("pais", new Pais());
        return "paises/nuevo";
    }

    @PostMapping("/store")
    public String store(Model model, Pais pais, RedirectAttributes ra) {
        restTemplate.postForEntity(POST_PAISES_API, pais, Pais.class);
        ra.addFlashAttribute("msgExito", "País registrado exitosamente");
        return "redirect:/paises";
    }

    @GetMapping("/editar/{id}")
    public String editar(Model model, @PathVariable("id") Integer id) {
        Pais pais = restTemplate.getForObject(GET_BY_ID_PAISES_API, Pais.class, id);
        model.addAttribute("pais", pais);
        return "paises/editar";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(Model model, @PathVariable("id") Integer id, @ModelAttribute Pais pais, RedirectAttributes ra) {
        restTemplate.put(PUT_PAISES_API, pais, id);
        ra.addFlashAttribute("msgExito", "País actualizado");
        return "redirect:/paises";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id, RedirectAttributes ra) {
        restTemplate.delete(DELETE_PAISES_API, id);
        ra.addFlashAttribute("msgExito", "País eliminado");
        return "redirect:/paises";
    }
}
