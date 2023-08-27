package com.example.userservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/app")
public class CommonController {

    @GetMapping("/info")
    public RedirectView redirectToYouTubeChannel() {
        String youtubeChannelUrl = "https://www.youtube.com/watch?v=cjCgEdm3WF8";
        return new RedirectView(youtubeChannelUrl);
    }

    @GetMapping("/admin")
    public RedirectView redirectYouTubeChannel() {
        String youtubeChannelUrl = "https://www.youtube.com/watch?v=AnwgxRtWXLI&list=PLhfrWIlLOoKMe1Ue0IdeULQvEgCgQ3a1Bhttps://www.youtube.com/watch?v=AnwgxRtWXLI&list=PLhfrWIlLOoKMe1Ue0IdeULQvEgCgQ3a1B";
        return new RedirectView(youtubeChannelUrl);
    }
}
