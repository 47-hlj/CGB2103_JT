package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SysResult {

    private Integer status; //定义状态码 200成功 201失败
    private String msg;     //服务器提示信息
    private Object data;    //服务器返回的数据

    //重载: 方法名相同,参数列表不同
    public static SysResult fail(){
        return new SysResult(201, "服务器处理失败", null);
    }

    public static SysResult success(){
        return new SysResult(200, "服务器处理成功", null);
    }

    public static SysResult success(Object data){
        return new SysResult(200, "服务器处理成功", data);
    }
    //重载的原则: 参数之间不要耦合 JVM中利用"就近原则"导致业务有误
   /* public static SysResult success(String msg){
        return new SysResult(200, msg,null);
    }*/
    public static SysResult success(String msg,Object data){
        return new SysResult(200, msg, data);
    }
}

