package org.scoula.board.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.scoula.util.UploadFiles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Log4j2

public class BoardController {

    private final BoardService service;

    @GetMapping("/list")
    public void list(Model model){
        log.info("list......");
        model.addAttribute("list", service.getList());
    }

    @GetMapping("/get")
    public void get(@RequestParam("no") Long no, Model model) {

        log.info("get......");
        model.addAttribute("board", service.get(no));
    }
    @GetMapping("/create")
    public void create(){
        log.info("create......");
    }
    //글쓰기 버튼을 누르면 create로만 가면 되는데... Get 방식 사용. PostMapping은 서버에 전달하기 위함. 더 자세한 공부 알아서 할 것.

    @PostMapping("/create")
    public String cretate(BoardDTO board, RedirectAttributes ra) {
        log.info("create......");
        service.create(board);
        ra.addFlashAttribute("result", board.getNo());
        return "redirect:/board/list";
    }
    @GetMapping({ "/update"})
    public void update(@RequestParam("no") Long no , Model model) {
        log.info("update......");
        model.addAttribute("board", service.get(no));
    }
    @PostMapping("/update")
    public String update(BoardDTO board, RedirectAttributes ra) {
        if (service.update(board)) {
            ra.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("no") Long no){
        log.info("delete......");
        service.delete(no);
        return "redirect:/board/list";
    }
    @GetMapping("/download/{no}")
    @ResponseBody // view를사용하지않고, 직접내보냄
    public void download(@PathVariable("no") Long no, HttpServletResponse response) throws Exception {
        BoardAttachmentVO attach = service.getAttachment(no);
        File file= new File(attach.getPath());
        UploadFiles.download(response, file, attach.getFilename());
    }
}



