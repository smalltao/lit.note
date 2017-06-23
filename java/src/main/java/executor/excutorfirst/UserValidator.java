package executor.excutorfirst;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title:      用户验证类. </p>
 * <p>Description 运行多个任务并处理第一个结果 </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/23 13:50
 */
public class UserValidator {

    private String name;

    public UserValidator(String name) {
        this.name = name;
    }

    //模拟用户验证
    public boolean validate(String name ,String password) {

        Random random = new Random();

        try {
            long duration = (long) (Math.random() * 10);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            return false;
        }
        return random.nextBoolean();

    }

    public String getName() {
        return name;
    }
}
