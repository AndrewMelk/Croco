package croco.com.croco.utils;

import android.os.CountDownTimer;

/**
 * Created by Andrew on 04.04.2017.
 */

public class GameTimer extends CountDownTimer {
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public GameTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long millisUntilFinished) {

    }

    @Override
    public void onFinish() {

    }
}
