package org.zerock.bitboard.controller;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Log4j2
@WebServlet(name = "download", value = "/down")
public class DownloadController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("Download... doGet....");


        String path = "C:\\upload";//파일경로
        String fileName = request.getParameter("fname"); //request.getParameter("fname");으로 가지고와야하는..

        File targetFile = new File(path, fileName);//파일준비
        OutputStream out = null;

        try{
            out = response.getOutputStream();//파일보낼outputstream전달!
            Path targetPath = targetFile.toPath();//패스를 따로 빼놓기.(자주 등장하니까)
            //파일복사! 파일복사를 위한 빨대 필요.

            //mime type
            String contentType = Files.probeContentType(targetPath);

            log.info(contentType);//내용확인예정

            response.setContentType(contentType);
            Files.copy(targetPath, out);

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                out.close();   //빨대 썼으니 닫아주기.
            }catch(Exception e){}
        }
    }





}
