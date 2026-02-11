package com.example.springkadaiform.controller;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springkadaiform.form.ContactForm;

@Controller
public class ContactFormController {

    // フォーム表示
    @GetMapping("/form")
    public String showForm(Model model) {

        // リダイレクト時にデータがなければ初期化
        if (!model.containsAttribute("contactForm")) {
            model.addAttribute("contactForm", new ContactForm());
        }

        return "contactFormView";
    }

    // 確認画面表示（URLは /confirm）
    @PostMapping("/confirm")
    public String confirm(
            @Valid ContactForm contactForm,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model) {

        // ❌ バリデーションエラー時
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("contactForm", contactForm);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.contactForm",
                    bindingResult);

            return "redirect:/form";
        }

        // ✅ バリデーションOK時
        model.addAttribute("contactForm", contactForm);
        return "confirmView";   // ← URLは /confirm のまま
    }
}
