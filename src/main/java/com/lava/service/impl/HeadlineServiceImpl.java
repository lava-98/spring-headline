package com.lava.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lava.pojo.Headline;
import com.lava.pojo.PageInfo;
import com.lava.service.HeadlineService;
import com.lava.mapper.HeadlineMapper;
import com.lava.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author Lava
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2023-10-18 15:38:03
*/
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
    implements HeadlineService{

    @Autowired
    private HeadlineMapper headlineMapper;

    @Override
    public Result findNewsPage(PageInfo pageInfo) {

        IPage<Headline> page = new Page<>(pageInfo.getPageNum(),pageInfo.getPageSize());
        headlineMapper.selectMypage(page, pageInfo);
        Map<String,Object> info =new HashMap<>();
        info.put("pageData",page.getRecords());
        info.put("pageNum",page.getCurrent());
        info.put("pageSize",page.getSize());
        info.put("totalPage",page.getPages());
        info.put("totalSize",page.getTotal());
        Map<String,Object> pageInfoMap=new HashMap<>();
        pageInfoMap.put("pageInfo",info);
        return Result.ok(pageInfoMap);
    }
}




