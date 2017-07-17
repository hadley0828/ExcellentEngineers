package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/7/17.
 */
@Controller
@RequestMapping(value = "/etoc")
public class EToCController {
    @RequestMapping(value = "/translate")
    public String translate(String string){
        return null;
    }
}
