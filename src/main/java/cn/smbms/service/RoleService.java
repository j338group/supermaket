package cn.smbms.service;

import cn.smbms.pojo.Role;

import java.util.List;

/**
 * description:
 * Created by Ray on 2019-09-24
 */

/*controlller层  -service层  -dao层
* Service层是建立在DAO层之上的，建立了DAO层后才可以建立Service层，而Service层又是在Controller层之下的，
* 因而Service层应该既调用DAO层的接口，又要提供接口给Controller层的类来进行调用，它刚好处于一个中间层的位置。
* 每个模型都有一个Service接口，每个接口分别封装各自的业务处理方法。
* */

//查询用户Role的列表。再写一个实现类
public interface RoleService {
    List<Role> queryRoleList();
}
