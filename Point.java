public class Point{
	private int x;
	private int y;

	public Point(int a,int b){
		x=a;
		y=b;
	}

	public void setX(int a){
		x=a;
	}

	public void setY(int a){
		y=a;
	}

	public void setPosition(int a, int b){
		setX(a);
		setY(b);
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}
	
}
