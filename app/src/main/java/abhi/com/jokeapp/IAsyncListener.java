package abhi.com.jokeapp;

/**
 * Created by Abhishek on 15-May-16.
 */
public interface IAsyncListener {
    public void onAsyncStart();
    public void onAsyncFinish(String joke);
}
