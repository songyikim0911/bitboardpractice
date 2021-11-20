package com.example.bitboardpractice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class BoardDTO {

    private Integer bno;
    private String title, content, writer;
    private int viewcount;
    private Timestamp regdate, updatedate;

    private List<AttachDTO> attachDTOList;

    public void addAttach(AttachDTO attachDTO){
        if(attachDTOList == null){
            attachDTOList = new ArrayList<>();

        }
        attachDTOList.add(attachDTO);
    }

}
