package com.codeup.springblog.controllers;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller

public class MathController {
//    @RequestMapping(path = "/add/{x}/and/{y}", method = RequestMethod.GET)
//    @ResponseBody
//    public int addNums(@PathVariable int x, @PathVariable int y){
//        return x + y;
//    }

//    @GetMapping("/add/{x}/and/{y}")
//    @ResponseBody
//    public int add(@PathVariable int x, @PathVariable int y){
//        return x + y;
//    }

    /////Do it this way to render correctly in all browsers/////////////////////////////////////
    @GetMapping("/add/{num1}/and/{num2}")
    @ResponseBody
    public String addition(@PathVariable int num1, @PathVariable int num2){
        return String.valueOf(num1 + num2);
    }

//    @RequestMapping(path = "/subtract/{x}/from/{y}", method = RequestMethod.GET)
//    @ResponseBody
//    public int subtractNums(@PathVariable int x, @PathVariable int y){
//        return x - y;
//    }

    @GetMapping("subtract/{x}/from/{y}")
    @ResponseBody
    public String subtract(@PathVariable int x, @PathVariable int y){
        return String.valueOf(y - x);
    }

//    @RequestMapping(path = "/multiply/{x}/and/{y}", method = RequestMethod.GET)
//    @ResponseBody
//    public int multiplyNums(@PathVariable int x, @PathVariable int y){
//        return x * y;
//    }

    @GetMapping("multiply/{x}/and/{y}")
    @ResponseBody
    public String multiply(@PathVariable int x, @PathVariable int y){
        return String.valueOf(x * y);
    }

//    @RequestMapping(path = "/divide/{x}/by/{y}", method = RequestMethod.GET)
//    @ResponseBody
//    public int divideNums(@PathVariable int x, @PathVariable int y){
//        return x / y;
//    }

    @GetMapping("divide/{x}/by/{y}")
    @ResponseBody
    public String division(@PathVariable int x, @PathVariable int y){
        return String.valueOf(x / y);
    }


}
