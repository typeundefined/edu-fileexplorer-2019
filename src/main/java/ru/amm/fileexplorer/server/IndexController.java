package ru.amm.fileexplorer.server;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller


public class IndexController {
    @RequestMapping(path="/", method = RequestMethod.GET)
    public ModelAndView index() {
        Map<String, String> data = new HashMap<>();
        data.put("username", "Вася");

        //ResponseEntity<ModelAndView> m=new ResponseEntity<ModelAndView>();
        return new ModelAndView("index", data);
    }
}
