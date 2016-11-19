package atm;

import atm.model.Atm;
import atm.view.MainFrame;

public class Main{

	public static void main(String[] args) throws Exception  {
        Atm atm = new Atm("DreamBank");
        MainFrame mainFrame = new MainFrame("ATM");
	}

}
