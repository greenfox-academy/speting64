package com.greenfox.connectionwithmysql.controller;

import com.greenfox.connectionwithmysql.repository.AssigneeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/assignees")
public class AssigneeController {

    @Autowired
    AssigneeRepository assigneeRepository;

    @RequestMapping(value = {"/", ""})
    public String listAssignee(Model model) {
        model.addAttribute("assignee", assigneeRepository.findAll());
        return "assignees";
    }
}
