//import statements
import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

/**
 * @author John Snow
 * @version 1.0
 * @since 2015-11-10
 * 
 */

public class SplitPDF {
	//Fields
	File in_file;
	String out_file;
	
	
	public SplitPDF(File input, String output){
		in_file = input;
		out_file = output;
	}
	
	public void Reset(){
		in_file = null;
		out_file = null;
	}
	
	public void Split() throws IOException{
		PDDocument doc = null;
		PDDocument doc2 = null;
		PDDocument out = new PDDocument();
		
		try{
			doc = PDDocument.load(this.in_file);
			doc2 = PDDocument.load(this.in_file);
		} catch (IOException e){
			e.printStackTrace();
		}
		int n = doc.getNumberOfPages();
		//for each page
		PDPage page1 = null;
		PDPage page2 = null;

		PDRectangle rect1 = new PDRectangle();
		PDRectangle rect2 = new PDRectangle();
		
		for(int i=0;i<n;i++){
			page1 = doc.getPage(i);
			page2 = doc2.getPage(i);
			
			rect1.setUpperRightY(page1.getMediaBox().getUpperRightY());
			rect1.setUpperRightX(page1.getMediaBox().getUpperRightX());
			rect1.setLowerLeftX((float) (page1.getMediaBox().getUpperRightX()/2.0));
			rect1.setLowerLeftY(page1.getMediaBox().getLowerLeftY());

			page1.setMediaBox(rect1);
			out.addPage(page1);

			rect2.setUpperRightY(page2.getMediaBox().getUpperRightY());
			rect2.setUpperRightX((float)(page2.getMediaBox().getUpperRightX()/2.0));
			rect2.setLowerLeftX(page2.getMediaBox().getLowerLeftX());
			rect2.setLowerLeftY(page2.getMediaBox().getLowerLeftY());
			
			page2.setMediaBox(rect2);
			out.addPage(page2);
			
		}
		
		out.save(this.out_file);
		doc.close();
		out.close();
	}
	
	public static void main(String[] args) throws IOException {
		String input_path = "/Users/jsnow/Downloads/Paradiso_Sinclair_3_5_6_7.pdf";
		String output_path = "/Users/jsnow/Downloads/new.pdf";
		
		File input;//, output;
		input = new File(input_path);
		//output = new File(output_path);
		
		SplitPDF spdf = new SplitPDF(input, output_path);
		
		spdf.Split();
	}
}
