package com.sicnu.wzh.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sicnu.wzh.Entity.Chat;

import java.util.List;

public interface ChatService extends IService<Chat> {

    public int getUnreadMessageNumber(String userId);

    public boolean notifyFrontend(String targetUserId, Chat chat);

    public List<Chat> getMessages(String userId,String friendId);

    public boolean sendMessage(String fromUserId,
                               String targetUserId,
                               int contentType,
                               String content);
}
