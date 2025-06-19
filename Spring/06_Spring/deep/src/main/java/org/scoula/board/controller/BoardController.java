package org.scoula.board.controller;


import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
@Log4j2
@Api(tags = "게시글 관리")
public class BoardController {

    private final BoardService service;

    @ApiOperation(value = "게시글 목록", notes = "게시글 목록을 얻는 API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공적으로 요청이 처리되었습니다.", response = BoardDTO.class),
            @ApiResponse(code = 400, message = "잘못된 요청입니다."),
            @ApiResponse(code = 500, message = "서버에서 오류가 발생했습니다.")
    })

    @GetMapping("")
    public ResponseEntity<List<BoardDTO>> getList(){
        return ResponseEntity.ok(service.getList());
    }

    @ApiOperation(value = "상세정보얻기", notes = "게시글상제정보를얻는API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공적으로요청이처리되었습니다.", response = BoardDTO.class),
            @ApiResponse(code = 400, message = "잘못된요청입니다."),
            @ApiResponse(code = 500, message = "서버에서오류가발생했습니다.")
    })

    @GetMapping("/{no}")
    public ResponseEntity<BoardDTO> get(
            @ApiParam(value = "게시글 ID", required = true, example = "1")
            @PathVariable Long no){
        return ResponseEntity.ok(service.get(no));
    }
    @ApiOperation(value = "게시글생성", notes = "게시글생성API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공적으로요청이처리되었습니다.", response = BoardDTO.class),
            @ApiResponse(code = 400, message = "잘못된요청입니다."),
            @ApiResponse(code = 500, message = "서버에서오류가발생했습니다.")
    })
    @PostMapping("")
    public ResponseEntity<BoardDTO> create(
            @ApiParam(value = "게시글객체", required = true)
            @RequestBody BoardDTO board) {
        return ResponseEntity.ok(service.create(board));
    }


    @ApiOperation(value = "게시글수정", notes = "게시글수정API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공적으로요청이처리되었습니다.", response = BoardDTO.class),
            @ApiResponse(code = 400, message = "잘못된요청입니다."),
            @ApiResponse(code = 500, message = "서버에서오류가발생했습니다.")
    })


    @PutMapping("/{no}")
    public ResponseEntity<BoardDTO> update(
            @ApiParam(value = "게시글ID", required = true, example = "1")
            @PathVariable Long no,
            @ApiParam(value = "게시글객체", required = true) @RequestBody BoardDTO board) {
        return ResponseEntity.ok(service.update(board));
    }
    @ApiOperation(value = "게시글삭제", notes = "게시글삭제API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공적으로요청이처리되었습니다."),
            @ApiResponse(code = 400, message = "잘못된요청입니다."),
            @ApiResponse(code = 500, message = "서버에서오류가발생했습니다.")
    })

    @DeleteMapping("/{no}")
    public ResponseEntity<BoardDTO> delete(
            @ApiParam(value = "게시글ID", required = true, example = "1")
            @PathVariable
            Long no) {
        return ResponseEntity.ok(service.delete(no));
    }
}


