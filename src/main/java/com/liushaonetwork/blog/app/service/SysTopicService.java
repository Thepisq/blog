package com.liushaonetwork.blog.app.service;

import com.liushaonetwork.blog.app.DO.SysTopic;

import java.util.List;

/**
 * @author 13496
 */
public interface SysTopicService {
    int saveSysTopic(SysTopic topic);

    List<SysTopic> getSysTopics(int page);

    SysTopic getById(int id);
}
