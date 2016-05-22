package abhi.com.jokeapp;

import android.content.Context;
import android.support.v4.util.Pair;
import android.test.AndroidTestCase;

/**
 * Created by Abhishek on 21-May-16.
 */
public class GetJokeAsyncTest extends AndroidTestCase implements IAsyncListener {

    private String mJoke = "";
    private GetJokeAsync mAsync;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mAsync = new GetJokeAsync(this);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        mAsync = null;
    }

    public void testAsync() {
        {
            assertTrue(mJoke.length() == 0);
            mAsync.execute();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            assertTrue(mJoke.length() > 0);
        }
    }

    @Override
    public void onAsyncStart() {

    }

    @Override
    public void onAsyncFinish(String joke) {
        mJoke = joke;
    }
}
