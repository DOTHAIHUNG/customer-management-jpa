package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.service.CustomerService;
import com.codegym.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/provinces")
    public ModelAndView listProvinces() {
        Iterable <Province> provinces = provinceService.findAll();
        ModelAndView modelAndView = new ModelAndView("provinces/list");
        modelAndView.addObject("provinces", provinces);
        return modelAndView;
    }

    @GetMapping("/create-province")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("provinces/create");
        modelAndView.addObject("province", new Province());
        return modelAndView;
    }

    @PostMapping("/create-province")
    public String saveProvince(@ModelAttribute Province province, RedirectAttributes redirectAttributes) {
        provinceService.save(province);
        redirectAttributes.addFlashAttribute("message", "New province created successfully");
        return "redirect:/provinces";
    }

    @GetMapping("/edit-province/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Province province = provinceService.findById(id);
        if (province != null) {
            ModelAndView modelAndView = new ModelAndView("provinces/edit");
            modelAndView.addObject("province", province);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-province")
    public String updateProvince(@ModelAttribute("province") Province province, RedirectAttributes redirectAttributes) {
        provinceService.save(province);
        redirectAttributes.addFlashAttribute("message", "Province updated successfully");
        return "redirect:/provinces";
    }

    @RequestMapping("/delete-province/{id}")
    public String deleteProvince(@PathVariable Long id) {
        Province province = provinceService.findById(id);
        if (province != null) {
            provinceService.remove(id);
            return "redirect:/provinces";
        } else {
            return "redirect:/error.404";
        }
    }

    @GetMapping("/view-province/{id}")
    public ModelAndView viewProvince(@PathVariable Long id) {
        Province province = provinceService.findById(id);
        if (province == null) {
            return new ModelAndView("error.404");
        }
        Iterable <Customer> customers = customerService.findAllByProvince(province);
        ModelAndView modelAndView = new ModelAndView("provinces/view");
        modelAndView.addObject("province", province);
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }
}
