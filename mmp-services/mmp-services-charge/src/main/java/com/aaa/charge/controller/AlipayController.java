package com.aaa.charge.controller;

import com.aaa.charge.util.MyAlipayUtil;
import com.aaa.core.entity.CareOrderItem;
import com.aaa.core.vo.Result;
import com.alipay.api.AlipayApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("alipay")
public class AlipayController {

    @Autowired
    private MyAlipayUtil myAlipayUtil;

    @Autowired
    public void setMyAlipayUtil(MyAlipayUtil myAlipayUtil){
        this.myAlipayUtil=myAlipayUtil;
    }

    @PostMapping("pay/{itemId}/{itemName}/{allAmount}")
    public String payForIt(@RequestBody String itemId,@RequestBody String itemName,@RequestBody String allAmount, Model model) throws AlipayApiException {
        String pay=myAlipayUtil.pay(itemId,itemName,allAmount);
        model.addAttribute("form",pay);
        return "pay";
    }
}
