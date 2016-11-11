import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class Help extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Help frame = new Help();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Help() {
		setTitle("Help");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 486, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html>\r\nOperation Manual<br/>\r\n  <br/>\r\n1.Rotate the object:<br/>\r\nHold left mouse button to drag (not to much)<br/>\r\nRelease<br/>\r\nHold the window and shake several times till it repaint<br/>\r\n(I know it's werid, sorry for I didn't find the auto-repaint function in java T_T)<br/>\r\nKeep shaking so that it can rotate automaticlly with the magnitude and direction you dragged<br/>\r\nRe-drag if you want to chage the rotation<br/>\r\n   <br/>\r\n Translate the object:<br/>\r\nHold middle mouse button to drag (if you want it go up, move your mouse bottom up, but not to much)<br/>\r\nThe other operations is similar to Rotation operation<br/>\r\n    <br/>\r\n3.Zoom in/out the object:<br/>\r\nHold right mouse button to drag <br/>\r\nbottom-up to zoom out and top-down to zoom in <br/>\r\nHow much you will zoom depends on the distance your mouse move <br/>");
		lblNewLabel.setBounds(20, 11, 450, 427);
		contentPane.add(lblNewLabel);
	}

}
