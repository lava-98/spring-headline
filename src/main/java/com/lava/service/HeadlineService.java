package com.lava.service;

import com.lava.pojo.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lava.pojo.PageInfo;
import com.lava.utils.Result;

/**
* @author Lava
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2023-10-18 15:38:03
*/
public interface HeadlineService extends IService<Headline> {

    Result findNewsPage(PageInfo pageInfo);
}
