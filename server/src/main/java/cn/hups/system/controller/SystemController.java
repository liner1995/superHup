package cn.hups.system.controller;

import cn.hups.common.exception.GlobalException;
import cn.hups.common.po.Auth;
import cn.hups.common.utils.AjaxJson;
import cn.hups.common.utils.AuthUtil;
import cn.hups.rbac.constract.RbacConstract;
import cn.hups.rbac.po.SmRolePo;
import cn.hups.rbac.po.SmUserPo;
import cn.hups.rbac.po.VueRouterPo;
import cn.hups.rbac.service.IRbacService;
import cn.hups.system.constract.SysConstract;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/systemController")
public class SystemController {

    @Resource
    private IRbacService iRbacService;

    /**
     * 用户登陆
     * @return
     * @throws GlobalException
     */
    @RequestMapping("/login")
    public AjaxJson login(@RequestBody SmUserPo smUserPo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        if (smUserPo == null) {
            throw new GlobalException(SysConstract.NOTFOUND_LOGINUSER);
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(smUserPo.getUserName(), smUserPo.getUserPassword());

        Auth auth = null;
        try {
            subject.login(token);
            auth = AuthUtil.getAuth();
            auth.setToken(subject.getSession().getId().toString());
        } catch (IncorrectCredentialsException e) {
            throw new GlobalException(SysConstract.ERROR_PASSWORD);
        } catch (LockedAccountException e) {
            throw new GlobalException(SysConstract.LOCAL_USER);
        } catch (AuthenticationException e) {
            throw new GlobalException(SysConstract.HAVENO_USER);
        } catch (GlobalException e) {
            throw new GlobalException(e.getMessage());
        } catch (Exception e) {
            throw new GlobalException(SysConstract.UNKNOW_ERROR + e.getMessage());
        }

        ajaxJson.setObj(auth);
        return ajaxJson;
    }

    /**
     * 用户登出
     * @return
     * @throws GlobalException
     */
    @RequestMapping("/logout")
    public AjaxJson logout(String token) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        return ajaxJson;
    }

    /**
     * 获取用户信息
     * @return
     * @throws GlobalException
     */
    @RequestMapping("/getInfo")
    public AjaxJson getInfo(String token) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        if (StringUtils.isEmpty(token)) {
            throw new GlobalException(SysConstract.UNLEGAL_TOKEN);
        }

        Auth auth = AuthUtil.getAuth();
        if (auth != null && auth.getUserid() != null) {
            Map<String, Object> resultMap = iRbacService.selectPermByUserid(auth.getUserid());
            if (resultMap != null && resultMap.size() > 0) {
                auth.setRoles( (List<SmRolePo>) resultMap.get(RbacConstract.USER_ROLE_KEY));
                auth.setPerms( (List<VueRouterPo>) resultMap.get(RbacConstract.USER_PERM_KEY));
            }
        }
        ajaxJson.setObj(auth);

        return ajaxJson;
    }
}
