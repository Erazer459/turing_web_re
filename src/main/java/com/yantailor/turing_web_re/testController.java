package com.yantailor.turing_web_re;

import com.yantailor.turing_web_re.service.AwardService;
import com.yantailor.turing_web_re.utils.TransferUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by yantailor
 * on 2022/2/6 10:33 @Version 1.0
 */
@RestController
public class testController {

    @Autowired
    TransferUtil transferUtil;

    @Autowired
    AwardService awardService;
    @PostMapping("/testTransfer")
    public void test(@RequestPart(name = "test") MultipartFile[] multipartFile) throws IOException {
//        transferUtil.TransferFiles(multipartFile, "xxx");
//        LinkedList<Member> objects = new LinkedList<>();
//        Member member = new Member();
//        member.setMemberName("徐达梦");
//        objects.add(member);
//        AwardDto awardDto = new AwardDto("某比赛啊", new Date(), objects, "作品名称");
//        awardService.addAward(awardDto,null);
    }
}
