package cn.root.bean.entity;

import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AMain {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i++){
            User user = new User();
            user.setVersion(1);
            user.setId(String.valueOf(i));
            users.add(user);
        }

        User user = new User();
        user.setVersion(0);
        users.add(user);
        for (User user1 : users) {
            System.out.println(user1);
        }
        System.out.println("-------------");
        users = users.stream()
                .filter(a -> a.getVersion() == 1)
                .collect(Collectors.toList());
        for (User user1 : users) {
            System.out.println(user1);
        }
    }
}
