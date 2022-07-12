package com.weather.progressor.app.progressdetail.controller;

import com.weather.progressor.app.member.domain.Member;
import com.weather.progressor.app.member.domain.SessionConst;
import com.weather.progressor.app.progress.domain.Progress;
import com.weather.progressor.app.progress.domain.ProgressStatus;
import com.weather.progressor.app.progress.dto.ProgressDto;
import com.weather.progressor.app.progress.service.ProgressService;
import com.weather.progressor.app.progressdetail.dto.CreateDetailForm;
import com.weather.progressor.app.progressdetail.dto.EditDetailForm;
import com.weather.progressor.app.progressdetail.dto.ProgressDetailDto;
import com.weather.progressor.app.progressdetail.dto.ProgressDetailResponse;
import com.weather.progressor.app.progressdetail.service.ProgressDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/detail")
public class ProgressDetailController {

    private final ProgressService progressService;
    private final ProgressDetailService detailService;

    @GetMapping("/create")
    public String createForm(Model model,
                             @RequestParam(value = "progress", required = false) Long progressId,
                             @SessionAttribute(SessionConst.LOGIN_MEMBER) Member member){
        List<ProgressDto> progresses = progressService.allProgress(member.getId(), List.of(ProgressStatus.OPENED));
        ProgressDto targetProgress = findTargetProcess(progressId, progresses);

        CreateDetailForm createDetailForm = new CreateDetailForm();
        createDetailForm.setProgressId(progressId);


        model.addAttribute("detail", createDetailForm);
        model.addAttribute("targetProgress", targetProgress);
        model.addAttribute("progresses", progresses);
        return "detail/createDetailForm";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute CreateDetailForm form){
        detailService.createDetail(form);
        return "redirect:/progress/" + form.getProgressId();
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id,
                           @RequestParam("progressId") Long progressId,
                           Model model) {
        ProgressDto progress = ProgressDto.of(progressService.getProgress(progressId));
        ProgressDetailResponse detail = ProgressDetailResponse.of(detailService.findById(id));

        model.addAttribute("progress", progress);
        model.addAttribute("detail", detail);

        return "detail/editDetailForm";
    }

    @PostMapping("/{id}/edit")
    public String editForm(@PathVariable Long id,
                           @RequestParam("progressId") Long progressId,
                           @ModelAttribute EditDetailForm form) {

        detailService.updateDetail(id, form);

        return "redirect:/progress/"+progressId;
    }

    private ProgressDto findTargetProcess(Long progressId, List<ProgressDto> progresses) {
        return progresses.stream()
                .filter(p -> p.getId().equals(progressId))
                .findFirst()
                .orElse(new ProgressDto());
    }
}
