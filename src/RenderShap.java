import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import com.jogamp.newt.event.MouseListener;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.gl2.GLUT;


public class RenderShap implements GLEventListener, ActionListener{

	 static RenderShap rs = new RenderShap();
     static MeshViewer window = new MeshViewer();
     static ReadFile rf = new ReadFile();
 	private GLU glu  = new GLU();
 	static double rotationX = 0;
 	static double rotationY = 0;
 	static double translationX=0;
 	static double translationY=0;
 	static double translationZ=0;
 	static double zoomI=0;
// 	static double zoomO;




	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		final GL2 gl = drawable.getGL().getGL2();
		final GLU glu = GLU.createGLU(gl);
	    final GLUT glut = new GLUT();

	    float no_mat[] =
		    { 0.0f, 0.0f, 0.0f, 1.0f };
		    float mat_ambient[] =
		    { 0.7f, 0.7f, 0.7f, 1.0f };
		    float mat_ambient_color[] =
		    { 0.8f, 0.8f, 0.2f, 1.0f };
		    float mat_diffuse[] =
		    { 0.1f, 0.5f, 1f, 1.0f };
		    float mat_specular[] =
		    { 1.0f, 0.0f, 0.0f, 1.0f };
		    float no_shininess[] =
		    { 0.0f };
		    float low_shininess[] =
		    { 5.0f };
		    float high_shininess[] =
		    { 100.0f };
		    float mat_emission[] =
		    { 0.0f, 0.1f, 0.2f, 0.0f };

		gl.glClear(gl.GL_COLOR_BUFFER_BIT|gl.GL_ACCUM_BUFFER_BIT);


		rotationX+=window.rotateChangeX;
		rotationY+=window.rotateChangeY;
		translationX+=window.translateChangeX;
		translationY+=window.translateChangeY;

//		
		

			// grid
				 gl.glPushMatrix();
				 gl.glLoadIdentity();
				 gl.glColor3f(0, 0, 0);
				 for(int b=-10;b<=10;b++){
				 gl.glBegin(gl.GL_LINE_STRIP);
				 for(int a = -10;a<=10;a++){
				 gl.glVertex3f(b, 0, a);
				 }
				 gl.glEnd();
				 }
				 gl.glPopMatrix();
				 
				 gl.glPushMatrix();
				 gl.glLoadIdentity();
				 gl.glColor3f(0, 0, 0);
				 for(int b=-10;b<=10;b++){
				 gl.glBegin(gl.GL_LINE_STRIP);
				 for(int a = -10;a<=10;a++){
				 gl.glVertex3f(a, 0, b);
				 }
				 gl.glEnd();
				 }
				 gl.glPopMatrix();
			  
				 gl.glPushMatrix();//red y axis
				 gl.glLoadIdentity();
			     gl.glRotatef(-90, 1, 0, 0);
			     gl.glColor3f(1, 0, 0);
				  GLUquadric Yaxis = glu.gluNewQuadric();
				  glu.gluCylinder(Yaxis, 0.035, 0.035, 10, 10, 1);
				  System.out.println("===red===");
			 gl.glPopMatrix();
			 // system
		     gl.glPushMatrix();//green x axis
				 gl.glLoadIdentity();
			     gl.glRotatef(90, 0, 1, 0);
			     gl.glColor3f(0, 1, 0);
				  GLUquadric Xaxis = glu.gluNewQuadric();
				  glu.gluCylinder(Xaxis, 0.035, 0.035, 10, 10, 1);
				  System.out.println("==green=====");
			 gl.glPopMatrix();
			 
		     gl.glPushMatrix();//blue z axis
				 gl.glLoadIdentity();
			     gl.glRotatef(0, 1, -1, 0);
			     gl.glColor3f(0, 0, 1);
				  GLUquadric Zaxis = glu.gluNewQuadric();
				  glu.gluCylinder(Zaxis, 0.035, 0.035, 10, 10, 1);
				  System.out.println("===blue====");
			 gl.glPopMatrix();
			 
			 gl.glPushMatrix();//y arrow
				 gl.glLoadIdentity();
				 gl.glTranslatef(0, 10f, 0f);
			     gl.glRotatef(-90, 1, 0, 0);
			     gl.glColor3f(1, 0, 0);
			      glut.glutSolidCone(0.1, 0.3, 10, 1);
				  System.out.println("============");
		     gl.glPopMatrix();
	     
