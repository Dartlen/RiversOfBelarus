package java.com.mvp.base;

import static org.junit.Assert.fail;

/***
 * Created by Dartlen on 31.12.2017.
 */

public class AbstractTest {
    protected void waitFor(int milliseconds){
        try{
            Thread.sleep(milliseconds);

        }catch (Exception e){
            fail();
        }
    }

    protected void waitForCondition(Condition condition, int time) {
        int timeWaited = 0;
        while (timeWaited < time) {
            if (condition.isSatisfied()) {
                return;
            }
            waitFor(200);
            timeWaited += 200;
        }
    }
}
