package com.riding.springboot.controller.utils;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Api(tags = "6. File Upload API")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class FileUploadController {

    @Value("${temp.path}")
    private String tempPath;

    @PostMapping("/upload")
    public String upload(@RequestParam("files") MultipartFile[] files) throws Exception {

        log.info("### upload");

        for(MultipartFile file : files) {
            File temp = new File(tempPath + UUID.randomUUID().toString());
            try {
                FileUtils.copyInputStreamToFile(file.getInputStream(), temp);
            } catch (IOException e){
                log.error("Error while copying.", e);
                throw e;
            }
        }
        return "success";
    }


}
