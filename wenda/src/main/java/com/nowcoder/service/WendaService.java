package com.nowcoder.service;

import org.springframework.stereotype.Service;

/**
 *  * Created by wenda on 2018/2/2.
 */
@Service
public class WendaService {
    public String getMessage(int userId) {
        return "Hello Message:" + String.valueOf(userId);
    }
}
