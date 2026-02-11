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

        // リダイレクト時に値がなければ初期化
        if (!model.containsAttribute("contactForm")) {
            model.addAttribute("contactForm", new ContactForm());
        }

        return "contactFormView";
    }

    // 確認画面表示
    @PostMapping("/confirm")
    public String confirm(
            @Valid ContactForm contactForm,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        // ❌ バリデーションエラー
        if (bindingResult.hasErrors()) {

            // フォームの値を保持
            redirectAttributes.addFlashAttribute("contactForm", contactForm);

            // エラー情報を保持（これ超重要）
            redirectAttributes.addFlashAttribute(
                "org.springframework.validation.BindingResult.contactForm",
                bindingResult);

            return "redirect:/form";
        }

        // 正常時
        redirectAttributes.addFlashAttribute("contactForm", contactForm);
        return "redirect:/confirmView";
    }

    // 確認画面表示用
    @GetMapping("/confirmView")
    public String confirmView() {
        return "confirmView";
    }
}
