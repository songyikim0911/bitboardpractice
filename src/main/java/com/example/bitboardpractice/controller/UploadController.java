package com.example.bitboardpractice.controller;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.Collection;

@Log4j2
@WebServlet(name = "UploadController", value = "/upload")
@MultipartConfig(fileSizeThreshold = 1024*1024)
public class UploadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("WEB-INF/upload.jsp").forward(request,response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uploadFolder = "C:\\upload";
        byte[] buffer = new byte[1024*8];


        Collection<Part> parts = request.getParts();

        parts.forEach(part -> {

            String type = part.getContentType();
            if(type == null){
                //null 파일이 아니라는 것.
                log.info("partName:" + part.getName());
                return;
            }

            String fileName = part.getSubmittedFileName();

            String uploadFileName = System.currentTimeMillis()+"_"+fileName;
            log.info(fileName);

            //원본 파일 저장
        try (InputStream in = part.getInputStream();
             OutputStream fos = new FileOutputStream(uploadFolder+ File.separator+ uploadFileName);
             //중복 파일 구분을 위한 코드 추가.

        ) {
            while(true){
                int count = in.read(buffer);
                if(count == -1){ break; }
                fos.write(buffer, 0, count);
            }
        }catch(Exception e){

        }//원본파일저장 끝

        //이미지에 대해서만 섬네일
        if(type.startsWith("image")){

            try {
                Thumbnails.of(new File(uploadFolder+ File.separator+ uploadFileName))
                        .size(100, 100)
                        .toFile(new File(uploadFolder+ File.separator+ "s_"+uploadFileName));
            }catch(Exception e){

            }
        }


        log.info("---------------------------");

    });//for each


    }
}
