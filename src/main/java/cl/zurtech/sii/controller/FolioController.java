package cl.zurtech.sii.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/folio")
public class FolioController {


    @GetMapping()
    public void getFolios() {

    }

}
