import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import com.jogamp.opengl.awt.GLCanvas;

import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

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
	 JButton Open;
	   JCheckBox Box;
	 
	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	public void initialize(GLCanvas glcanvas) {
		frmLiying = new JFrame();
		frmLiying.setBounds(100, 100, 450, 300);
		frmLiying.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		glcanvas.setBounds(0,60,666,700);
	    frmLiying.getContentPane().add(glcanvas);
	    frmLiying.getContentPane().setLayout(null);
	    
	      Point = new JCheckBox("Point Cloud");
	    Point.setBounds(335, 18, 97, 23);
	    frmLiying.getContentPane().add(Point);
	    
	      Wireframe = new JCheckBox("Wireframe");
	    Wireframe.setBounds(447, 18, 97, 23);
	    frmLiying.getContentPane().add(Wireframe);
	    
	      Flat = new JRadioButton("Flat Mode");
	    Flat.setBounds(224, 18, 109, 23);
	    frmLiying.getContentPane().add(Flat);
	    
	      Smooth = new JRadioButton("Smooth Mode");
	    Smooth.setBounds(113, 18, 109, 23);
	    frmLiying.getContentPane().add(Smooth);
	    ButtonGroup shading = new ButtonGroup();
	    shading.add(Smooth);
	    shading.add(Flat);
	    
	      Open = new JButton("Open");
	    Open.setBounds(18, 18, 89, 23);
	    frmLiying.getContentPane().add(Open);
	    
	    Box = new JCheckBox("Bounding Box");
	    Box.setBounds(546, 18, 117, 23);
	    frmLiying.getContentPane().add(Box);
	    
		frmLiying.setSize(666,700);
		frmLiying.setVisible(true);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getModifiers());
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
			System.out.println(e.getModifiers()+"translateChangeX="+translateChangeX+","+translateChangeY);

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
