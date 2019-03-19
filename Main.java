package defaultz;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	static int currentTime = 0;
	
	static int Idle = 0;
	static int avgResponseTime;
	static int avgWaitingTime;
	static int avgTurnAroundTime;

	static ProcessInfo p1 = new ProcessInfo();
	static ProcessInfo p2 = new ProcessInfo();
	static ProcessInfo p3 = new ProcessInfo();
	static ProcessInfo p4 = new ProcessInfo();
	static ProcessInfo p5 = new ProcessInfo();
	static ProcessInfo p6 = new ProcessInfo();
	static ProcessInfo p7 = new ProcessInfo();
	static ProcessInfo p8 = new ProcessInfo();
	static ProcessInfo p9 = new ProcessInfo();
	
	
	static List<ProcessInfo> queue = new ArrayList<ProcessInfo>();
	static List<ProcessInfo> active = new ArrayList<ProcessInfo>();
	static List<String> completedList = new ArrayList<String>();
	static List<ProcessInfo> io = new ArrayList<ProcessInfo>();

	public static void main(String[] args) {

		int p1CPU[] = { 4, 3, 2, 4, 4, 4, 3, 4 };
		int p1IO[] = { 27, 31, 43, 18, 22, 26, 24 };
		int p2CPU[] = { 16, 17, 5, 16, 7, 13, 11, 6, 3, 4 };
		int p2IO[] = { 24, 21, 36, 26, 31, 28, 21, 13, 11 };
		int p3CPU[] = { 8, 12, 18, 14, 4, 15, 14, 5, 6 };
		int p3IO[] = { 33, 41, 65, 21, 61, 18, 26, 31 };
		int p4CPU[] = { 3, 4, 4, 3, 4, 3, 6, 5, 3 };
		int p4IO[] = { 35, 41, 45, 51, 61, 54, 82, 77 };
		int p5CPU[] = { 4, 5, 7, 12, 9, 4, 9, 7, 8 };
		int p5IO[] = { 48, 44, 42, 37, 46, 41, 31, 43 };
		int p6CPU[] = { 11, 4, 5, 6, 7, 9, 12, 15, 8 };
		int p6IO[] = { 22, 8, 10, 12, 14, 18, 24, 30 };
		int p7CPU[] = { 14, 17, 11, 15, 4, 7, 16, 10 };
		int p7IO[] = { 46, 41, 42, 21, 32, 19, 33 };
		int p8CPU[] = { 4, 5, 6, 14, 16, 6, 7 };
		int p8IO[] = { 14, 33, 51, 63, 87, 74 };
		int p9CPU[] = { 3, 9, 8, 4, 7, 5, 4, 5, 16 };
		int p9IO[] = { 37, 41, 30, 29, 33, 22, 24, 29 };

		p1.setPCPU(p1CPU);
		p2.setPCPU(p2CPU);
		p3.setPCPU(p3CPU);
		p4.setPCPU(p4CPU);
		p5.setPCPU(p5CPU);
		p6.setPCPU(p6CPU);
		p7.setPCPU(p7CPU);
		p8.setPCPU(p8CPU);
		p9.setPCPU(p9CPU);

		p1.setPIO(p1IO);
		p2.setPIO(p2IO);
		p3.setPIO(p3IO);
		p4.setPIO(p4IO);
		p5.setPIO(p5IO);
		p6.setPIO(p6IO);
		p7.setPIO(p7IO);
		p8.setPIO(p8IO);
		p9.setPIO(p9IO);

		p1.setProcessNum("P1");
		p2.setProcessNum("P2");
		p3.setProcessNum("P3");
		p4.setProcessNum("P4");
		p5.setProcessNum("P5");
		p6.setProcessNum("P6");
		p7.setProcessNum("P7");
		p8.setProcessNum("P8");
		p9.setProcessNum("P9");

		p1.setPCPUBurstAndPIONum();
		p2.setPCPUBurstAndPIONum();
		p3.setPCPUBurstAndPIONum();
		p4.setPCPUBurstAndPIONum();
		p5.setPCPUBurstAndPIONum();
		p6.setPCPUBurstAndPIONum();
		p7.setPCPUBurstAndPIONum();
		p8.setPCPUBurstAndPIONum();
		p9.setPCPUBurstAndPIONum();
		
		active.add(p1);
		active.add(p2);
		active.add(p3);
		active.add(p4);
		active.add(p5);
		active.add(p6);
		active.add(p7);
		active.add(p8);
		active.add(p9);
		
		queue.add(p1);
		queue.add(p2);
		queue.add(p3);
		queue.add(p4);
		queue.add(p5);
		queue.add(p6);
		queue.add(p7);
		queue.add(p8);
		queue.add(p9);

		while (currentTime < 700) {
			SetCPUIO();
			setCPUburst();
			ProcessInfo Temp = whichProcessNext();
			Display(Temp);
			updateInfo(Temp);
		

		}
		
		getAvgResponseTime();
		getAvgWaitTime();
		getAvgTurnAroundTime();
		
		
	}

	public static void Display(ProcessInfo Temp2) {
		System.out.println("\n\nCurrent Time: " + currentTime + "\n");
		if(Idle == 0) {
		System.out.print("Next Process on the CPU:: " + Temp2.processNum);
		}
		else {
		System.out.print("Next Process on the CPU:: " + "[Idle] \n");
		}
		System.out.println("\n..................................\n");
		System.out.println("List of processes in the ready queue:\n");
		System.out.println("\tProcess   Burst\n");
		for(int i = 1; i < queue.size();i++) {
			if(queue.get(i).arrivalTime <= currentTime) {
				System.out.println("\t" + queue.get(i).processNum + "\t  " + queue.get(i).PCPUBurst);
			}
		}
		System.out.println("\n");
		System.out.println("\n..................................\n");
		System.out.println("List of processes in I/O:\n");
		System.out.println("\tProcess   Remaining I/O time\n");
		
		for(int i =0; i < io.size();i++) {
			if(io.get(i).arrivalTime > currentTime){
				System.out.println("\t" + io.get(i).processNum + "\t" + io.get(i).IORemainingTime);
			}
		}
		System.out.println("\n..................................\n");
		System.out.print("Completed: ");
		for(int i = 0; i < completedList.size(); i++) {
			System.out.print(completedList.get(i) + ", ");
		}
		System.out.println("\n..................................");
		System.out.println("..................................\n");

	}

	public static void setCPUburst() {
		p1.PCPUBurst = p1.PCPU[p1.PCPULoc];
		p2.PCPUBurst = p2.PCPU[p2.PCPULoc];
		p3.PCPUBurst = p3.PCPU[p3.PCPULoc];
		p4.PCPUBurst = p4.PCPU[p4.PCPULoc];
		p5.PCPUBurst = p5.PCPU[p5.PCPULoc];
		p6.PCPUBurst = p6.PCPU[p6.PCPULoc];
		p7.PCPUBurst = p7.PCPU[p7.PCPULoc];
		p8.PCPUBurst = p8.PCPU[p8.PCPULoc];
		p9.PCPUBurst = p9.PCPU[p9.PCPULoc];
	}

	public static void SetCPUIO() {
		p1.PIONum = p1.PIO[p1.PIOLoc];
		p2.PIONum = p2.PIO[p2.PIOLoc];
		p3.PIONum = p3.PIO[p3.PIOLoc];
		p4.PIONum = p4.PIO[p4.PIOLoc];
		p5.PIONum = p5.PIO[p5.PIOLoc];
		p6.PIONum = p6.PIO[p6.PIOLoc];
		p7.PIONum = p7.PIO[p7.PIOLoc];
		p8.PIONum = p8.PIO[p8.PIOLoc];
		p9.PIONum = p9.PIO[p9.PIOLoc];

	}

	public static ProcessInfo whichProcessNext() {

		int x = 1000;
		ProcessInfo k = new ProcessInfo();

		for (int i = 0; i < queue.size(); i++) {
			if(queue.get(i).PCPUBurst == 0) {
				
			}
			else if (queue.get(i).arrivalTime < x) {
				x = queue.get(i).arrivalTime;
				k = queue.get(i);
				//System.out.println(x);
				//System.out.println(k);
			}
		}
		
		return k;
	}

	public static void updateInfo(ProcessInfo x) {
		
		if (x.arrivalTime > currentTime) {
			currentTime++;
			Idle = 1;
			
			for (int u =0; u < io.size(); u++){
				io.get(u).IORemainingTime--; 
			}
				
			for(int q = 0; q < queue.size();q++) {
				if(queue.get(q).PCPUBurst == 0) {
					completedList.add(queue.get(q).processNum);
					queue.remove(queue.get(q));
				}
				}
					
			for(int k = 0; k < io.size(); k++) {
				if(io.get(k).IORemainingTime <= 0) {
					io.remove(k);
						}
					}
				
			}
		else {
		
		Idle = 0;
		x.prevTime = currentTime;
		currentTime += x.PCPUBurst;
		x.arrivalTime = currentTime + x.PIONum;
		x.PCPULoc++;
		x.PIOLoc++;
		
		
		
		if(x.responseTime == 0 && x.PIOLoc == 1) {
			x.responseTime = (x.prevTime - 0);
		}
//		turn around time 
//        = waiting_time + burst_time for all processes
//Average waiting time = 
//                  total_waiting_time / no_of_processes
//Average turn around time = 
//               total_turn_around_time / no_of_processes
		
		
		for (int i =0; i < io.size() - 1; i++){
			io.get(i).IORemainingTime -= x.PCPUBurst; 
		}

		x.setPCPUBurstAndPIONum();
		Collections.sort(queue,(a,b) -> a.arrivalTime - b.arrivalTime);
		
		for(int i = 0; i < io.size(); i++) {
			if(io.get(i).IORemainingTime <= 0) {
				io.remove(i);
			}
		}
		
		for(int i = 0; i < queue.size();i++) {
			if(queue.get(i).PCPUBurst == 0) {
				queue.get(i).turnAroundTime = queue.get(i).arrivalTime - queue.get(i).responseTime;
				completedList.add(queue.get(i).processNum);
				queue.remove(queue.get(i));
			}
		}
		}	
		}
	
	public static void getAvgResponseTime() {
		for(int i = 0;i < active.size();i++) {
			
			avgResponseTime = active.get(i).responseTime + avgResponseTime;
			System.out.println(active.get(i).processNum + " response time is " + active.get(i).responseTime);
			 
		}
		
		System.out.println("Average reponse time is:  " + (avgResponseTime/active.size()) + "\n\n");
		avgResponseTime = 0;
	}
	
	//This function is not entirely correct
	public static void getAvgWaitTime() {
		for(int i = 0;i < active.size();i++) {
			
			avgResponseTime = active.get(i).responseTime + avgResponseTime;
			System.out.println(active.get(i).processNum + " response time is " + active.get(i).responseTime);
			 
		}
		
		System.out.println("Average wait time is:  " + (avgResponseTime/active.size()) + "\n\n");
	}
	
	public static void getAvgTurnAroundTime() {
		for(int i = 0;i < active.size();i++) {
			
			avgTurnAroundTime = active.get(i).turnAroundTime + avgTurnAroundTime;
			System.out.println(active.get(i).processNum + " Turnaround time is " + active.get(i).turnAroundTime);
			 
		}
		
		System.out.println("Average wait time is:  " + (avgTurnAroundTime/active.size()) + "\n\n");
	}
}
