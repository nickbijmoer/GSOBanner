/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsobanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Bart Memelink
 */
public class MockEffectenbeurs implements IEffectenbeurs{

    private List<IFonds> fonds;
    private Timer timer;
    private Random random;
    
    public MockEffectenbeurs()
    {
        ArrayList<IFonds> temps = new ArrayList<IFonds>();
        temps.add(new Fond("Nick", 5.2));
        temps.add(new Fond("Bart", 7.1));
        temps.add(new Fond("Turtle", 2));
        this.fonds = temps;
        this.timer = new Timer();
        this.random = new Random();
        StartTimer();
    }
    
    @Override
    public List<IFonds> getKoersen() {
        return fonds;
    }
    
    private void StartTimer()
    {
        this.timer.schedule(new TimerTask() {

            @Override
            public void run() {
                 for(IFonds f : fonds)
                   ((Fond) f).setKoers(random.nextDouble() + random.nextInt(100));
            }
        }, 0, 8000);
    }
    
    public void StopTimer()
    {
        this.timer.cancel();
    }
    
}
