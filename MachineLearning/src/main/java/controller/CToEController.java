package controller;

import bl.TranslateBL.TranslateBL;
import blservice.TranslateBLService;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static bl.TranslateBL.Translation.CHSTOEN;

/**
 * Created by I Like Milk on 2017/6/19.
 */
@Controller
@RequestMapping(value = "/ctoe")
public class CToEController {
    @RequestMapping(value = "/translate", method = RequestMethod.POST)
    @ResponseBody
    public String translate(@RequestParam("input") String input){
        TranslateBLService translateBLService = new TranslateBL();
        return translateBLService.translate(input,CHSTOEN);
    }
}
