import java.util.Scanner;
import java.util.Arrays;
 
public class Main {
	// 接口Comparable和类方法Arrays.sort()的配合使用可以使进程按reachTime(到达时间)排序
    private static class PCB implements Comparable<PCB> {
	    String id;
		float reachTime;
		float needTime;
		float startTime;
		float finishTime;
		char state;
		//按照到达时间排序的原理
		public int compareTo( PCB b ) {
			if( reachTime==b.reachTime ) return 0;
			if( reachTime<b.reachTime ) return -1;
			return 1;
		}
	}
	private static final float INF = 10000000.0f;
	/* 两种情况：
       1.在lastTime时刻，选择已经到达且拥有最短运行时间的进程
       2.在lastTime时刻，没有进程到达，此时选择拥有最早到达时间的进程
    */
	private static int findNext( PCB[] arr, float lastTime ) {
	    int i, p = -1;
		float minNeedTime = INF;
		for( i = 0; i < arr.length; i++ ) {
		    if( arr[i].state=='R' ) {
			    /* 数组arr已经按reachTime排序,当出现
			       arr[i].reachTime>lastTime时,说明在lastTime时刻无进程到达,终止循环.
			     */
			    if( arr[i].reachTime > lastTime ) break;
				if( arr[i].needTime < minNeedTime )
				  { p = i; minNeedTime = arr[i].needTime; }
			}
		}
		if( p != -1 ) return p;
		return i;
	}
	
    public static void main( String[] args ) {
	    Scanner sc = new Scanner( System.in );
		
		System.out.print( "请输入进程数:" );
		int num = sc.nextInt();
		PCB[] arr = new PCB[num];
		System.out.println( "请依次输入进程ID,进程到达时间,进程运行时间:" );
		for( int i = 0; i < num; i++ ) {
		    arr[i] = new PCB();
			arr[i].id = sc.next();
			arr[i].reachTime = sc.nextFloat();
			arr[i].needTime = sc.nextFloat();
			arr[i].state = 'R';
		}
		Arrays.sort(arr); // 使进程按reachTime(到达时间)排序
		//将第一个到达的进程的到达时间为当前时间
		float lastTime=arr[0].reachTime;
		for( int i=0; i<num; i++ ) {
		    // 找到下一个将要执行的进程
		    int p = findNext( arr, lastTime );
			if( arr[p].reachTime<=lastTime ) arr[p].startTime = lastTime;
			else arr[p].startTime = arr[p].reachTime;
			arr[p].finishTime = arr[p].startTime + arr[p].needTime;
			arr[p].state = 'F';
			
			lastTime = arr[p].finishTime;  // 更新lastTime
		}
		
		float sum1=0.0f, sum2=0.0f;
		System.out.println( "\n进程         到达时间        运行时间      开始时间      完成时间      周转时间      带权周转时间" );
		for( PCB jcb : arr ) {
			System.out.format( "%4s  %8.2f  %8.2f  ", jcb.id, jcb.reachTime, jcb.needTime );
		    System.out.format( "%8.2f  %8.2f  ", jcb.startTime, jcb.finishTime );
			System.out.format( "%8.2f  ", jcb.finishTime-jcb.reachTime );
			System.out.format( "%12.2f\n", (jcb.finishTime-jcb.reachTime)/jcb.needTime );
			sum1 += jcb.finishTime-jcb.reachTime;
			sum2 += (jcb.finishTime-jcb.reachTime)/jcb.needTime;
		}
		System.out.format( "平均周转时间: %.3f\n", (sum1/num) );
		System.out.format( "平均带权周转时间: %.3f", (sum2/num) );
	}
}
