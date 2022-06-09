package com.weather.progressor.app.progress.controller;

import com.weather.progressor.app.calendar.MonthCalendar;
import com.weather.progressor.app.progress.service.ProgressService;
import com.weather.progressor.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProgressController {

    private final ProgressService progressService;

    @GetMapping("/calendar")
    public String home(@RequestParam(value = "targetDate", required = false) LocalDate targetParam, Model model){

        LocalDate today = DateUtil.today();
        LocalDate target = targetParam == null ? today : targetParam;

        MonthCalendar monthCalendar = MonthCalendar.create(target);

        model.addAttribute("calendar", monthCalendar);
        model.addAttribute("prev", target.minusMonths(1L));
        model.addAttribute("today", today);
        model.addAttribute("next", target.plusMonths(1L));

        return "/progress/calendar";
    }


}
