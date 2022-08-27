package cn.root.common;

public enum CommonString {

    LIST_ID_TOKEN("list_id_token"),
    LIST_IP_TOKEN("list_ip_token"),

    ACCOUNT_IN_RISK_IP("账号ip地址有变化"),

    LIST_ACTIVITIES("list_activity"),
    LIST_ACTIVITY_BODY("list_activity_body"),
    LIST_USER("list_user"),
    LIST_USER_ACTIVITY("list_user_activity"),


    LOCK_VIEW_COUNT("lock_view_count"),
    ;

    private String name;

    CommonString(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
