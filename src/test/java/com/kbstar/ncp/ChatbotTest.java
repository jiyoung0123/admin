package com.kbstar.ncp;

import com.kbstar.util.ChatBotUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@Slf4j
@SpringBootTest
class ChatbotTest {

    @Test
    void contextLoads() throws IOException {
        String txt = "채팅하자";
        String result = ChatBotUtil.chat(txt);
        log.info(result);
    }
}
