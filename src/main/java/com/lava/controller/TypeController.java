package com.lava.controller;

import com.lava.pojo.Headline;
import com.lava.pojo.PageInfo;
import com.lava.service.HeadlineService;
import com.lava.service.TypeService;
import com.lava.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("portal")
@CrossOrigin
public class TypeController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private HeadlineService headlineService;
    @GetMapping("findAllTypes")
    public Result findAllTypes(){
        Result result = typeService.findAllTypes();
        return result;
    }

    @PostMapping("findNewsPage")
    public Result findNewsPage(@RequestBody PageInfo pageInfo){
        Result result = headlineService.findNewsPage(pageInfo);
        return result;
    }
}
