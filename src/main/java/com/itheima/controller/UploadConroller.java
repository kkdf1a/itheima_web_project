package com.itheima.controller;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@RestController
public class UploadConroller {
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info ( "文件上传:{}",image.getOriginalFilename () );
        String url = "https://ecmb.bdimg.com/tam-ogel/1815134652_-1760084283_1200_1200.png";
        log.info ( url );
        return Result.success (url);
    }

}
