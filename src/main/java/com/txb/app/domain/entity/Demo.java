//package com.txb.app.domain.entity;
//
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.annotation.TableField;
//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.annotation.TableName;
//import lombok.ToString;
//import org.springframework.stereotype.Service;
//
//import java.io.Serializable;
//import java.util.Date;
//
//@ToString
//@TableName("DEMO")
//public class Demo implements Serializable {
//    @TableId(value = "C_ID",type = IdType.INPUT)
//    private String id;
//    @TableField("C_NAME")
//    private String cName;
//    @TableField("C_AGE")
//    private String cAge;
//    @TableField("T_CRT_TM")
//    private Date tCrtTm;
//    @TableField("T_UPD_TM")
//    private Date tUpdTm;
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getcName() {
//        return cName;
//    }
//
//    public void setcName(String cName) {
//        this.cName = cName;
//    }
//
//    public String getcAge() {
//        return cAge;
//    }
//
//    public void setcAge(String cAge) {
//        this.cAge = cAge;
//    }
//
//    public Date gettCrtTm() {
//        return tCrtTm;
//    }
//
//    public void settCrtTm(Date tCrtTm) {
//        this.tCrtTm = tCrtTm;
//    }
//
//    public Date gettUpdTm() {
//        return tUpdTm;
//    }
//
//    public void settUpdTm(Date tUpdTm) {
//        this.tUpdTm = tUpdTm;
//    }
//}
