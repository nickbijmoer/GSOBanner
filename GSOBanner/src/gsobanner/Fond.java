/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsobanner;

/**
 *
 * @author Bart Memelink
 */
public class Fond implements IFonds {

    private String name;
    private double koers;
    
    public Fond (String Name, double Koers)
    {
        this.name = Name;
        this.koers = Koers;
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getKoers() {
        return koers;
    }
    
    public void setKoers(double Koers)
    {
        this.koers = Koers;
    }
    
}