			 gl.glPushMatrix();//x arrow
				 gl.glLoadIdentity();
				 gl.glTranslatef(10f, 0f, 0f);
			     gl.glRotatef(90, 0, 1, 0);
			     gl.glColor3f(0, 1, 0);
			      glut.glutSolidCone(0.1, 0.3, 10, 1);
				  System.out.println("============");
	         gl.glPopMatrix();
	         
			 gl.glPushMatrix();//z arrow
				 gl.glLoadIdentity();
			     gl.glRotatef(0, 1, -1, 0);
				 gl.glTranslatef(0, 0f, 10f);
			     gl.glColor3f(0, 0, 1);
			      glut.glutSolidCone(0.1, 0.3, 10, 1);
				  System.out.println("============");
			 gl.glPopMatrix();
			  //draw face
			  gl.glPushMatrix();
			  gl.glLoadIdentity();
			  gl.glRotated(-rotationX/30, 0, 1, 0);
			  gl.glRotated(-rotationY/30, 1, 0, 0);
			  gl.glTranslated(-translationX/1000,translationY/1000, 0);
			  gl.glBegin(GL2.GL_TRIANGLES);
			  gl.glColor4f(1, 1, 1, 0.5f);;
		      for(int b=1;b<=rf.face_num;b++){
		  	    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, mat_ambient, 0);
			    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, mat_diffuse, 0);
			    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, mat_specular, 0);
			    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SHININESS, high_shininess, 0);
			    gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_EMISSION, no_mat, 0);
		      gl.glNormal3d(rf.vertexNormal[rf.face[b][0]][0]/(0.1*rf.diagnal), rf.vertexNormal[rf.face[b][0]][1]/(0.1*rf.diagnal), rf.vertexNormal[rf.face[b][0]][2]/(0.1*rf.diagnal));   
		      gl.glVertex3d(rf.vertex[rf.face[b][0]][0]/(0.1*rf.diagnal), rf.vertex[rf.face[b][0]][1]/(0.1*rf.diagnal), rf.vertex[rf.face[b][0]][2]/(0.1*rf.diagnal));
		      gl.glNormal3d(rf.vertexNormal[rf.face[b][1]][0]/(0.1*rf.diagnal), rf.vertexNormal[rf.face[b][1]][1]/(0.1*rf.diagnal), rf.vertexNormal[rf.face[b][1]][2]/(0.1*rf.diagnal));   
		      gl.glVertex3d(rf.vertex[rf.face[b][1]][0]/(0.1*rf.diagnal), rf.vertex[rf.face[b][1]][1]/(0.1*rf.diagnal), rf.vertex[rf.face[b][1]][2]/(0.1*rf.diagnal));
		      gl.glNormal3d(rf.vertexNormal[rf.face[b][2]][0]/(0.1*rf.diagnal), rf.vertexNormal[rf.face[b][2]][1]/(0.1*rf.diagnal), rf.vertexNormal[rf.face[b][2]][2]/(0.1*rf.diagnal));   
		      gl.glVertex3d(rf.vertex[rf.face[b][2]][0]/(0.1*rf.diagnal), rf.vertex[rf.face[b][2]][1]/(0.1*rf.diagnal), rf.vertex[rf.face[b][2]][2]/(0.1*rf.diagnal));
		      
		      }
		      gl.glEnd();
			  gl.glPopMatrix();
			  
			//draw bounding box
			  if (window.Box.isSelected()) {
				
			
			  gl.glPushMatrix();//top
			  gl.glLoadIdentity();
			  gl.glRotated(-rotationX/30, 0, 1, 0);
			  gl.glRotated(-rotationY/30, 1, 0, 0);
			  gl.glTranslated(-translationX/1000,translationY/1000, 0);

//			  gl.glTranslated(translationX/80,translationY/80, 0f);
			  gl.glBegin(GL2.GL_LINE_LOOP);
			  gl.glColor3f(1, 0,0);
			  gl.glLineWidth(5);
		      gl.glVertex3d(rf.vertexMax_x/(0.1*rf.diagnal), rf.vertexMax_y/(0.1*rf.diagnal), rf.vertexMax_z/(0.1*rf.diagnal));
		      gl.glVertex3d(rf.vertexMax_x/(0.1*rf.diagnal), rf.vertexMax_y/(0.1*rf.diagnal), rf.vertexMin_z/(0.1*rf.diagnal));
		      gl.glVertex3d(rf.vertexMin_x/(0.1*rf.diagnal), rf.vertexMax_y/(0.1*rf.diagnal), rf.vertexMin_z/(0.1*rf.diagnal));
		      gl.glVertex3d(rf.vertexMin_x/(0.1*rf.diagnal), rf.vertexMax_y/(0.1*rf.diagnal), rf.vertexMax_z/(0.1*rf.diagnal));   
		      System.out.println("("+rf.vertexMax_x+","+ rf.vertexMax_y+","+ rf.vertexMax_z+")");
		      System.out.println("("+rf.vertexMax_x+","+ rf.vertexMax_y+","+ rf.vertexMin_z+")");
		      System.out.println("("+rf.vertexMin_x+","+ rf.vertexMax_y+","+ rf.vertexMin_z+")");
		      System.out.println("("+rf.vertexMin_x+","+ rf.vertexMax_y+","+ rf.vertexMax_z+")");

		      gl.glEnd();
			  gl.glPopMatrix();	
			  
			  gl.glPushMatrix();//bottom
			  gl.glLoadIdentity();
			  gl.glRotated(-rotationX/30, 0, 1, 0);
			  gl.glRotated(-rotationY/30, 1, 0, 0);
			  gl.glTranslated(-translationX/1000,translationY/1000, 0);

			  gl.glBegin(GL2.GL_LINE_LOOP);
			  gl.glColor3f(1, 0,0);
			  gl.glLineWidth(5);
		      gl.glVertex3d(rf.vertexMax_x/(0.1*rf.diagnal), rf.vertexMin_y/(0.1*rf.diagnal), rf.vertexMax_z/(0.1*rf.diagnal));
		      gl.glVertex3d(rf.vertexMax_x/(0.1*rf.diagnal), rf.vertexMin_y/(0.1*rf.diagnal), rf.vertexMin_z/(0.1*rf.diagnal));
		      gl.glVertex3d(rf.vertexMin_x/(0.1*rf.diagnal), rf.vertexMin_y/(0.1*rf.diagnal), rf.vertexMin_z/(0.1*rf.diagnal));
		      gl.glVertex3d(rf.vertexMin_x/(0.1*rf.diagnal), rf.vertexMin_y/(0.1*rf.diagnal), rf.vertexMax_z/(0.1*rf.diagnal));   
		      gl.glEnd();
			  gl.glPopMatrix();	
			  
			  gl.glPushMatrix();
			  gl.glLoadIdentity();
			  gl.glRotated(-rotationX/30, 0, 1, 0);
			  gl.glRotated(-rotationY/30, 1, 0, 0);
			  gl.glTranslated(-translationX/1000,translationY/1000, 0);

			  gl.glBegin(GL2.GL_LINES);
			  gl.glColor3f(1, 0,0);
			  gl.glLineWidth(5);
		      gl.glVertex3d(rf.vertexMax_x/(0.1*rf.diagnal), rf.vertexMax_y/(0.1*rf.diagnal), rf.vertexMax_z/(0.1*rf.diagnal));
		      gl.glVertex3d(rf.vertexMax_x/(0.1*rf.diagnal), rf.vertexMin_y/(0.1*rf.diagnal), rf.vertexMax_z/(0.1*rf.diagnal));

		      gl.glVertex3d(rf.vertexMax_x/(0.1*rf.diagnal), rf.vertexMax_y/(0.1*rf.diagnal), rf.vertexMin_z/(0.1*rf.diagnal));
		      gl.glVertex3d(rf.vertexMax_x/(0.1*rf.diagnal), rf.vertexMin_y/(0.1*rf.diagnal), rf.vertexMin_z/(0.1*rf.diagnal));

		      gl.glVertex3d(rf.vertexMin_x/(0.1*rf.diagnal), rf.vertexMax_y/(0.1*rf.diagnal), rf.vertexMin_z/(0.1*rf.diagnal));
		      gl.glVertex3d(rf.vertexMin_x/(0.1*rf.diagnal), rf.vertexMin_y/(0.1*rf.diagnal), rf.vertexMin_z/(0.1*rf.diagnal));

		      gl.glVertex3d(rf.vertexMin_x/(0.1*rf.diagnal), rf.vertexMax_y/(0.1*rf.diagnal), rf.vertexMax_z/(0.1*rf.diagnal));   
		      gl.glVertex3d(rf.vertexMin_x/(0.1*rf.diagnal), rf.vertexMin_y/(0.1*rf.diagnal), rf.vertexMax_z/(0.1*rf.diagnal));   

		      gl.glEnd();
			  gl.glPopMatrix();		
			  }
			  if (window.Wireframe.isSelected()) {
				
			
			//draw edge
			  gl.glPushMatrix();
			  gl.glLoadIdentity();
			  gl.glRotated(-rotationX/30, 0, 1, 0);
			  gl.glRotated(-rotationY/30, 1, 0, 0);
			  gl.glTranslated(-translationX/1000,translationY/1000, 0);
			  
			  gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, no_mat, 0);
			  gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, mat_diffuse, 0);
			  gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, no_mat, 0);
			  gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SHININESS, no_shininess, 0);
			  gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_EMISSION, mat_emission, 0);
		      gl.glBegin(GL2.GL_LINES);
			  gl.glColor3f(0, 1, 0);
		      for(int b=1;b<=rf.face_num;b++){
		      gl.glVertex3d(rf.vertex[rf.face[b][0]][0]/(0.1*rf.diagnal), rf.vertex[rf.face[b][0]][1]/(0.1*rf.diagnal), rf.vertex[rf.face[b][0]][2]/(0.1*rf.diagnal));
		      gl.glVertex3d(rf.vertex[rf.face[b][1]][0]/(0.1*rf.diagnal), rf.vertex[rf.face[b][1]][1]/(0.1*rf.diagnal), rf.vertex[rf.face[b][1]][2]/(0.1*rf.diagnal));
		      gl.glVertex3d(rf.vertex[rf.face[b][2]][0]/(0.1*rf.diagnal), rf.vertex[rf.face[b][2]][1]/(0.1*rf.diagnal), rf.vertex[rf.face[b][2]][2]/(0.1*rf.diagnal));
		      }
		      gl.glEnd();
		     gl.glPopMatrix(); 
			  }
			  
			  if (window.Point.isSelected()) {

//		      draw point cloud
				gl.glPushMatrix();
				  gl.glLoadIdentity();
				  gl.glRotated(-rotationX/30, 0, 1, 0);
				  gl.glRotated(-rotationY/30, 1, 0, 0);
				  gl.glTranslated(-translationX/1000,translationY/1000, 0);
//
				  gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, no_mat, 0);
				  gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, mat_diffuse, 0);
				  gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, no_mat, 0);
				  gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SHININESS, no_shininess, 0);
				  gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_EMISSION, no_mat, 0);		    
				  gl.glBegin (GL2.GL_POINTS);
				  gl.glColor3f(0, 0, 1);
			      for(int a=1;a<=rf.vertex_num;a++){
					gl.glVertex3d(rf.vertex[a][0]/(0.1*rf.diagnal), rf.vertex[a][1]/(0.1*rf.diagnal), rf.vertex[a][2]/(0.1*rf.diagnal));
			      }
			      gl.glEnd();
				  gl.glPopMatrix();
			  }
