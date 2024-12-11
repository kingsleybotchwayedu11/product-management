package product.mangagement.productm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomControler {
    @GetMapping("say-hello")
    public int getMethodName() {
        return 20;
    }
    
}
