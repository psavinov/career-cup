import java.util.Arrays;

/**
 * Google in-person interview task.
 * 
 * There are at most eight servers in a data center. 
 * Each server has got a capacity/memory limit. 
 * There can be at most 8 tasks that need to be scheduled on those servers. 
 * Each task requires certain capacity/memory to run, and each server can 
 * handle multiple tasks as long as the capacity limit is not hit. 
 * 
 * Write a program to see if all of the given tasks 
 * can be scheduled or not on the servers? 
 * 
 * Ex: 
 * Servers capacity limits: 8, 16, 8, 32 
 * Tasks capacity needs: 18, 4, 8, 4, 6, 6, 8, 8 
 * For this example, the program should say 'true'. 
 * 
 * Ex2: 
 * Server capacity limits: 1, 3 
 * Task capacity needs: 4 
 * For this example, program should return false. 
 * 
 * @author Pavel Savinov // savinovpa@gmail.com
 *
 */
public class Capacity {
	
	public static void main(String[] args) {
		
		Capacity exampleOne = new Capacity(new int[]{8,16,8,32}, new int[]{18,4,8,4,6,6,8,8});
		Capacity exampleTwo = new Capacity(new int[]{1,3}, new int[]{4});
		
		System.out.println(exampleOne.canHandle());
		System.out.println(exampleTwo.canHandle());
		
	}
	
	public boolean canHandle() {
		
		int[] tasks = getTasks();
		int[] servers = getServers();
		
		for (int t=0; t<tasks.length; t++) {
			boolean handle = false;
			
			for (int s=0; s<servers.length; s++) {
				if(tasks[t] <= servers[s]) {
					handle = true;
					servers[s] -= tasks[t];
					break;
				}
			}
				
			if (!handle) {
				return false;
			}			
		}
		
		return true;
	}

	private int[] servers;
	private int[] tasks;
	
	public Capacity(int[] serversIn, int[] tasksIn) {
		setServers(serversIn);
		setTasks(tasksIn);
	}

	private int[] getServers() {
		return servers;
	}

	private void setServers(int[] array) {
				
		this.servers = sort(array);
	}

	private int[] getTasks() {
		return tasks;
	}

	private void setTasks(int[] array) {
		
		this.tasks = sort(array);
	}
	
	private static int[] sort(int[] array) {
		Arrays.sort(array);
		int[] out = new int[array.length];
		
		for (int q=array.length-1, k=0; k<array.length; k++, q--) {
			out[k] = array[q];
		}
		
		return out;
	}
	
}
