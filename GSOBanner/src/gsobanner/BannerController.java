/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsobanner;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Timer;
import javax.annotation.Resource;

/**
 *
 * @author Bart Memelink
 */
public class BannerController {
    private AEXBanner banner;
    private IEffectenbeurs effectenbeurs;
    private Timer pollingTimer;
    public Registry registry = null;
    public String bindingName = "AEX";
    public int portNumber = 5081;
    public String ipAddress = "10.0.1.13";
    private List<IFonds> fondslist;


    public BannerController(AEXBanner banner) throws RemoteException {
        connect();

        this.banner = banner;
        this.effectenbeurs = new MockEffectenbeurs();
        
        // Start polling timer: update banner every two seconds
        pollingTimer = new Timer();
        // TODO
        pollingTimer.schedule(new UpdateBannerTask(banner,effectenbeurs), 2000,2000);
    }

    // Stop banner controller
    public void stop() {
        pollingTimer.cancel();
        // Stop simulation timer of effectenbeurs
        // TODO
        ((MockEffectenbeurs) effectenbeurs).StopTimer();
    }
    
    public void connect() throws RemoteException {

        try {
            registry = LocateRegistry.getRegistry(ipAddress, portNumber);
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: RemoteException: " + ex.getMessage());
            registry = null;
        }
        if (registry != null) {
        } else {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: Registry is null pointer");
        }

        if (registry != null) {

            try {
                effectenbeurs = (IEffectenbeurs) registry.lookup(bindingName);
            } catch (RemoteException | NotBoundException ex) {
                effectenbeurs = null;
            }

            if (effectenbeurs != null) {
            } else {
                
                System.out.println("Client: Effectenbeur is null pointer");
               
            }
            

        }
    }
    

}
