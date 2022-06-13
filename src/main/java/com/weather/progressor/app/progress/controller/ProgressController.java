package com.weather.progressor.app.progress.controller;

import com.weather.progressor.app.calendar.MonthCalendar;
import com.weather.progressor.app.progress.domain.Progress;
import com.weather.progressor.app.progress.dto.CreateProgressRequest;
import com.weather.progressor.app.progress.service.ProgressService;
import com.weather.progressor.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/progress")
public class ProgressController {

    private final ProgressService progressService;

    @GetMapping("/calendar")
    public String calendar(@RequestParam(value = "targetDate", required = false) LocalDate targetParam, Model model){

        LocalDate today = DateUtil.today();
        LocalDate target = targetParam == null ? today : targetParam;

        MonthCalendar monthCalendar = MonthCalendar.create(target);

        model.addAttribute("calendar", monthCalendar);
        model.addAttribute("prev", target.minusMonths(1L));
        model.addAttribute("today", today);
        model.addAttribute("next", target.plusMonths(1L));

        return "/progress/calendar";
    }

    @GetMapping("/create")
    public String createForm(Model model){

        model.addAttribute("progress", new CreateProgressRequest());
        return "/progress/createProgressForm";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute CreateProgressRequest request, Model model){

        long savedId = progressService.openProgress(request);
        return "redirect:/progress/"+savedId;
    }

    @GetMapping("/summary")
    public String summary(){

        return "/progress/summary";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id){
        return "";
    }

}
