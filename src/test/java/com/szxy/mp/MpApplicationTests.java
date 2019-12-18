package com.szxy.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.szxy.mp.bean.User;
import com.szxy.mp.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest

public class MpApplicationTests {

    @Test
    public void contextLoads() {
    }


    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList() {
        System.out.println(("----- selectAll method test ------"));
        //UserMapper 中的 selectList() 方法的参数为 MP 内置的条件封装器 Wrapper
        //所以不填写就是无任何条件
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void testInsert(){

        User user = new User();
        user.setName("Hww");
        user.setAge(1222);
        user.setEmail("552232@qq.com");

        int result = userMapper.insert(user);
        System.out.println(result); //影响的行数
        System.out.println(user); //id自动回填
    }
    @Test
    public  void testUpdate(){
        User user = new User();
        user.setId(1207223727805202434L);
        user.setName("ggwwwy");
        userMapper.updateById(user);
    }

    @Test
    public void tUpdate(){
        User user = userMapper.selectById(1207242814753193986L);
        user.setName("HssHHHH");
        int i = userMapper.updateById(user);
        System.out.println(i);

    }

    @Test
    public void getUserById(){
        User user = userMapper.selectById(5L);
        System.out.println(user);

    }
    @Test//批量id查询用户
    public void getUserByBanchIds(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3, 4));
//        for (User user : users) {
//            System.out.println(user);
//        }
        users.forEach(System.out::println);

    }

    @Test
    public void selectByMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("name", "Helen");
        map.put("age", 18);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectPage(){
        Page<User> page = new Page<>(1,3);
        userMapper.selectPage(page,null);
        page.getRecords().forEach(System.out::println);
        System.out.println(page.getCurrent());
        System.out.println(page.getPages());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.hasNext());
        System.out.println(page.hasPrevious());

    }
    @Test
    public void testSelMaps(){
        Page<User> page = new Page<>(1,3);
        IPage<Map<String, Object>> mapIPage = userMapper.selectMapsPage(page, null);
        mapIPage.getRecords().forEach(System.out::println);
        System.out.println(page.getCurrent());
        System.out.println(page.getPages());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.hasNext());
        System.out.println(page.hasPrevious());

    }
    @Test
    public void deUserById(){
        int i = userMapper.deleteById(3L);
        System.out.println(i);

    }
    @Test
    public void deUserByIds(){
        int i = userMapper.deleteBatchIds(Arrays.asList(2, 4));
        System.out.println(i);
    }
    @Test
    public void deByMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","ggy");
        map.put("age",18);
        userMapper.deleteByMap(map);

    }

    @Test
    public void tSelectBage(){
        QueryWrapper<User>  wrapper = new QueryWrapper<>();
    //    wrapper.ge("age", 20);
     //   wrapper.eq("age",24);
       // wrapper.between("age",20,40);
     //   wrapper.like("name","l");
      //  wrapper.orderByAsc("age");
        wrapper.select("name","age");
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }


}
