package defaultz;

public class ProcessInfo {
	
	public int PCPU[] = new int[11];
	public int PIO[] = new int[11];
	public int PCPUBurst =1;
	public int arrivalTime = 0;
	public int PCPULoc = 0;
	public int PIONum;
	public int PIOLoc = 0;
	public int IORemainingTime = 0;
	public int prevTime = 0;
	public String processNum = "Holder";
	public int lastTimeToFinish = 0;
	
	public int responseTime = 0;
	public int waitTime = 0;
	public int turnAroundTime = 0;
	
	public void setProcessNum(String x) {
		processNum = x;
	}
	
	public void setPCPU(int x[]){
		for(int i = 0; i < x.length; i++) {
			PCPU[i]=x[i];
		}
	}
	
	public void setPIO(int x[]) {
		for (int i = 0; i < x.length; i++)
			PIO[i] = x[i];
	}
	
	public void setPCPUBurstAndPIONum() {
		PCPUBurst = PCPU[PCPULoc];
		PIONum = PIO[PIOLoc];
	}

	
	public void printValues(int x[]) {
		for(int i = 0; i < x.length; i++) {
			System.out.print( x[i] + "\t");
		}
		System.out.println("");
	}
	
	public void displayProcessNum() {
		System.out.println(processNum);
	}
	
	 public String toString() {
		 	return "null";
	    }
}
