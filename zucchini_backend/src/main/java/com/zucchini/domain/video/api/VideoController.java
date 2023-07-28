package com.zucchini.domain.video.api;

import com.zucchini.domain.video.dto.request.AddVideoRequest;
import com.zucchini.domain.video.dto.response.FindVideoResponse;
import com.zucchini.domain.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/video")
public class VideoController {

    private final VideoService videoService;

    /**
     * 비디오 등록
     */
    @PostMapping
    public ResponseEntity<Void> addVideo(@Valid @RequestBody AddVideoRequest addVideoRequest){
        videoService.addVideo(addVideoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 해당 아이템의 비디오 조회
     */
    @GetMapping("/{no}")
    public ResponseEntity<FindVideoResponse> findVideo(@PathVariable int no){
        FindVideoResponse findVideoResponse = videoService.findVideo(no);
        return ResponseEntity.status(HttpStatus.OK).body(findVideoResponse);
    }

    /**
     * 해당 아이템의 비디오 기한 연장
     */
    @PutMapping("/extension/{no}")
    public ResponseEntity<Void> extendVideoDeadLine(@PathVariable int no){
        videoService.extendVideoDeadLine(no);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
