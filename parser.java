  public static void file_parser(){
    //read file
    //parse
    //store to array
     String fileName = "C:\\Users\\zwm19\\Desktop\\meshes\\meshes\\cap.m";  
     try {
            // read file content from file
            StringBuffer sb= new StringBuffer("");
            FileReader reader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(reader);
            String str = null;  //line_string
           
            while((str = br.readLine()) != null) {
                  sb.append(str+"/n");
                //  System.out.println(str);
                  
                  String arrays[] = str.split(" +");
                
//                   for (int i = 0; i < arrays.length; i++){
//                 System.out.println(" "+ arrays[i]);
//                     }
               
                    
                 if(arrays[0].equals("Vertex")){
                  //store to vertex array/ struct
                  vertex_array[Integer.parseInt(arrays[1])] = new HE_vert();
                  vertex_array[Integer.parseInt(arrays[1])].x = Double.parseDouble(arrays[2]);
                  vertex_array[Integer.parseInt(arrays[1])].y = Double.parseDouble(arrays[3]);
                  vertex_array[Integer.parseInt(arrays[1])].z = Double.parseDouble(arrays[4]);
//                  vertex[Integer.parseInt(arrays[1])][0] = Double.parseDouble(arrays[2]);
//                  vertex[Integer.parseInt(arrays[1])][1] = Double.parseDouble(arrays[3]);
//                  vertex[Integer.parseInt(arrays[1])][2] = Double.parseDouble(arrays[4]);
                  vertex_num++;
                  } else if (arrays[0].equals("Face")){
                   //store to face array/ struct

                  face[Integer.parseInt(arrays[1])][0] = Integer.parseInt(arrays[2]);
                  face[Integer.parseInt(arrays[1])][1] = Integer.parseInt(arrays[3]);
                  face[Integer.parseInt(arrays[1])][2] = Integer.parseInt(arrays[4]);
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
      }//FILE_PARSER