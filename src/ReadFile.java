import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Spliterator;

import com.jmatio.*;
import com.jmatio.io.MatFileReader;

public class ReadFile {

//	private static Object[] vertex_array;
	public static int vertex_num=0	;
	public static int[][] face=new int[80010][4];//v1 v2 v3 index
	public static int face_num=0;
	public static double[][] vertex=new double[40010][3];//x y z 
	public static HE_edge halfEdge=new HE_edge();//[start point] [end point]
	public static ArrayList<HE_edge>[] orignVertex ;//define by vertices
	public static int edge_num;
	public static double[][] faceNormal	= new double[80010][3];
//	public static double[][] vertexNormal = new double[40010][3];
	public static double[][] vertexNormal=new double[40010][3];

	static double vertexMax_x ;
	static double vertexMin_x ;
	static double vertexMax_y ;
	static double vertexMin_y ;
	static double vertexMax_z ;
	static double vertexMin_z ;
	static double diagnal;
	public static void file_parser(){
	    //read file
	    //parse
	    //store to array
	     String fileName = "E://在NTU//eclipse//MeshViewer//meshes//cap.m";  
	     try {
	            // read file content from file
	            StringBuffer sb= new StringBuffer("");
	            FileReader reader = new FileReader(fileName);
	            BufferedReader br = new BufferedReader(reader);
	            String str = null;  //line_string
	           
	            while((str = br.readLine()) != null) {
	                  sb.append(str+"/n");
//	                  System.out.println(str);
	                  
	                  String arrays[] = str.split(" +");
	                
//	                   for (int i = 0; i < arrays.length; i++){
//	                 System.out.println(" "+ arrays[i]);
//	                     }
	               
	                    
	                 if(arrays[0].equals("Vertex")){
	                  vertex[Integer.parseInt(arrays[1])][0] = Double.parseDouble(arrays[2]);
	                  
	                  vertex[Integer.parseInt(arrays[1])][1] = Double.parseDouble(arrays[3]);
	                  vertex[Integer.parseInt(arrays[1])][2] = Double.parseDouble(arrays[4]);
//	                  System.out.println(vertex);
	                  vertex_num++;
	                  } else if (arrays[0].equals("Face")){

	                  face[Integer.parseInt(arrays[1])][0] = Integer.parseInt(arrays[2]);
	                  face[Integer.parseInt(arrays[1])][1] = Integer.parseInt(arrays[3]);
	                  face[Integer.parseInt(arrays[1])][2] = Integer.parseInt(arrays[4]);
	                  face[Integer.parseInt(arrays[1])][3] = Integer.parseInt(arrays[1]);
	                  face_num++;
	                  }        
	               
	            }
	            br.close();
	            reader.close();      
	      }
	      catch(FileNotFoundException e) {
	                  e.printStackTrace();
	            }
	            catch(IOException e) {
	                  e.printStackTrace();
	            }
	      }
	
