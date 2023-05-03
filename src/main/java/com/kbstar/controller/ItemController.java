package com.kbstar.controller;


import com.kbstar.dto.Cust;
import com.kbstar.dto.Item;
import com.kbstar.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    String dir = "item/";

    // 127.0.0.1/cust
    @RequestMapping("")
    public String main(Model model){
        model.addAttribute("left", dir+"left");
        model.addAttribute("center", dir+"center");
        return "index";
    }
    @RequestMapping("/add")
    public String add(Model model){
        model.addAttribute("left", dir+"left");
        model.addAttribute("center", dir+"add");
        return "index";
    }

    @RequestMapping("/all")
    public String all(Model model) throws Exception {
        List<Item> list = null;
        list = itemService.get();
        model.addAttribute("ilist", list);
        model.addAttribute("center", dir+"all");
        return "index";
    }

}