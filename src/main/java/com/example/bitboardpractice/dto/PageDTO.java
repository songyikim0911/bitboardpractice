package com.example.bitboardpractice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder


public class PageDTO {

    @Builder.Default
    private int page =1;

    @Builder.Default
    private int size = 10;


    public int getSkip(){
        //1페이지일땐 -0
        //2페이지일땐 -10
        //3페이지일땐- 20
        return (this.page -1) * size;
    }


}
