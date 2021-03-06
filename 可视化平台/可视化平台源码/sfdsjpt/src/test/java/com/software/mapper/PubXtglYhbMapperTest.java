package com.software.mapper;

import com.software.service.LhjfService;
import com.software.datasource.DataSourceRouter;
import com.software.model.KvModel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PubXtglYhbMapperTest {
    @Autowired
    PubXtglYhbMapper yhb;
    @Autowired
    AjMapper ajMapper;
    @Autowired
    LhjfService lhjfService;
    @Test
    public void selectByPrimaryKey() {
        DataSourceRouter.routerTo("120000 200");
        /*PubXtglXxxgl p= pum.selectByPrimaryKey(3);
        System.out.println(p.getSjxx());*/
    }
}