//			  gl.glPopMatrix();

		      
		     
		     
		     
				

			 gl.glFlush();
			 
			    



//projection
			 if(window.Smooth.isSelected()){
				gl.glShadeModel(GL2.GL_SMOOTH);
				}
			 if (window.Flat.isSelected()) {
				gl.glShadeModel(GL2.GL_FLAT);
				}
//look at
				System.out.println(window.zoomChange+"------");
				 gl.glMatrixMode(GL2.GL_PROJECTION);
				 gl.glLoadIdentity();
					glu.gluPerspective(50.0f,1,1.0,50.0);
					if (window.zoomChange>500) {
						zoomI=15;

					}else {
						zoomI=window.zoomChange;
					}
					glu.gluLookAt(15+zoomI/(100/3),15+zoomI/(100/3),15+zoomI/(100/3), 0, 0, 0, 0, 1, 0);	 
				 gl.glMatrixMode(GL2.GL_MODELVIEW);
				 gl.glLoadIdentity();

//		
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable drawable)
	  {
		  final GL2 gl = drawable.getGL().getGL2();
	    //
		  float ambient[] =
			    { 0.0f, 0.0f, 0.0f, 1f };
			    float diffuse[] =
			    { 1.0f, 1.0f, 1.0f, 1.0f };
			    float specular[] =
			    { 1.0f, 1.0f, 1.0f, 1.0f };
			    float position[] =
			    { 10.0f, 10.0f, 10.0f, 1.0f };
			    float lmodel_ambient[] =
			    { 0.4f, 0.4f, 0.4f, 1.0f };
		  float local_view[] = { 0.0f };
	    
//////		gl.glShadeModel(GL2.GL_SMOOTH);
//		gl.glShadeModel(GL2.GL_FLAT);
//	    gl.glEnable(GL.GL_DEPTH_TEST);//????????
		gl.glDepthFunc(GL2.GL_LEQUAL);

//		gl.glHint(GL2.GL_ PECTIVE_CORRECTION_HINT,GL2.GL_NICEST);

	    gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, ambient, 0);
	    gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuse, 0);
	    gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, position, 0);
	    gl.glLightModelfv(GL2.GL_LIGHT_MODEL_AMBIENT, lmodel_ambient, 0);
	    gl.glLightModelfv(GL2.GL_LIGHT_MODEL_LOCAL_VIEWER, local_view, 0);

	    gl.glEnable(GL2.GL_LIGHTING);
	    gl.glEnable(GL2.GL_LIGHT0);


	    gl.glClearColor(0.5f, 0.5f, 0.5f, 0.0f);
	  }

	@Override
