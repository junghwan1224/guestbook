package kr.or.connect.guestbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class FileController {

    @GetMapping(path="/upload")
    public String uploadform() {
        return "uploadform";
    }

    @PostMapping(path = "/upload")
    public String upload(@RequestParam("file")MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        System.out.println(" ****** file getSize => " + file.getSize());

//        try (
//            FileOutputStream fos = new FileOutputStream("/tmp/" + file.getOriginalFilename());
//            InputStream is = file.getInputStream();
//        ) {
//            int readCount = 0;
//            byte[] buffer = new byte[1024];
//
//            while((readCount = is.read(buffer)) != -1) {
//                fos.write(buffer, 0, readCount);
//            }
//        }
        try {
            file.transferTo(new File("/tmp/" + file.getOriginalFilename()));
        }
        catch(Exception ex) {
            throw new RuntimeException("file Save Error");
        }

        return "uploadok";
    }

    @GetMapping(path = "/download")
    public void download(HttpServletResponse response) {
        // 직접 파일 정보를 변수에 저장해 놨지만, 이 부분이 db에서 읽어왔다고 가정한다.
        String fileName = "connect.png";
        String saveFileName = "/tmp/connect.png"; // 맥일 경우 "/tmp/connect.png" 로 수정
        String contentType = "image/png";
        int fileLength = 116303;

        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Type", contentType);
        response.setHeader("Content-Length", "" + fileLength);
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");

        try(
                FileInputStream fis = new FileInputStream(saveFileName);
                OutputStream out = response.getOutputStream();
        ){
            int readCount = 0;
            byte[] buffer = new byte[1024];
            while((readCount = fis.read(buffer)) != -1){
                out.write(buffer,0,readCount);
            }
        }catch(Exception ex){
            throw new RuntimeException("file Save Error");
        }
    }
}
