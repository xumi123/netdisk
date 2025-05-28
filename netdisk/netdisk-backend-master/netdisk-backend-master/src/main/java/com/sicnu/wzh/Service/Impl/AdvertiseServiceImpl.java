package com.sicnu.wzh.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sicnu.wzh.Entity.Advertisement;
import com.sicnu.wzh.Entity.DTO.User2FileDTO;
import com.sicnu.wzh.Entity.FileEntity;
import com.sicnu.wzh.Entity.ThumbnailEntity;
import com.sicnu.wzh.Mapper.AdvertiseMapper;
import com.sicnu.wzh.Service.AdvertiseService;
import com.sicnu.wzh.Service.ThumbnailService;
import com.sicnu.wzh.Util.FileUtil;
import net.coobird.thumbnailator.Thumbnails;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.UUID;

import static com.sicnu.wzh.Constant.FileConstant.UPLOAD_PATH;
import static com.sicnu.wzh.Constant.MinetypeConstant.MINETYPE_IMAGE;
import static com.sicnu.wzh.Constant.MinetypeConstant.MINETYPE_VIDEO;
import static com.sicnu.wzh.Constant.NSFW.NSFW_BAN;
import static com.sicnu.wzh.Constant.NSFW.NSFW_IMG_PATH;

@Service
public class AdvertiseServiceImpl extends ServiceImpl<AdvertiseMapper, Advertisement> implements AdvertiseService {


    @Override
    public boolean addAdvertisement(String name, String description, MultipartFile file){
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-","");
        Advertisement advertisement = new Advertisement();
        advertisement.setAdvertisementId(uuid);
        advertisement.setAdvertisementName(name);
        advertisement.setAdvertisementSize(file.getSize());
        advertisement.setAdvertisementDescription(description);
        FileUtil.saveMultipartFile(file,UPLOAD_PATH + "advertise/",uuid);
        advertisement.setAdvertisementLocation(UPLOAD_PATH + "advertise/" + uuid  + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
        return save(advertisement);
    }

    @Override
    public void chunkDownload(String id , HttpServletResponse response, HttpServletRequest request){
        Advertisement advertisement = getById(id);
        FileUtil.chunkDownload(advertisement.getAdvertisementName(),
                advertisement.getAdvertisementLocation(),
                advertisement.getAdvertisementSize(),"video/mp4",response,request);
    }

    /**
     * 下载缩略图
     * @param
     * @param response
     */
    @Override
    public void downloadThumbnail(String advertisementId, HttpServletResponse response){
        Advertisement advertisement = getById(advertisementId);
        String thumbnailLocation = advertisement.getAdvertisementThumbnail();
        /**
         * 如果缩略图不存在，先生成再发送
         */
        if (thumbnailLocation == null || "".equals(thumbnailLocation)) {
            generateThumbnailForVideo(advertisement, advertisement.getAdvertisementId(), response);
            return;
        }
        /**
         * 如果缩略图存在，直接向前端发送
         */
        FileEntity downloadFile = new FileEntity();
        downloadFile.setFileName(advertisement.getAdvertisementId()+".png");
        downloadFile.setFileLocation(advertisement.getAdvertisementThumbnail());
        FileUtil.download(response,downloadFile);
    }

    public void generateThumbnailForVideo(Advertisement advertisement, String uuid, HttpServletResponse response) {
        //最后获取到的视频的图片的路径
        String videPicture="";
        //Frame对象
        Frame frame = null;
        //标识
        int flag = 0;
        try {
             /*
            获取视频文件
            */
            // FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber(videoPath + "/" + videoFileName);
            FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber(advertisement.getAdvertisementLocation());
            fFmpegFrameGrabber.start();

            //获取视频总帧数
            int ftp = fFmpegFrameGrabber.getLengthInFrames();
            System.out.println("时长 " + ftp / fFmpegFrameGrabber.getFrameRate() / 60);


            while (flag <= ftp) {
                frame = fFmpegFrameGrabber.grabImage();

                /*
                对视频的第五帧进行处理
                 */
                if (frame != null && flag==5) {

//                    //文件转换
                    BufferedImage bufferedImage = FrameToBufferedImage(frame);
                    try {
                        System.out.println("生成中");
                        Thumbnails.of(bufferedImage)
                                .size(200,200)
                                .toFile(new File(getThumbnailPath(uuid)));
                        System.out.println("生成完成");
                        FileEntity downloadFile = new FileEntity();
                        downloadFile.setFileName(uuid+".png");
                        downloadFile.setFileLocation(getThumbnailPath(uuid));
                        System.out.println("发送");
                        FileUtil.download(response,downloadFile);
                        return;
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
                flag++;
            }
            fFmpegFrameGrabber.stop();
            fFmpegFrameGrabber.close();
        } catch (Exception E) {
            E.printStackTrace();
        }
        return ;
    }

    public BufferedImage FrameToBufferedImage(Frame frame) {
        //创建BufferedImage对象
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage bufferedImage = converter.getBufferedImage(frame);
        return bufferedImage;
    }

    private String getThumbnailPath(String uuid){
        return UPLOAD_PATH + "advertise/thumbnail/" + uuid + ".png";
    }
}
