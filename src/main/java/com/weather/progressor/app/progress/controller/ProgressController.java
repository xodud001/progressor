package com.weather.progressor.app.progress.controller;

import com.weather.progressor.app.calendar.MonthCalendar;
import com.weather.progressor.app.member.domain.Member;
import com.weather.progressor.app.member.domain.SessionConst;
import com.weather.progressor.app.progress.domain.Progress;
import com.weather.progressor.app.progress.domain.ProgressStatus;
import com.weather.progressor.app.progress.dto.CreateProgressRequest;
import com.weather.progressor.app.progress.dto.ProgressDto;
import com.weather.progressor.app.progress.dto.ProgressSummaryRequest;
import com.weather.progressor.app.progress.dto.ProgressSummaryResponse;
import com.weather.progressor.app.progress.service.ProgressService;
import com.weather.progressor.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

        return "progress/calendar";
    }

    @GetMapping("/create")
    public String createForm(Model model){

        model.addAttribute("progress", new CreateProgressRequest());
        return "progress/createProgressForm";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("progress") CreateProgressRequest progress,
                         @SessionAttribute(SessionConst.LOGIN_MEMBER) Member member){

        long savedId = progressService.openProgress(progress, member);
        return "redirect:/progress/summary";
    }

    @GetMapping("/summary")
    public String summary(
            @ModelAttribute("summary") ProgressSummaryRequest summaryRequest,
            @SessionAttribute(SessionConst.LOGIN_MEMBER) Member member,
            Model model){

        List<ProgressStatus> statuses = getProgressStatuses(summaryRequest);

        List<ProgressDto> progressDtos = progressService.allProgress(member.getId(), statuses);

        var progress = ProgressSummaryResponse.builder()
            .statuses(statuses)
            .progresses(progressDtos)
            .build();

        model.addAttribute("progress", progress);
        model.addAttribute("statuses", createStatusMap());

        return "progress/summary";
    }

    private List<ProgressStatus> getProgressStatuses(ProgressSummaryRequest summaryRequest) {
        List<ProgressStatus> statuses;
        if(summaryRequest.getStatuses() == null){
            statuses = List.of(ProgressStatus.OPENED);
        }else{
            statuses = summaryRequest.getStatuses();
        }
        return statuses;
    }

    private Map<ProgressStatus, String> createStatusMap() {
        Map<ProgressStatus, String> statuses = new LinkedHashMap<>();
        statuses.put(ProgressStatus.OPENED, "Open");
        statuses.put(ProgressStatus.CLOSED, "Close");
        statuses.put(ProgressStatus.COMPLETED, "Complete");
        return statuses;
    }


    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id, Model model){
        Progress progress = progressService.getProgress(id);
        ProgressDto progressDto = ProgressDto.of(progress);

        model.addAttribute("progress", progressDto);

        return "progress/detail";
    }

}
