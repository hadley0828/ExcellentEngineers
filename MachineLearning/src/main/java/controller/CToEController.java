package controller;

import bl.TranslateBL.TranslateBL;
import blservice.TranslateBLService;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static bl.TranslateBL.Translation.CHSTOEN;

/**
 * Created by I Like Milk on 2017/6/19.
 */
@Controller
@RequestMapping(value = "/ctoe")
public class CToEController {
    String string;
    @RequestMapping(value = "/translate", method = RequestMethod.POST)
    public String translate(){
        TranslateBLService translateBLService = new TranslateBL();
        return translateBLService.translate(string,CHSTOEN);
    }
}
