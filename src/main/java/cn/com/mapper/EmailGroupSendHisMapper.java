package cn.com.mapper;

import cn.com.pojo.EmailGroupSendHis;
import cn.com.vo.EmailGroupHisVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Title: cn.com.mapper-EmailGroupSendHis
 * @Description:StudentManager 系统API接口开发Demo，重点关注业务逻辑部分
 * @Author: yzh
 * @Date 2023/11/7 11:22
 */
public interface EmailGroupSendHisMapper {

    void addEmailGroupHis(@Param("customerNo") Integer customerNo,@Param("status") Integer status);

    /**
     * 查询历史记录
     * @param map
     * @return
     */
    List<EmailGroupHisVo> getEmailGroupSendHisInfo(Map map);
}
