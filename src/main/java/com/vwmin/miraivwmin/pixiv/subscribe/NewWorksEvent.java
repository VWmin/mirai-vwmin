package com.vwmin.miraivwmin.pixiv.subscribe;

import com.vwmin.miraivwmin.pixiv.ListIllustResponse;
import org.springframework.context.ApplicationEvent;

import java.util.List;

/**
 * @author vwmin
 * @version 1.0
 * @date 2020/4/17 0:03
 */
public class NewWorksEvent extends ApplicationEvent {

    private final Long userId;
    private final List<ListIllustResponse.IllustsBean> illusts;


    public NewWorksEvent(Object source, Long userId, List<ListIllustResponse.IllustsBean> illusts) {
        super(source);
        this.userId = userId;
        this.illusts = illusts;
    }

    public List<ListIllustResponse.IllustsBean> getIllusts() {
        return illusts;
    }

    public Long getUserId() {
        return userId;
    }


}
