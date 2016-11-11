import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import com.jogamp.opengl.awt.GLCanvas;

import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.awt.Toolkit;

public class MeshViewer implements MouseMotionListener,MouseListener{

	public JFrame frmLiying;

	public double rotateX =0;
	public double rotateY =0;
	public double rotateZ =0;

	public double translateX =0;
	public double translateY =0;
	public double translateZ =0;

	public double zoom =0;	

	public double rotateChangeX =0;
	double rotateChangeY =0;
	public double translateChangeX =0;
	public double translateChangeY =0;
	public double translateChangeZ =0;

	public double zoomChange =0;




	/**
	 * Launch the application.
	 * @wbp.parser.entryPoint
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MeshViewer window = new MeshViewer();
//					window.frmLiying.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	JCheckBox Point;
	JCheckBox Wireframe ;
	 JRadioButton Flat;
	 JRadioButton Smooth; 
	   JCheckBox Box;

	  JLabel lblProcess;

	  JLabel lblNewLabel;

	  JLabel lblNewLabel_1;

	  JLabel lblFaceverticesNormalDone;

	  JLabel lblNewLabel_2;

	  JLabel lblHowToOperation;

	  JLabel lblChooseTheM;

	  JButton Open;
	 
	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	public void initialize(GLCanvas glcanvas) {
		frmLiying = new JFrame();
		frmLiying.setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\\u5728NTU\\eclipse\\MeshViewer\\LiYing.png"));
		frmLiying.setTitle("LiYing_MeshViewer");
		frmLiying.setBounds(100, 100, 450, 300);
		frmLiying.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		glcanvas.setBounds(0,60,666,700);
	    frmLiying.getContentPane().add(glcanvas);
	    frmLiying.getContentPane().setLayout(null);
	    
	      Point = new JCheckBox("Point Cloud");
	    Point.setBounds(241, 18, 97, 23);
	    frmLiying.getContentPane().add(Point);
	    
	      Wireframe = new JCheckBox("Wireframe");
	    Wireframe.setBounds(353, 18, 97, 23);
	    frmLiying.getContentPane().add(Wireframe);
	    
	      Flat = new JRadioButton("Flat Mode");
	    Flat.setBounds(130, 18, 109, 23);
	    frmLiying.getContentPane().add(Flat);
	    
	      Smooth = new JRadioButton("Smooth Mode");
	    Smooth.setBounds(19, 18, 109, 23);
	    frmLiying.getContentPane().add(Smooth);
	    ButtonGroup shading = new ButtonGroup();
	    shading.add(Smooth);
	    shading.add(Flat);
	    
	    Box = new JCheckBox("Bounding Box");
	    Box.setBounds(452, 18, 117, 23);
	    frmLiying.getContentPane().add(Box);
	    
	      lblProcess = new JLabel("------Process\r\n------");
	    lblProcess.setBounds(706, 57, 109, 14);
	    frmLiying.getContentPane().add(lblProcess);
	    
	      lblNewLabel = new JLabel("M-File Parser Done");
	    lblNewLabel.setBounds(696, 101, 176, 14);
	    lblNewLabel.setVisible(false);
	    frmLiying.getContentPane().add(lblNewLabel);
	    
	      lblNewLabel_1 = new JLabel("Half-edge Data Structure Done");
	    lblNewLabel_1.setBounds(696, 126, 176, 14);
	    lblNewLabel_1.setVisible(false);

	    frmLiying.getContentPane().add(lblNewLabel_1);
	    
	     lblFaceverticesNormalDone = new JLabel("Face&Vertices Normal Done");
	    lblFaceverticesNormalDone.setBounds(696, 151, 176, 14);
	    lblFaceverticesNormalDone.setVisible(false);

	    frmLiying.getContentPane().add(lblFaceverticesNormalDone);
	    
	     lblNewLabel_2 = new JLabel("Enjoy the Model!");
	    lblNewLabel_2.setBounds(696, 176, 176, 14);
	    lblNewLabel_2.setVisible(false);
	    frmLiying.getContentPane().add(lblNewLabel_2);
	    
	     lblHowToOperation = new JLabel("How to OPERATION");
	    lblHowToOperation.setBounds(706, 218, 109, 14);
	    lblHowToOperation.setVisible(false);

	    frmLiying.getContentPane().add(lblHowToOperation);
	    
	     lblChooseTheM = new JLabel("<html>\r\n<br/>\r\nLEFT mouse button for Rotation <br/>\r\nMIDDLE mouse button for Translation<br/>\r\nRIGHT mouse button for Zoom in/out<br/>\r\n     <br/>\r\nPlease click \"Open\" to see more details<br/>\r\n      <br/>\r\n</html>");
	    lblChooseTheM.setBounds(678, 243, 205, 134);
	    lblChooseTheM.setVisible(false);

	    frmLiying.getContentPane().add(lblChooseTheM);
	    
	    Open = new JButton("Open");
	    Open.setBounds(716, 388, 89, 23);
	    Open.setVisible(false);

	    frmLiying.getContentPane().add(Open);
	    
		frmLiying.setSize(906,700);
		frmLiying.setVisible(true);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
//		System.out.println(e.getModifiers());
		if(e.getModifiers()==16){//left
		
		double rotateX_c = e.getX();
		double rotateY_c = e.getY();

		rotateChangeX  = rotateX-rotateX_c;
		rotateChangeY  = rotateY-rotateY_c;

//		System.out.println(e.getModifiers()+"rotateChange="+rotateChangeX+","+rotateChangeY);
		frmLiying.repaint();
		}
		
		if(e.getModifiers()==8){//middle
			double translateX_c = e.getX();
			double translateY_c = e.getY();

			translateChangeX  = translateX-translateX_c;
			translateChangeY  = translateY-translateY_c;
//			System.out.println(e.getModifiers()+"translateChangeX="+translateChangeX+","+translateChangeY);

			frmLiying.repaint();
		}
		if(e.getModifiers()==4){//right
			double zoom_c = e.getY();

			zoomChange  = zoom-zoom_c;//>0 near <0 far
//			System.out.println("zoomChange="+zoomChange);
			frmLiying.repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton()==MouseEvent.BUTTON1){
			rotateX = e.getX();
			rotateY = e.getY();

		System.out.println("pressed left"+rotateX);
		}
		
		if(e.getButton()==MouseEvent.BUTTON2){
			translateX = e.getX();
			translateY = e.getY();

		System.out.println("pressed middle");
		}	
		
		if(e.getButton()==MouseEvent.BUTTON3){
			zoom =e.getY();
			System.out.println("pressed right");
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton()==MouseEvent.BUTTON1){
		System.out.println("Released left");
		}
		
		if(e.getButton()==MouseEvent.BUTTON2){
		System.out.println("Released middle");
		}	
		
		if(e.getButton()==MouseEvent.BUTTON3){
			System.out.println("Released right");
		}
	}
}
