package com.ll.sbtstudy;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecondController {
    @GetMapping("/random-quote")
    public String randomQuote(Model model) {

        String [] quotes = {
                "행복은 습관이다. 그것을 몸에 지니라" + "-하버드-",

                "고개를 숙이지 마십시오. 세상을 똑바로 정면으로 바라보십시오 - 헬렌 켈러 -",

                "고난의 시기에 동요하지 않는 것 , 이것은 진정 " + "칭찬받을 만한 뛰어난 인물의 증거이다. - 베토벤 -" ,

                "당신이 할 수 있다고 믿든 할 수 없다고 믿든" + "믿는대로 될 것이다. - 헬렌 켈러 -" ,

                "나의 죽음을 적에게 알리지 마라 " + "- 이순신 -"
        };

        int randInt = (int) (Math.random() * quotes.length);
        model.addAttribute("randomQuote" , quotes[randInt]);
        return "quote";
    }
}
