package cn.root.util;

import cn.root.bean.entity.Activity;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ActivityFilter {
    public static List<Activity> filter(List<Activity> activities){
        return activities.stream()
                .filter((activity) -> activity.getExpireTime().compareTo(new Date()) >= 0)
                .collect(Collectors.toList());
    }
}
