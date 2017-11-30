package il.ac.hit.shanyshiranshani.financialmanagementapp;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Shiran on 29-Jun-16.
 */
public class WebAppInterface {
    Context mContext;

    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
        mContext = c;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void loginSuccess(String userId, String userName) throws IOException {
        String FILENAME = "userId";
        Toast.makeText(mContext, "Welcome " + userName, Toast.LENGTH_LONG).show();
        FileOutputStream fos = mContext.openFileOutput(FILENAME, Context.MODE_PRIVATE);
        fos.write(userId.getBytes());
        fos.close();
    }
}
