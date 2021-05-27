import java.io.*;
import java.util.*;

public class MosaicProject{

    public static void main(String[] args) {	
        String file=args[1];
        Mosaic mosaic=null;	
        // RectangularRegion region=null;
        Scanner input=null;
        int value;
        int status;
        int row;
        int column;
        String regionname=null;
	    try {
		    input = new Scanner (new FileInputStream(file)) ;
        }
	    catch (FileNotFoundException e) {
	    	System.out.println("Instruction file open error");
    		System.exit(-1);
        }   
        String line=null;
        while(input.hasNextLine()){
            line=input.nextLine();
            System.out.println(line);
            String inst=line.substring(0, line.indexOf(" "));
            System.out.println(inst);
            String argument=line.substring(line.indexOf(" ")+1,line.length());
            System.out.println(argument);

            switch (inst) {
                case "ReadMosaic":
                    mosaic=new Mosaic(argument);
                    break;
                case "CreateRegion":
                    String name=argument.substring(0,argument.indexOf(","));
                    int coma1=argument.indexOf(",", argument.indexOf(",")+1);
                    int orow=Integer.parseInt(argument.substring(argument.indexOf(","), coma1));
                    int coma2=argument.indexOf(",",coma1+1);
                    int ocol=Integer.parseInt(argument.substring(coma1+1, coma2));
                    int coma3=argument.indexOf(",",coma2+1);
                    int heigh=Integer.parseInt(argument.substring(coma2+1, coma3));
                    int width=Integer.parseInt(argument.substring(coma3+1,argument.length()));
                    System.out.println(name+" "+orow+" "+ocol+" "+heigh+" "+width);
                    new RectangularRegion(mosaic, name, orow, ocol, heigh, width);

                    break;
                case "ChangeLuminosityMosaic":
                    value=Integer.parseInt(argument);
                    if(mosaic!=null)mosaic.changeLuminosity(value);
                    break;
                case "ChangeLuminosityRegion":
                    value=Integer.parseInt(argument.substring(0,argument.indexOf(",")));
                    regionname=argument.substring(argument.indexOf(",")+1,argument.length());
                    if(mosaic!=null)mosaic.getRegion(regionname).changeLuminosity(value);
                    break;
                case "ChangeLuminosityTile":
                    value=Integer.parseInt(argument.substring(0,argument.indexOf(",")));
                    row=Integer.parseInt(argument.substring(argument.indexOf(",")+1,argument.indexOf(",",argument.indexOf(",")+1)));
                    column=Integer.parseInt(argument.substring(argument.indexOf(",",argument.indexOf(",")+1), argument.length()));
                    System.out.println(row+""+column);
                    if(mosaic!=null)mosaic.getTile(new Coordinate(row, column)).changeLuminosity(value);
                    break;
                case "ChangeStatusMosaic":
                    if(mosaic!=null)mosaic.changeStatus(Integer.parseInt(argument));
                    break;
                case "ChangeStatusRegion":
                    status=Integer.parseInt(argument.substring(0,argument.indexOf(",")));
                    regionname=argument.substring(argument.indexOf(",")+1,argument.length());
                    if(mosaic!=null)mosaic.getRegion(regionname).changeStatus(status);
                    break;
                case "ChangeStatusTile":
                    status=Integer.parseInt(argument.substring(0,argument.indexOf(",")));
                    row=Integer.parseInt(argument.substring(argument.indexOf(",")+1,argument.indexOf(",",argument.indexOf(",")+1)));
                    column=Integer.parseInt(argument.substring(argument.indexOf(",",argument.indexOf(",")+1),argument.length()));
                    if(mosaic!=null)mosaic.getTile(new Coordinate(row, column)).setStatus(status);
                    break;
                case "SortRegionsByArea":
                if(mosaic!=null)mosaic.sortRegionsByAreaAsc();
                    break;
                case "SaveMosaic":

                    break;
                default:
                    break;
            }
        }

        input.close();
        }
}
