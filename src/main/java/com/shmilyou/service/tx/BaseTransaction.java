package com.shmilyou.service.tx;
/* Created with 岂止是一丝涟漪     530060499@qq.com    2018/11/17 */

import com.shmilyou.entity.User;
import com.shmilyou.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class BaseTransaction {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void insertTx() {
        User user=new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("高山流水");
        int row = userRepository.insert(user);
        if(row>0){
            System.out.println("添加成功！");
        }
        row=1/0;
        System.out.println("insertTx() is end");
    }
}
