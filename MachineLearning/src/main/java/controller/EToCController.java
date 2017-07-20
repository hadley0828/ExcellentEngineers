package controller;

import bl.TranslateBL.TranslateBL;
import blservice.TranslateBLService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static bl.TranslateBL.Translation.ENTOCHS;

/**
 * Created by Administrator on 2017/7/17.
 */
@Controller
@RequestMapping(value = "/etoc")
public class EToCController {
    @RequestMapping(value = "/translate", method = RequestMethod.POST)
    @ResponseBody
    public String translate(@RequestParam("input") String input){
        TranslateBLService translateBLService = new TranslateBL();
        return translateBLService.translate(input, ENTOCHS);
    }
}
