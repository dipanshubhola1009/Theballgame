package gamenw;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame g = new JFrame();
		gameplay mygame= new gameplay();
		g.setBounds(10,10,700,600);
		g.setResizable(false);
		g.setVisible(true);
		g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		g.add(mygame);

	}

}
