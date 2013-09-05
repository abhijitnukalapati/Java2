package library;

/**
 * Created by Patrick on 9/4/13.
 */
import android.widget.TextView;
import android.app.Activity;
import com.app.java2patrick.R;

public class PostDisplay {

    public static void showResult(Activity activity, String title, String postURL) {


        //Not sure I need PostDisplay anymore...
        TextView postTitle2 = (TextView) activity.findViewById(R.id.game_title2);
        TextView postUrl2 = (TextView) activity.findViewById(R.id.game_url2);
        TextView postTitle3 = (TextView) activity.findViewById(R.id.game_title3);
        TextView postUrl3 = (TextView) activity.findViewById(R.id.game_url3);
        TextView postTitle4 = (TextView) activity.findViewById(R.id.game_title4);
        TextView postUrl4 = (TextView) activity.findViewById(R.id.game_url4);
        TextView postTitle5 = (TextView) activity.findViewById(R.id.game_title5);
        TextView postUrl5 = (TextView) activity.findViewById(R.id.game_url5);


        postTitle2.setText(title);
        postUrl2.setText(postURL);
        postTitle3.setText(title);
        postUrl3.setText(postURL);
        postTitle4.setText(title);
        postUrl4.setText(postURL);
        postTitle5.setText(title);
        postUrl5.setText(postURL);
    }

}