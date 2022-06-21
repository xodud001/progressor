package com.weather.progressor.app.progressdetail.controller;

import com.weather.progressor.app.member.domain.Member;
import com.weather.progressor.app.member.domain.SessionConst;
import com.weather.progressor.app.progress.domain.Progress;
import com.weather.progressor.app.progress.domain.ProgressStatus;
import com.weather.progressor.app.progress.dto.ProgressDto;
import com.weather.progressor.app.progress.service.ProgressService;
import com.weather.progressor.app.progressdetail.dto.CreateDetailForm;
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
                             @SessionAttribute(SessionConst.LOGIN_MEMBER) Member member){
        List<ProgressDto> progresses = progressService.allProgress(member.getId(), List.of(ProgressStatus.OPENED));

        model.addAttribute("detail", new CreateDetailForm());
        model.addAttribute("progresses", progresses);
        return "detail/createDetailForm";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute CreateDetailForm form){
        detailService.createDetail(form);
        return "redirect:/progress/" + form.getProgressId();
    }
}
