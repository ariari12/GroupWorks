package kr.co.groupworks.calendar.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
@Slf4j
@Controller
@RequestMapping("/calendar")
public class FileDownloadController {
    @Value("${file.upload-dir}")
    private String uploadDirectory;

    @GetMapping("/fileDownload/{filename}")
    public ResponseEntity<Resource> download(@PathVariable String filename) throws UnsupportedEncodingException {
        log.info("FileDownloadController - download");
        log.info("filename: {}", filename);
        File file = new File(uploadDirectory + File.separator + filename);
        // 파일이 존재하지 않는다면
        if(!file.exists()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        // 여기까지 오면 파일이 존재한다.
        Resource resource = new FileSystemResource(file);
        // 파일의 인코딩
        String encodingFileName =
                URLEncoder.encode(filename,"UTF-8").replaceAll("\\+","%20");

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\""+ encodingFileName+"\"");
        headers.add(HttpHeaders.CONTENT_LENGTH,String.valueOf(file.length()));
        System.out.println("resource = " + resource);
        return ResponseEntity.ok().headers(headers).body(resource);
    }
}
