package atm;

import atm.model.Atm;
import atm.view.MainFrame;

public class Main{

	public static void main(String[] args) throws Exception  {
        Atm atm = Atm.getInstance();
        atm.setBankName("DreamBank");
        MainFrame mainFrame = MainFrame.getInstance();
        Dispatcher dispatcher = new Dispatcher(atm, mainFrame);
	}
}
