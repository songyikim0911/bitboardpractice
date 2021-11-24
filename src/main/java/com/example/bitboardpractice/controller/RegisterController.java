package com.example.bitboardpractice.controller;

import com.example.bitboardpractice.dto.BoardDTO;
import com.example.bitboardpractice.service.BoardService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet(name="RegisterController", value="/board/register")
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/board/register.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BoardDTO boardDTO = BoardDTO.builder()
                            .title(request.getParameter("title"))
                            .content(request.getParameter("content"))
                            .writer(request.getParameter("writer"))
                            .build();


        Integer bno = BoardService.INSTANCE.register(boardDTO);

        response.sendRedirect("/board/list?bno="+bno);

    }
}
