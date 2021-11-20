package com.example.bitboardpractice.service;

import com.example.bitboardpractice.dao.BoardDAO;
import com.example.bitboardpractice.dto.BoardDTO;
import com.example.bitboardpractice.dto.PageDTO;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public enum BoardService {

    INSTATNCE;


    public Integer register(BoardDTO boardDTO) throws RuntimeException{

        return BoardDAO.INSTANCE.insert(boardDTO);

    }


    public List<BoardDTO> getList(PageDTO pageDTO) throws RuntimeException{

        log.info("BoardService getList....");

        log.info(pageDTO);

        return BoardDAO.INSTANCE.list(pageDTO);

    }

    public BoardDTO read(Integer bno) throws RuntimeException{

        log.info("BoardService read..........."+bno);

        return BoardDAO.INSTANCE.select(bno);


    }

    public void remove(Integer bno) throws RuntimeException{

        BoardDAO.INSTANCE.delete(bno);

    }

    public void modify(BoardDTO dto) throws RuntimeException{

        BoardDAO.INSTANCE.update(dto);

    }

}
