package com.example.springkadaiform.controller;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springkadaiform.form.ContactForm;

@Controller
public class ContactFormController {

    // フォーム表示
    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("contactForm", new ContactForm());
        return "contactFormView";
    }

    // 確認画面表示
    @PostMapping("/confirm")
    public String confirm(
            @Valid ContactForm contactForm,
            BindingResult bindingResult,
            Model model) {

        // バリデーションNGならフォームに戻る
        if (bindingResult.hasErrors()) {
            return "contactFormView";
        }

        model.addAttribute("contactForm", contactForm);
        return "confirmView";
    }
}
