package generator.domain;

import java.io.Serializable;

/**
 * 
 * @TableName user
 */
public class User implements Serializable {
    /**
     * 
     */
    private String id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 加密后的密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 盐
     */
    private String salt;

    /**
     * 注册时间/登录时间
     */
    private String time;

    /**
     * 上次登录ip地址
     */
    private String ip;

    /**
     * 乐观锁version
     */
    private Integer version;

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public String getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 加密后的密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 加密后的密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 盐
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 盐
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 注册时间/登录时间
     */
    public String getTime() {
        return time;
    }

    /**
     * 注册时间/登录时间
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 上次登录ip地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * 上次登录ip地址
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 乐观锁version
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 乐观锁version
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getNickName() == null ? other.getNickName() == null : this.getNickName().equals(other.getNickName()))
            && (this.getSalt() == null ? other.getSalt() == null : this.getSalt().equals(other.getSalt()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
            && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getNickName() == null) ? 0 : getNickName().hashCode());
        result = prime * result + ((getSalt() == null) ? 0 : getSalt().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userName=").append(userName);
        sb.append(", password=").append(password);
        sb.append(", nickName=").append(nickName);
        sb.append(", salt=").append(salt);
        sb.append(", time=").append(time);
        sb.append(", ip=").append(ip);
        sb.append(", version=").append(version);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}