	static HE_edge[] edgeArray = new HE_edge[240000];//
	static HE_face[] faceArray = new HE_face[80010];
	static HE_vert[] vertArray = new HE_vert[240030];
	
//	add edge
	static void creatHalfEdge(){
		int i = 0;
//		int i+n;
//		edge = new HE_edge[vertex_num][vertex_num];
		for(i=0;i<face_num;i++){
//			System.out.println("work on face"+i);
			faceArray[i] = new HE_face();
			for(int n =0;n<3;n++){
				vertArray[i+n]=new HE_vert();
			if(n==0){
			int startPoint = face[i][0];
			int endPoint = face[i][1];
			vertArray[i+n] = new HE_vert();
			vertArray[i+n].currVert=startPoint;
			vertArray[i+n].x = vertex[startPoint][0];
			vertArray[i+n].y = vertex[startPoint][1];
			vertArray[i+n].z = vertex[startPoint][2];
			edgeArray[i+n] = new HE_edge() ;
			edgeArray[i+n].startPoint =vertArray[i+n];
			edgeArray[i+n].endPoint = endPoint;
			edgeArray[i+n].incidentFace = faceArray[i];
			vertArray[i+n].edge=edgeArray[i+n];//应该不对
			faceArray[i].Edges=edgeArray[i];
			}
			
			if (n==1) {
			int startPoint2 = face[i][1];
			int endPoint2 = face[i][2];
			vertArray[i+n] = new HE_vert();
			vertArray[i+n].currVert=startPoint2;
			vertArray[i+n].x = vertex[startPoint2][0];
			vertArray[i+n].y = vertex[startPoint2][1];
			vertArray[i+n].z = vertex[startPoint2][2];
			edgeArray[i+n] = new HE_edge() ;
			edgeArray[i+n].startPoint =vertArray[i+n];
			edgeArray[i+n].endPoint = endPoint2;
//			orignVertex[startPoint2][endPoint2]=edgeArray[i+n];
			edgeArray[i+n].incidentFace = faceArray[i];
			vertArray[i+n].edge=edgeArray[i+n];

			}
			
			if (n==2) {
			int startPoint3 = face[i][2];
			int endPoint3 = face[i][0];
			vertArray[i+n] = new HE_vert();
			vertArray[i+n].currVert=startPoint3;
			vertArray[i+n].x = vertex[startPoint3][0];
			vertArray[i+n].y = vertex[startPoint3][1];
			vertArray[i+n].z = vertex[startPoint3][2];
			edgeArray[i+n] = new HE_edge() ;
			edgeArray[i+n].startPoint =vertArray[i+n];
			edgeArray[i+n].endPoint = endPoint3;
			edgeArray[i+n].incidentFace = faceArray[i];
			vertArray[i+n].edge=edgeArray[i+n];

			}
			

			}
			
			edgeArray[i].prevEdge=edgeArray[i+2];
			edgeArray[i].nextEdge=edgeArray[i+1];
			
			edgeArray[i+1].prevEdge = edgeArray[i];
			edgeArray[i+1].nextEdge = edgeArray[i+3];
			
			edgeArray[i+2].prevEdge = edgeArray[i+1];
			edgeArray[i+2].nextEdge = edgeArray[i];
			
//			double ABx = vertex[face[i][1]][0]/(0.1*diagnal)-vertex[face[i][0]][0]/(0.1*diagnal);//b1-a1
//			double ABy = vertex[face[i][1]][1]/(0.1*diagnal)-vertex[face[i][0]][1]/(0.1*diagnal);//b2-a2
//			double ABz = vertex[face[i][1]][2]/(0.1*diagnal)-vertex[face[i][0]][2]/(0.1*diagnal);//b3-a3
//					
//			double BCx = vertex[face[i][2]][0]/(0.1*diagnal)-vertex[face[i][1]][0]/(0.1*diagnal);//c1-b1
//			double BCy = vertex[face[i][2]][1]/(0.1*diagnal)-vertex[face[i][1]][1]/(0.1*diagnal);//c2-b2
//			double BCz = vertex[face[i][2]][2]/(0.1*diagnal)-vertex[face[i][1]][2]/(0.1*diagnal);//c3-b3
			
			double ABx = vertex[face[i][1]][0] -vertex[face[i][0]][0];//b1-a1
			double ABy = vertex[face[i][1]][1]-vertex[face[i][0]][1];//b2-a2
			double ABz = vertex[face[i][1]][2]-vertex[face[i][0]][2];//b3-a3
					
			double BCx = vertex[face[i][2]][0]-vertex[face[i][1]][0];//c1-b1
			double BCy = vertex[face[i][2]][1]-vertex[face[i][1]][1];//c2-b2
			double BCz = vertex[face[i][2]][2]-vertex[face[i][1]][2];//c3-b3

			
			faceNormal[i][0] = ABy*BCz;
//			System.out.println("faceNormal"+faceNormal[i][0]);
			faceNormal[i][1] = ABz*BCx;
//			System.out.println(faceNormal[i][1]);
			faceNormal[i][2] = ABx*BCy;
//			System.out.println(faceNormal[i][2]);

			}
		
		int faceNormalNo = faceNormal.length;
		edge_num = edgeArray.length;
//		System.out.println(edge);
//		System.out.println("faceNormalNo="+faceNormalNo);


//		System.out.println("edgeNumber="+edge_num);
		
	}
	static int num;

	
	/*main 方法内包括了点的平均法向量的计算，待改造*/	

//	public static void main(String[] args) throws IOException, IOException {
//		// TODO Auto-generated method stub
//		file_parser();
//		creatHalfEdge();
//		for(int v = 1;v<=vertex_num;v++){
//		vertexNormal(v);
//		vertexNormal[v][0]=vertexNormal[v][0]/num;
//		vertexNormal[v][1]=vertexNormal[v][1]/num;
//		vertexNormal[v][2]=vertexNormal[v][2]/num;
//
//		System.out.println("算完了的点数："+v+"平均法向量是("+vertexNormal[v][0]+","+vertexNormal[v][1]+","+vertexNormal[v][2]+")");
//
//		}
//
//	}

