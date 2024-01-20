package demo.graphql.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.net.InetAddress;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(Model model){
        try {
            model.addAttribute("hostName", InetAddress.getLocalHost().getHostName());
        }catch (Exception e){
            System.out.println("Hostname can not be resolved");
        }
        return "index";
    }
}
