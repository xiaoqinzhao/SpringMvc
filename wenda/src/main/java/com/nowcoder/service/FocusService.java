package com.nowcoder.service;

import com.nowcoder.dao.FocusDao;
import com.nowcoder.model.Focus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FocusService {
    @Autowired
    FocusDao focusDao;

    public int addFocus(Focus focus)
    {
        return focusDao.insertFocus(focus);
    }

    public int getFocusCount(int entityId,int entityType)
    {
        return focusDao.getFocusCount(entityId,entityType);
    }

    public int delFocus(Focus focus)
    {
        return focusDao.delFocus(focus);
    }

    public Focus getFocusByUser(int userId,int entityId,int entityType)
    {
        return focusDao.SelectByUser(userId,entityId,entityType);
    }
}
