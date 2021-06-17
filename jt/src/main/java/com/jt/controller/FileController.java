package com.jt.controller;

import com.jt.vo.SysResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileController {

    /**
     * 文件上传入门案例
     * URL: /file/upload
     * 参数名:  file  二进制字节信息
     * 返回值: SysResult(imageVO)
     * 步骤:
     *      1.获取文件名称
     *      2.指定具体上传路径
     *      3.拼接文件的全路径
     *      4.实现上传
     */
    @PostMapping("/upload")
    public SysResult upload(MultipartFile file) throws IOException {
        //1.获取文件名称
        String fileName = file.getOriginalFilename();
        //2.定义上传路径  绝对路径  注意/问题
        String fileDir = "D:/CGB2103_JT/image";
        File dirFile = new File(fileDir);
        if(!dirFile.exists()){
            //如果文件不存在,则应该创建一个新的目录
            dirFile.mkdirs(); //多级目录创建
        }
        //3.指定文件上传的全路径 目录/文件名称
        //D:/CGB2103_JT/image/abc.jpg  注意/问题
        String filePath = fileDir + "/" + fileName;
        //4.实现文件上传
        file.transferTo(new File(filePath));
        return SysResult.success();
    }
}

