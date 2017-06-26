package thread.concurrent.countdownlatch;

/**
 * <p>Title:      与会者信息类. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate 2017/6/22 10:42
 */
public class Participant implements Runnable {

    private VideoConference videoConference;

    private String name;

    public Participant(VideoConference videoConference, String name) {
        this.videoConference = videoConference;
        this.name = name;
    }

    @Override
    public void run() {
        long duration = (long) (Math.random() * 10);

        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //与会者到来
        videoConference.arrive(name);
    }

}
