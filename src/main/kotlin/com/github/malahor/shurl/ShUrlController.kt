package com.github.malahor.shurl

import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.validator.routines.UrlValidator
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import org.springframework.web.servlet.view.RedirectView

@Controller
@RequestMapping("/")
class ShUrlController(private val repository: ShUrlRepository) {

    @GetMapping
    fun home(
        model: Model,
        @ModelAttribute("input") input: Input,
        @ModelAttribute("link") link: String,
        @ModelAttribute("error") error: String?
    ): String {
        model["input"] = input
        model["link"] = link
        model["error"] = error ?: ""
        return "start";
    }

    @PostMapping
    fun shorten(attributes: RedirectAttributes, @ModelAttribute("input") input: Input): RedirectView {
        if (UrlValidator().isValid(input.url)) {
            val shurl = findExisting(input.url!!) ?: save(input.url)
            attributes.addFlashAttribute("input", input)
            attributes.addFlashAttribute("link", buildLink(shurl.short))
        } else {
            attributes.addFlashAttribute("error", "Invalid URL")
        }
        return RedirectView("/")
    }

    @GetMapping("{id}")
    fun redirect(@PathVariable("id") id: String): RedirectView {
        val shurl = repository.findById(id)
        if (shurl.isEmpty) return RedirectView("/")
        return RedirectView(shurl.get().long)
    }

    private fun findExisting(url: String): ShUrl? {
        return repository.findByLong(url)
    }

    private fun save(url: String): ShUrl {
        return repository.save(ShUrl(generateShort(), url));
    }

    private fun generateShort(): String {
        val short = RandomStringUtils.secure().nextAlphanumeric(6);
        if (repository.findById(short).isPresent) return generateShort()
        return short
    }

    private fun buildLink(short: String): String {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(short).build().toUriString()
    }
}