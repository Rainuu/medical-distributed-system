package com.aaa.system.controller.api;

import com.aaa.core.vo.Result;
import com.aaa.system.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("system/api/oss")
public class OssController {
    @Autowired
    private OssService service;
@PostMapping("upload")
    public Result upload( MultipartFile file){
        String s = service.uploadFile(file);
        return new Result(200,null,s);
    }
}
