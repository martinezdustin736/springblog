package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Random;

@Controller
public class RollController{
    @GetMapping("/roll-dice")
    public String viewRollDice() {
        return "roll-dice";
    }


    @GetMapping("/roll-dice/{n}")
    public String displayGuess(@PathVariable int n, Model model) {
        int randomNumber = new Random().nextInt(6 - 1 + 1) + 1;
        model.addAttribute("randomNumber", "Your roll: " + randomNumber);
        model.addAttribute("n", "Your guess: " + n);
        if (n == randomNumber) {
            model.addAttribute("result", "You win, good guess!");
        } else {
            model.addAttribute("result", "You lose, try again!");
        }
        return "roll-dice";
    }
}


