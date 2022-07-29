package com.yantailor.turing_web_re;


import com.sun.org.apache.bcel.internal.generic.NEW;
import com.yantailor.turing_web_re.entity.Inform;
import com.yantailor.turing_web_re.service.AwardService;
import com.yantailor.turing_web_re.utils.RedisUtils;
import com.yantailor.turing_web_re.utils.TransferUtil;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.codec.Hex;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootTest
class TuringWebReApplicationTests {
public final Logger logger = LoggerFactory.getLogger(TuringWebReApplication.class);
@Autowired
    TransferUtil transferUtil;
@Autowired
    AwardService awardService;
    @Test
    void contextLoads() throws IOException {
//        LinkedList<Member> objects = new LinkedList<>();
//        Member member = new Member();
//        member.setMemberName("徐达梦");
//        objects.add(member);
//        AwardDto awardDto = new AwardDto("某比赛啊", new Date(), objects, "作品名称");
//        awardService.addAward(awardDto,null);
//        logger.info("sout");
        Inform inform = new Inform();
        System.out.println(inform.getInformEditTime());
    }

    @Test
    void taskTest() throws Exception {
        ThreadPoolTaskScheduler TaskScheduler = new ThreadPoolTaskScheduler();
        TaskScheduler.initialize();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = simpleDateFormat.parse("2022-3-18 23:53");
        TaskScheduler.schedule(() -> System.out.println("taskRunning"), date);
        System.out.println("setTime->"+date);
        System.out.println("nowTime->"+simpleDateFormat.format(new Date()));
        Date date1 = simpleDateFormat.parse("2022-3-19 00:08");
        ScheduledFuture<?> taskFinishRATE = TaskScheduler.scheduleAtFixedRate(() -> System.out.println("taskFinishRATE"), 10000);
        TaskScheduler.scheduleWithFixedDelay(()-> System.out.println("taskFinishDELAY"),10000);
        System.out.println("delayTime->"+ (date1.getTime() - new Date().getTime()));
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Autowired
    RedisUtils redisUtils;
    @Test
    void testRedis(){
        redisUtils.set("test", "value");
    }

    @Test
     void testHex(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String abc = bCryptPasswordEncoder.encode("turing");
        System.out.println(abc);

        System.out.println(bCryptPasswordEncoder.matches("turing", "$2a$10$5Ua43dwS/dv9Tru7qu9YAuxxFytRTHv7lPdI2X7zCZOkQPN/.ofU2"));


    }



}