	static void vertexNormal(int v){
		 num=0;
			for (int[] f : face) {   
	        for (int y : f) {
	        	if (y==v) {
//	        		System.out.println(y);
//	        		System.out.println("f[0]="+f[0]);
//	        		System.out.println("f[1]="+f[1]);
//	        		System.out.println("f[2]="+f[2]);
//	        		System.out.println("f[3]="+f[3]);

//	        		faceNormal[f[0]
	        		vertexNormal[v][0]+=faceNormal[f[3]][0];//第【】个点的x
	        		vertexNormal[v][1]+=faceNormal[f[3]][1];//第【】个点的x
	        		vertexNormal[v][2]+=faceNormal[f[3]][2];//第【】个点的x

	        		System.out.println(vertexNormal[y][0]);
	        		num++;
//	        		System.out.println("num====="+num);
	        		//第f[3]个face的法向量的x
//	        		faceNormal[f[3]][0];
//	        		faceNormal[f[3]][1];//第f[3]个face的法向量的y
//	        		faceNormal[f[3]][2];//第f[3]个face的法向量的z

//	        		System.out.println(v+"点相邻的面"+vertexNormal[v].length);
	        		}}}
	}


	static void vertexMaxMin(){
		 vertexMax_x = vertex[1][0];
		 vertexMin_x = vertex[2][0];
		 vertexMax_y = vertex[1][1] ;
		 vertexMin_y = vertex[1][1];
		 vertexMax_z = vertex[1][2];
		 vertexMin_z = vertex[1][2];
		for(int m =1;m<=vertex_num;m++){

		if(vertex[m][0]>vertexMax_x){
			 vertexMax_x=vertex[m][0];
		}
		if(vertex[m][0]<vertexMin_x){
			vertexMin_x=vertex[m][0];

		}
		
		if(vertex[m][1]>vertexMax_y){
			 vertexMax_y=vertex[m][1];
		}
		
		if(vertex[m][1]<vertexMin_y){
			vertexMin_y=vertex[m][1];
		      System.out.println(m);

		}
		if(vertex[m][2]>vertexMax_z){
			 vertexMax_z=vertex[m][2];
		}
		
		if(vertex[m][2]<vertexMin_z){
			vertexMin_z=vertex[m][2];
		}
		}
		
		diagnal = Math.sqrt(Math.pow((vertexMax_x-vertexMin_x),2)+Math.pow((vertexMax_y-vertexMin_y),2)+Math.pow((vertexMax_z-vertexMin_z),2));
		System.out.println("vertexMax_x="+vertexMax_x);
		System.out.println("vertexMin_x="+vertexMin_x);
		System.out.println("vertexMax_y="+vertexMax_y);
		System.out.println("vertexMin_y="+vertexMin_y);
		System.out.println("vertexMax_z="+vertexMax_z);
		System.out.println("vertexMin_z="+vertexMin_z);
		System.out.println("diagnal="+diagnal);

		

	}
}
