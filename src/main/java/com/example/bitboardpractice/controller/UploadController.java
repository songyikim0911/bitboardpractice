package com.example.bitboardpractice.controller;

import lombok.extern.log4j.Log4j2;

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
            log.info("partName"+part.getName());
            return;
        }
        String fileName = part.getSubmittedFileName();

        String uploadFileName = System.currentTimeMillis()+"_"+fileName;
        log.info(fileName);

          try(InputStream in = part.getInputStream();
             OutputStream fos = new FileOutputStream(uploadFolder+ File.separator+ uploadFileName);
        ){
              while(true){
                  int count = in.read(buffer);
              }
          }


    })

    }
}
