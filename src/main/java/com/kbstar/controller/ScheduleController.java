package com.kbstar.controller;

import com.kbstar.dto.Cart;
import com.kbstar.dto.MsgAdm;
import com.kbstar.dto.Sales;
import com.kbstar.service.CartService;
import com.kbstar.service.SalesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Slf4j
@Component
public class ScheduleController {

    @Autowired
    SalesService salesService;

    @Autowired
    CartService cartService;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;


    @Scheduled(cron = "*/15 * * * * *")
    public void cronJobDailyUpdate() {

        //log.info("----------- Scheduler Start ------------");
        Random r = new Random();
        int content1 = r.nextInt(100)+1;
        int content2 = r.nextInt(1000)+1;
        int content3 = r.nextInt(500)+1;
        int content4 = r.nextInt(10)+1;

        MsgAdm msg = new MsgAdm();
        msg.setContent1(content1);
        msg.setContent2(content2);
        msg.setContent3(content3);
        msg.setContent4(content4);

        //send는 채팅할 때 사용했으니 senadm 이라고 나가는 통로를 별도로 만듬
        //15초에 한번씩 집계를 해서 msg를 만들고 sendadm으로 msg를 보낸다
        messagingTemplate.convertAndSend("/sendadm", msg);
    }


    @Scheduled(cron = "*/8 * * * * *")
    public void cronJobWeeklyUpdate() throws Exception {

        List<Cart>list = cartService.get();
        int len = list.size();
        int total=0;
        for(Cart obj: list){
            total= total+obj.getCnt()*obj.getItem_price();
        }
        log.info(total+"");
    }
}