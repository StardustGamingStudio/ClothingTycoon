package stardust.game.clothingtycoon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import stardust.game.clothingtycoon.common.FullscreenOptimization;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener
{
    FullscreenOptimization fullscreenOptimization;

    int Money = 0, Cost = 10, Increase = 1, Multiply = 2;

    TextView Money_TextView, Object_1_Level_TextView, Multiply_TextView;
    Button LevelUp_Object_1_Button;

    Timer Money_Timer; TimerTask Money_TimerTask;

    private View selected_item = null;
    private int offset_x = 0;
    private int offset_y = 0;
    private int width = 0;
    private int height = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        fullscreenOptimization = new FullscreenOptimization(getSupportActionBar(), getWindow().getDecorView());
        fullscreenOptimization.Optimization();

        Money_TextView = findViewById(R.id.Money_TextView);

        ConstraintLayout Shop_ConstraintLayout = findViewById(R.id.Shop_ConstraintLayout);
        final FrameLayout Main_FrameLayout = findViewById(R.id.Main_FrameLayout);

        Shop_ConstraintLayout.setOnTouchListener(this);
        Main_FrameLayout.setOnTouchListener(new View.OnTouchListener()
        {
            public boolean onTouch(View v, MotionEvent event)
            {
                switch (event.getActionMasked())
                {
                    case MotionEvent.ACTION_DOWN:
                        break;

                    case MotionEvent.ACTION_MOVE:
                        int x = (int) event.getX() - offset_x;
                        int y = (int) event.getY() - offset_y;

                        FrameLayout.LayoutParams lp =
                                new FrameLayout.LayoutParams(
                                        new ViewGroup.MarginLayoutParams(width, height));

                        lp.setMargins(x, y, -x, -y);
                        selected_item.setLayoutParams(lp);
                        break;

                    case MotionEvent.ACTION_UP:
                        break;

                    default:
                        break;
                }
                return true;
            }
        });
    }

    public boolean onTouch(View v, MotionEvent event)
    {
        switch (event.getActionMasked())
        {
            case MotionEvent.ACTION_DOWN:
                offset_x = (int) event.getX();
                offset_y = (int) event.getY();
                width = v.getWidth();
                height = v.getHeight();
                selected_item = v;
                break;

            case MotionEvent.ACTION_UP:
                selected_item = null;
                break;

            default:
                break;
        }
        return false;
    }
}