package com.example.app1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private XYPlot plot;
    private XYSeries s1, s2;
    private Random r;
    private int x1;
    private List<Integer> nums, numsTotal;
    private Button btn;
    private boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r = new Random();
        plot = (XYPlot) findViewById(R.id.plot);
        nums = new ArrayList<Integer>();
        numsTotal = new ArrayList<Integer>();
        isRunning = false;
        btn = (Button) findViewById(R.id.startBtn);
        setNums();
    }

    public void setNums(){
        for(int i=0;i<=10;i++){
            nums.add(0);
        }
    }

    public void start(View view) {

        if(!isRunning){
            isRunning = true;
            btn.setText("Stop");
            final Timer t = new Timer();
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    if(!isRunning){
                        t.cancel();
                    }
                    x1 = r.nextInt(10);
                    nums.add(x1);
                    numsTotal.add(x1);

                    if (nums.size() > 10) {
                        nums.remove(0);
                    }

                    plot.clear();
                    s1 = new SimpleXYSeries(nums, SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Time unit");
                    plot.addSeries(s1, new LineAndPointFormatter(Color.GREEN, Color.RED, null, null)); //first color is for the lines and second for the points
                    plot.redraw();
                }
            }, 0, 1000);
        }else{
            isRunning = false;
            btn.setText("Start");
            plot.clear();
            s2 = new SimpleXYSeries(numsTotal, SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Time unit");
            plot.addSeries(s2, new LineAndPointFormatter(Color.GREEN, Color.RED, null, null)); //first color is for the lines and second for the points
            plot.redraw();

        }
    }
}
