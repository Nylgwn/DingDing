package github.com.suitelhy.webchat.domain;

import github.com.suitelhy.webchat.domain.entity.Log;
import github.com.suitelhy.webchat.domain.entity.User;
import github.com.suitelhy.webchat.domain.service.LogService;
import github.com.suitelhy.webchat.domain.service.UserService;
import github.com.suitelhy.webchat.domain.vo.HandleTypeVo;
import github.com.suitelhy.webchat.domain.vo.HumanVo;
import github.com.suitelhy.webchat.infrastructure.util.CalendarController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
public class LogServiceTests {

    @Autowired
    private LogService logService;

    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {
        Assert.notNull(logService, "获取测试单元失败");
    }

    @Test
    @Transactional
    public void selectAll() {
        Page<Log> result = logService.selectAll(0, 10);
        Assert.isTrue(result.getSize() > 0
                , "The result of count() equaled to or less than 0");
        System.out.println(result);
        System.out.println(result.getContent());
        System.out.println(result.getNumber());
        System.out.println(result.getTotalPages());
        System.out.println(result.getTotalElements());
    }

    @Test
    @Transactional
    public void selectCount() {
        long result = logService.selectCount(10);
        Assert.isTrue(result > 0
                , "The result of selectCount(int) equaled to or less than 0");
        System.out.println(result);
    }

    @Test
    @Transactional
    public void selectCountByUserid() {
        //===== userService =====//
        User newUser = User.Factory.USER.create(20
                , new CalendarController().toString()
                , "127.0.0.1"
                , new CalendarController().toString()
                , "测试"
                , "a12345678"
                , "测试数据"
                , null
                , ("测试" + new CalendarController())
                , HumanVo.Sex.MALE);
        Assert.isTrue(newUser.isLegal()
                , "User.Factory.USER.create(..) -> 无效的 User");
        Assert.isTrue(userService.insert(newUser)
                , "===== insert(User) -> unexpected");
        Assert.isTrue(!newUser.isEmpty()
                , "===== insert(User) -> 无效的 User");
        System.out.println("newUser: " + newUser);

        //===== logService =====//
        //=== insert(Log log)
        Log newLog = Log.Factory.USER_LOG.create(null
                , newUser.getIp()
                , new CalendarController().toString()
                , HandleTypeVo.Log.USER_REGISTRATION
                , newUser.getUserid()
        );
        Assert.isTrue(newLog.isLegal()
                , "Log.Factory.USER_LOG.create(..) -> 无效的 Log");
        Assert.isTrue(logService.insert(newLog)
                , "===== insert(Log log) -> unexpected");
        Assert.isTrue(!newLog.isEmpty()
                , "===== insert(Log log) -> 无效的 Log");
        System.out.println("newLog: " + newLog);

        //=== selectLogByUserid(String userid, int page, int pageSize)
        Long result = logService.selectCountByUserid(newUser.getUserid()
                , 10);
        Assert.isTrue(null != result && result > 0
                , "===== selectCountByUserid(String userid, int pageSize) -> null or not enough data");
        System.out.println(result);
    }

