package com.jt.service;

import com.jt.vo.ImageVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{

    @Value("${file.localDir}")
    private String localDir;    //获取磁盘路径
    @Value("${file.urlPath}")   //spel表达式
    private String urlPath;     //获取虚拟路径

    /**
     * 业务说明:
     *      1.是否为图片类型
     *      2.防止恶意程序  木马.exe.jpg
     *      3.分文件目录存储 hash date  yyyy/MM/dd
     *      4.重新设定文件名称UUID
     *      5.实现文件上传
     *      6.封装VO对象之后返回
     * @return
     */
    @Override
    public ImageVO upload(MultipartFile file) {
        //1.校验图片类型 jpg|png|gif  a.jpg A.JPG
        String fileName = file.getOriginalFilename();
        //将字符全部转化为小写之后校验
        fileName = fileName.toLowerCase();
        if(!fileName.matches("^.+\\.(jpg|png|gif)$")){
            return null;
        }

        //2.恶意程序校验  通过宽度/高度进行判断
        //将文件强制转化为图片
        try {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            if(width == 0 || height == 0){
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            //如果有错,则直接报错返回.
            return null;
        }

        //3.分目录存储 将当前时间格式化   /yyyy/MM/dd/
        String dateDir = new SimpleDateFormat("/yyyy/MM/dd/")
                .format(new Date());
        String fileDir = localDir + dateDir;
        File dirFile = new File(fileDir);
        if(!dirFile.exists()){
            dirFile.mkdirs();
        }

        //4.动态生成UUID   a.jpg
        String uuid = UUID.randomUUID().toString()
                .replace("-", "");
        //获取文件后缀
        int index = fileName.lastIndexOf('.');
        String fileType = fileName.substring(index);
        String realFileName = uuid + fileType;

        //5.实现文件上传
        String realFilePath = fileDir + realFileName;
        try {
            file.transferTo(new File(realFilePath));

            //6.封装VO对象
            ImageVO imageVO = new ImageVO();
            //存储文件磁盘地址:
            imageVO.setVirtualPath(realFilePath);
            //指定文件名称
            imageVO.setFileName(realFileName);
            //设定网络访问地址
            //https://img14.360buyimg.com/n1/s5463359/1/a70500aeed589028.jpg
            //http://image.jt.com/2021/11/11/uuid.jpg
            String url = urlPath + dateDir + realFileName;
            imageVO.setUrlPath(url);
            return imageVO;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

