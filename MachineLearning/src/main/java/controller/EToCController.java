package controller;

import bl.TranslateBL.TranslateBL;
import blservice.TranslateBLService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static bl.TranslateBL.Translation.ENTOCHS;

/**
 * Created by Administrator on 2017/7/17.
 */
@Controller
@RequestMapping(value = "/etoc")
public class EToCController {
    String string;
    @RequestMapping(value = "/translate", method = RequestMethod.POST)
    public String translate(){
        TranslateBLService translateBLService = new TranslateBL();
        return translateBLService.translate(string,ENTOCHS);
    }
}