//	public void reshape(GLAutoDrawable drawable, int arg1, int arg2, int arg3, int arg4) {
	  public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h)
	  {
	
		final GL2 gl = drawable.getGL().getGL2();
	    gl.glViewport(0, 0, w, h);
	    gl.glMatrixMode(GL2.GL_PROJECTION);
	    gl.glLoadIdentity();

		glu.gluPerspective(50.0f,1,1.0,50.0);
//	    gl.glFrustum(-10,10,-10,10,1,10);
//
//	    double range = 10;
//	    if (w <= (h * 2)) //
//	    gl.glOrtho(-range, range, -range/2 * ((float) h * 2) / (float) w, //
//	    		range/2 * ((float) h * 2) / (float) w, -range, range);
//	    else gl.glOrtho(-10.0 * (float) w / ((float) h * 2), //
//	    		range * (float) w / ((float) h * 2), -range/2, range/2, -range, range);

		glu.gluLookAt(15,15,15, 0, 0, 0, 0, 1, 0);

	    gl.glMatrixMode(GL2.GL_MODELVIEW);
	    gl.glLoadIdentity();

	}
	
	public static void main(String[] args) {
		 GLProfile profile = GLProfile.get(GLProfile.GL2);
	      GLCapabilities capabilities = new GLCapabilities(profile);
	      // The canvas 
	       GLCanvas glcanvas = new GLCanvas(capabilities);
	       glcanvas.addGLEventListener(rs);
	       glcanvas.addMouseListener(window);
	       glcanvas.addMouseMotionListener(window);
		   window.initialize(glcanvas);
	       window.Point.addActionListener(rs);
	       window.Wireframe.addActionListener(rs);
	       window.Flat.addActionListener(rs);
	       window.Smooth.addActionListener(rs);
		      rf.file_parser();
		      System.out.println("111111111111111111111111111");
		      rf.vertexMaxMin();
		      System.out.println("2222222222222222222222222222");
		      rf.creatHalfEdge();
		      System.out.println("333333333333333333333333333331");


			for(int v = 1;v<=rf.vertex_num;v++){
			rf.vertexNormal(v);
//		      System.out.println("4444444444444444444444444444");

			rf.vertexNormal[v][0]=rf.vertexNormal[v][0]/(rf.num);
			rf.vertexNormal[v][1]=rf.vertexNormal[v][1]/(rf.num);
			rf.vertexNormal[v][2]=rf.vertexNormal[v][2]/(rf.num);
//			System.out.println("diagnal="+rf.diagnal);
//			System.out.println("算完了的点数："+v+"平均法向量是("+rf.vertexNormal[v][0]+","+rf.vertexNormal[v][1]+","+rf.vertexNormal[v][2]+")");
			}
	      glcanvas.setSize(666,666);


	}

}
