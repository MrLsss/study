package com.websocket.controller;

import com.websocket.server.WebSocketServer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Copyright: Copyright (c) 2021
 *
 * <p> Description:
 *
 * @author liushuai
 * @version 1.0.0
 * @createTime 2021年04月19日 22:35
 */
@Controller
@RequestMapping("/websocket")
public class WebSocketController {


    @GetMapping({"", "/"})
    public String index() {
        return "index";
    }

    /**
     * 通过调用该接口，实现服务端向客户端发送消息
     */
    @ResponseBody
    @PostMapping("/push/{toUserId}")
    public ResponseEntity<String> push2Web(String message, @PathVariable String toUserId) throws IOException {
        WebSocketServer.sendInfo(message, toUserId);
        return ResponseEntity.ok("消息推送成功");
    }




}
