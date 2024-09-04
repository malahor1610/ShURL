package com.github.malahor.shurl

import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.validator.routines.UrlValidator
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
    fun home(
        model: Model,
        @ModelAttribute("input") input: Input,
        @ModelAttribute("link") link: String,
        @ModelAttribute("error") error: String
    ): String {
        model["input"] = input
        model["link"] = link
        model["error"] = error
        return "start";
    }

    @PostMapping
    fun shorten(attributes: RedirectAttributes, @ModelAttribute("input") input: Input): RedirectView {
        if (UrlValidator().isValid(input.url)) {
            val shurl = findExisting(input.url!!) ?: save(input.url)
            attributes.addFlashAttribute("input", input)
            attributes.addFlashAttribute("link", shurl.short)
        } else {
            attributes.addFlashAttribute("error", "Invalid URL")
        }
        return RedirectView("/")
    }

    private fun findExisting(url: String): ShUrl? {
        return repository.findByLong(url)
    }

    private fun save(url: String): ShUrl {
        return repository.save(ShUrl(generateShort(), url));
    }

    private fun generateShort(): String {
        return RandomStringUtils.secure().nextAlphanumeric(6);
    }
}