    @Test
    @Transactional
    public void selectLogByUserid() {
        //===== userService =====//
        User newUser = User.Factory.USER.create(20
                , new CalendarController().toString()
                , "127.0.0.1"
                , new CalendarController().toString()
                , "测试"
                , "a12345678"
                , "测试数据"
                , null
                , ("测试" + new CalendarController())
                , HumanVo.Sex.MALE);
        Assert.isTrue(newUser.isLegal()
                , "User.Factory.USER.create(..) -> 无效的 User");
        Assert.isTrue(userService.insert(newUser)
                , "===== insert(User) -> unexpected");
        Assert.isTrue(!newUser.isEmpty()
                , "===== insert(User) -> 无效的 User");
        System.out.println("newUser: " + newUser);

        //===== logService =====//
        //=== insert(Log log)
        Log newLog = Log.Factory.USER_LOG.create(null
                , newUser.getIp()
                , new CalendarController().toString()
                , HandleTypeVo.Log.USER_REGISTRATION
                , newUser.getUserid()
        );
        Assert.isTrue(newLog.isLegal()
                , "Log.Factory.USER_LOG.create(..) -> 无效的 Log");
        Assert.isTrue(logService.insert(newLog)
                , "===== insert(Log log) -> unexpected");
        Assert.isTrue(!newLog.isEmpty()
                , "===== insert(Log log) -> 无效的 Log");
        System.out.println("newLog: " + newLog);

        //=== selectLogByUserid(String userid, int page, int pageSize)
        List<Log> result = logService.selectLogByUserid(newUser.getUserid()
                , 0
                , 10);
        Assert.notEmpty(result
                , "===== selectLogByUserid(String userid, int page, int pageSize) -> null or not enough data");
        System.out.println(result);
    }

    @Test
    @Transactional
    public void insert() {
        //===== userService =====//
        User newUser = User.Factory.USER.create(20
                , new CalendarController().toString()
                , "127.0.0.1"
                , new CalendarController().toString()
                , "测试"
                , "a12345678"
                , "测试数据"
                , null
                , ("测试" + new CalendarController())
                , HumanVo.Sex.MALE);
        Assert.isTrue(newUser.isLegal()
                , "User.Factory.USER.create(..) -> 无效的 User");
        Assert.isTrue(userService.insert(newUser)
                , "===== insert(User) -> unexpected");
        Assert.isTrue(!newUser.isEmpty()
                , "===== insert(User) -> 无效的 User");
        System.out.println("newUser: " + newUser);

        //===== logService =====//
        Log newLog = Log.Factory.USER_LOG.create(null
                , newUser.getIp()
                , new CalendarController().toString()
                , HandleTypeVo.Log.USER_REGISTRATION
                , newUser.getUserid()
        );
        Assert.isTrue(newLog.isLegal()
                , "Log.Factory.USER_LOG.create(..) -> 无效的 Log");
        Assert.isTrue(logService.insert(newLog)
                , "===== insert(Log log) -> unexpected");
        Assert.isTrue(!newLog.isEmpty()
                , "===== insert(Log log) -> 无效的 Log");
        System.out.println("newLog: " + newLog);
    }

    @Test
    @Transactional
    public void deleteById() {
        //===== userService =====//
        User newUser = User.Factory.USER.create(20
                , new CalendarController().toString()
                , "127.0.0.1"
                , new CalendarController().toString()
                , "测试"
                , "a12345678"
                , "测试数据"
                , null
                , ("测试" + new CalendarController())
                , HumanVo.Sex.MALE);
        Assert.isTrue(newUser.isLegal()
                , "User.Factory.USER.create(..) -> 无效的 User");
        Assert.isTrue(userService.insert(newUser)
                , "===== insert(User) -> unexpected");
        Assert.isTrue(!newUser.isEmpty()
                , "===== insert(User) -> 无效的 User");
        System.out.println("newUser: " + newUser);

        //===== logService =====//
        boolean result;
        //=== insert(Log log)
        Log newLog = Log.Factory.USER_LOG.create(null
                , newUser.getIp()
                , new CalendarController().toString()
                , HandleTypeVo.Log.USER_REGISTRATION
                , newUser.getUserid()
        );
        Assert.isTrue(newLog.isLegal()
                , "Log.Factory.USER_LOG.create(..) -> 无效的 Log");
        Assert.isTrue(logService.insert(newLog)
                , "===== saveAndFlush(Log) -> unexpected");
        Assert.isTrue(!newLog.isEmpty()
                , "===== saveAndFlush(Log) -> 无效的 Log");
        System.out.println("newLog: " + newLog);

        //=== deleteById(String id)
        Assert.isTrue(result = logService.deleteById(newLog.id())
                , "");
        System.out.println(result);
        System.out.println(newLog);
    }

}
