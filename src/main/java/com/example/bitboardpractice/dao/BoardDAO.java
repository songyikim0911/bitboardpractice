package com.example.bitboardpractice.dao;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

@Log4j2
public enum BoardDAO {

    INSTANCE;

    private static final String PREFIX = "org.zerock.bitboard.dao.BoardMapper";

    public Integer insert(BoardDTO boardDTO) throws RuntimeException{
        //이제 bno를 반환할 수 있도록 리턴값을 integer로 변경
        Integer bno = null;

        //값 딸때는 앞에다가 먼저 선언해주고, 아래에 리턴해주는게좋다.
        try(SqlSession session = MyBatisLoader.INSTANCE.getFactory().openSession(true)){
            session.insert(PREFIX+".insert", boardDTO);
            bno = boardDTO.getBno();//bno값 받아오기

            List<AttachDTO> attachDTOList = boardDTO.getAttachDTOList();

            if(attachDTOList != null && attachDTOList.size() > 0) {
                for (AttachDTO attachDTO : attachDTOList) {
                    attachDTO.setBno(bno);
                    session.insert(PREFIX + ".insertAttach", attachDTO);
                }
            }
            session.commit();
        }catch(Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return bno;//bno반환!bno를 활용 가능.
    }


    public BoardDTO select(Integer bno) throws RuntimeException{

        BoardDTO dto = null;
        try(SqlSession session = MyBatisLoader.INSTANCE.getFactory().openSession(true)){
            dto = session.selectOne(PREFIX+".select", bno);
        }catch(Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return dto;
    }

    public List<BoardDTO> list(PageDTO pageDTO) throws RuntimeException{

        List<BoardDTO> list = null;

        try(SqlSession session = MyBatisLoader.INSTANCE.getFactory().openSession(true)){
            list = session.selectList(PREFIX+".list", pageDTO);
        }catch(Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return list;

    }

    public void update(BoardDTO dto) throws RuntimeException{

        try(SqlSession session = MyBatisLoader.INSTANCE.getFactory().openSession(true)){
            session.update(PREFIX+".update", dto);
        }catch(Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

    }


    public void delete(Integer bno) throws RuntimeException{
        try(SqlSession session = MyBatisLoader.INSTANCE.getFactory().openSession(true)){
            session.delete(PREFIX+".delete", bno);
        }catch(Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

    }




}
