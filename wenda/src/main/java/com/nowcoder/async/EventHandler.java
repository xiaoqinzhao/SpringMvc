package com.nowcoder.async;

import java.util.List;

/**
 * Created by wenda on 2018/3/21.
 */
public interface EventHandler {
    void doHandle(EventModel model);

    List<EventType> getSupportEventTypes();
}
