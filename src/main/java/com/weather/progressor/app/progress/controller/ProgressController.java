package com.weather.progressor.app.progress.controller;

import com.weather.progressor.app.calendar.MonthCalendar;
import com.weather.progressor.app.progress.service.ProgressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.TimeZone;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProgressController {

    private final ProgressService progressService;

    @GetMapping("/")
    public String home(@RequestParam(value = "targetDate", required = false) LocalDate targetParam, Model model){

        LocalDate target = targetParam == null ? today() : targetParam;

        log.info("target={} targetDate={}", target, targetParam);
        MonthCalendar monthCalendar = MonthCalendar.create(target);

        model.addAttribute("calendar", monthCalendar);
        model.addAttribute("prev", target.minusMonths(1L));
        model.addAttribute("today", today());
        model.addAttribute("next", target.plusMonths(1L));

        return "/progress/main";
    }

    private static LocalDate today(){
        TimeZone timeZone = Calendar.getInstance().getTimeZone();
        ZonedDateTime zonedDateTime = Instant.now().atZone(timeZone.toZoneId());

        return zonedDateTime.toLocalDate();
    }

}
