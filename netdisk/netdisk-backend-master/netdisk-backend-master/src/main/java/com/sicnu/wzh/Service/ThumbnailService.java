package com.sicnu.wzh.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sicnu.wzh.Entity.ThumbnailEntity;

import javax.servlet.http.HttpServletResponse;

public interface ThumbnailService extends IService<ThumbnailEntity> {

    public void downloadThumbnail(String picId, HttpServletResponse response);
    public void downloadThumbnailForAdmin(String picId, HttpServletResponse response);
}
