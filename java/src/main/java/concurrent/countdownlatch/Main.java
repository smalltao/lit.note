package concurrent.countdownlatch;


/**
 * <p>Title:      视频会议主类. </p>
 * <p>Description TODO </p>
 * <p>Company:    https://www.sogou.com/ </p>
 *
 * @Author         <a href="litaoos2862@sogou-inc.com"/>李涛</a>
 * @CreateDate     2017/6/22 10:47
 */
public class Main {

    public static void main(String[] args) {
        //视频会议对象等待10个与会者到来
        VideoConference videoConference = new VideoConference(10);

        //创建10个与会者对象
        for (int i = 0; i < 10; i++) {
            Participant participant = new Participant(videoConference, "participant"+ i);
            Thread partThread = new Thread(participant);
            partThread.start();
        }
    }
}
