package cn.hups.common.po;


import cn.hups.rbac.po.SmRolePo;
import cn.hups.rbac.po.VueRouterPo;

import java.io.Serializable;
import java.util.List;

/**
 * 用于权限及相关用户设置
 */
public class Auth implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userid;
    private String userName;
    private Integer pkCorp;
    private String corpName;
    private String token;
    private List<SmRolePo> roles;
    private List<VueRouterPo> perms;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getPkCorp() {
        return pkCorp;
    }

    public void setPkCorp(Integer pkCorp) {
        this.pkCorp = pkCorp;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<SmRolePo> getRoles() {
        return roles;
    }

    public void setRoles(List<SmRolePo> roles) {
        this.roles = roles;
    }

    public List<VueRouterPo> getPerms() {
        return perms;
    }

    public void setPerms(List<VueRouterPo> perms) {
        this.perms = perms;
    }
}
