package com.github.malahor.shurl

import org.apache.commons.lang3.RandomStringUtils
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView

@Controller
@RequestMapping("/")
class ShUrlController(private val repository: ShUrlRepository) {

    @GetMapping
    fun home(model: Model, @ModelAttribute("input") input: Input, @ModelAttribute("link") link: String): String {
        model["input"] = input
        model["link"] = link
        return "start";
    }

    @PostMapping
    fun shorten(attributes: RedirectAttributes, @ModelAttribute("input") input: Input): RedirectView {
        val shurl = ShUrl(generateShort(), input.url!!)
        repository.save(shurl);
        attributes.addFlashAttribute("input", input)
        attributes.addFlashAttribute("link", shurl.short)
        return RedirectView("/")
    }

    private fun generateShort(): String {
        return RandomStringUtils.secure().nextAlphanumeric(6);
    }
}