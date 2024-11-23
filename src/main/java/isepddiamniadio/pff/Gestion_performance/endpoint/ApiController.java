package isepddiamniadio.pff.Gestion_performance.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController
{
    @GetMapping("/api/data")
    public String getData(){
        return "communication  reussie";
    }
}
