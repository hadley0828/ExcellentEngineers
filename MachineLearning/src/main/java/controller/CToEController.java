package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by I Like Milk on 2017/6/19.
 */
@Controller
@RequestMapping(value = "/ctoe")
public class CToEController {
    @RequestMapping(value = "/translate", method = RequestMethod.POST)
    public String translate(String string){
        return null;
    }
}
