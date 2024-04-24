package TestsField;
import Core.Field;
import Core.Sprite;
import Core.Window;

public class Mario extends Sprite {

	public Mario(String name, int x1, int y1, int x2, int y2, String path) {
		super(name, x1, y1, x2, y2, path);
		// TODO Auto-generated constructor stub
	}

	int status; // 0 en terra, 1 pujant 2 caient
	int jumpdistance;
	
	
	boolean useArrayImages= false; 
	
	

	public void move(char c) {
		// TODO Auto-generated method stub
		if (c == 'd') {
			x1++;
			x2++;
		} else if (c == 'a') {
			x1--;
			x2--;
		}
	}

	public void fall(Field f) {
		if (status == 1) {
			y1--;
			y2--;
			jumpdistance--;
			if (jumpdistance == 0) {
				status = 2;
			}
		}

		else if (isGrounded(f)) {
			//System.out.println("POF");
			status = 0;
			getGrounded(f);

		} else {
			//System.out.println("ooff");
			status = 2;
			y1+=9;
			y2+=9;
		}

	}

	public void jump() {
		// TODO Auto-generated method stub
		
		if (status == 0) {
			status = 1;
			jumpdistance = 70;
			System.out.println("bump");
			

		}

	}

}
