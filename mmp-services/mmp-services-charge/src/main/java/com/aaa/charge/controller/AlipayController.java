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

@RestController
@RequestMapping("alipay")
public class AlipayController {

}
