package com.liushaonetwork.blog.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.liushaonetwork.blog.app.DO.SysTopic;
import com.liushaonetwork.blog.app.mapper.SysTopicMapper;
import com.liushaonetwork.blog.app.service.SysTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 13496
 * SysTopicServiceImpl:
 * topic/tag/话题/主题 的Service
 */
@Service
public class SysTopicServiceImpl implements SysTopicService {
    private final SysTopicMapper sysTopicMapper;

    @Autowired
    public SysTopicServiceImpl(SysTopicMapper topicMapper) {
        this.sysTopicMapper = topicMapper;
    }

    @Override
    public int saveSysTopic(SysTopic topic) {
        return sysTopicMapper.insert(topic);
    }

    @Override
    public List<SysTopic> getSysTopics(int page) {
        PageHelper.startPage(page, 10);
        return sysTopicMapper.select(c -> c);
    }

    @Override
    public SysTopic getById(int id) {
        return sysTopicMapper.selectByPrimaryKey(id).orElseThrow(() -> new RuntimeException("找不到id为[" + id + "]的话题"));
    }

}
