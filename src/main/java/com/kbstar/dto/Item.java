package com.kbstar.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Item {
    private int id;
    private String name;
    private int price;
    private String imgname;
    private Date rdate;

    //jsp파일에서 입력한 name과 동일해야 한다
    private MultipartFile img;

    public Item(int id, String name, int price, String imgname, Date rdate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgname = imgname;
        this.rdate = rdate;
    }
}
