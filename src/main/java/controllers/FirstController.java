package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/first")   //будет добавлено к пути всех методов, например http://localhost:8080/first/hello
public class FirstController {
    @GetMapping("/hello")
    // метод ожидает запрос с двумя параметрами (name и surname) по адресу /hello
    // в качестве отвера возвращает представление с именем first/hello (hello.html в пакете first)
    public String helloPage(@RequestParam (value = "name", required = false) String name,
                            @RequestParam (value = "surname", required = false) String surname,
                            HttpServletResponse response, Model model) {  // внедряется Spring'ом автоматически (POJO)

        response.setContentType("text/plain;charset=UTF-8");
        // кладем информацию в модель
        String s = "Hello, " + name + " " + surname;
        model.addAttribute("massage", s);

        // модель будет отправлена в представление, указанное в return
        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodbyePage() {
        return "first/goodbye";
    }
}
