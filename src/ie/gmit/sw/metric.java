package ie.gmit.sw;

public class metric {
	private int inDegree;
    private int outDegree;
    private String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getInDegree() {
		return inDegree;
	}
	public void setInDegree(int inDegree) {
		this.inDegree = inDegree;
	}
	public int getOutDegree() {
		return outDegree;
	}
	public void setOutDegree(int outDegree) {
		this.outDegree = outDegree;
	}
	public double getStability(){

        double stability = 0.0;

        if(getOutDegree() > 0){
        	
        	stability  = ((double) getOutDegree() / ((double)getInDegree() +(double)getOutDegree()));
        }

        return stability;
    }
	
    
}
