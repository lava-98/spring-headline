package com.lava.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lava.pojo.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lava.pojo.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author Lava
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2023-10-18 15:38:03
* @Entity com.lava.pojo.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {
    IPage<Map> selectMypage(IPage page, @Param("pageInfo") PageInfo pageInfo);

}




