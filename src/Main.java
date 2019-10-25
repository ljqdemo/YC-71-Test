import java.util.Scanner;
import java.util.Arrays;
 
public class Main {
	// �ӿ�Comparable���෽��Arrays.sort()�����ʹ�ÿ���ʹ���̰�reachTime(����ʱ��)����
    private static class PCB implements Comparable<PCB> {
	    String id;
		float reachTime;
		float needTime;
		float startTime;
		float finishTime;
		char state;
		//���յ���ʱ�������ԭ��
		public int compareTo( PCB b ) {
			if( reachTime==b.reachTime ) return 0;
			if( reachTime<b.reachTime ) return -1;
			return 1;
		}
	}
	private static final float INF = 10000000.0f;
	/* ���������
       1.��lastTimeʱ�̣�ѡ���Ѿ�������ӵ���������ʱ��Ľ���
       2.��lastTimeʱ�̣�û�н��̵����ʱѡ��ӵ�����絽��ʱ��Ľ���
    */
	private static int findNext( PCB[] arr, float lastTime ) {
	    int i, p = -1;
		float minNeedTime = INF;
		for( i = 0; i < arr.length; i++ ) {
		    if( arr[i].state=='R' ) {
			    /* ����arr�Ѿ���reachTime����,������
			       arr[i].reachTime>lastTimeʱ,˵����lastTimeʱ���޽��̵���,��ֹѭ��.
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
		
		System.out.print( "�����������:" );
		int num = sc.nextInt();
		PCB[] arr = new PCB[num];
		System.out.println( "�������������ID,���̵���ʱ��,��������ʱ��:" );
		for( int i = 0; i < num; i++ ) {
		    arr[i] = new PCB();
			arr[i].id = sc.next();
			arr[i].reachTime = sc.nextFloat();
			arr[i].needTime = sc.nextFloat();
			arr[i].state = 'R';
		}
		Arrays.sort(arr); // ʹ���̰�reachTime(����ʱ��)����
		//����һ������Ľ��̵ĵ���ʱ��Ϊ��ǰʱ��
		float lastTime=arr[0].reachTime;
		for( int i=0; i<num; i++ ) {
		    // �ҵ���һ����Ҫִ�еĽ���
		    int p = findNext( arr, lastTime );
			if( arr[p].reachTime<=lastTime ) arr[p].startTime = lastTime;
			else arr[p].startTime = arr[p].reachTime;
			arr[p].finishTime = arr[p].startTime + arr[p].needTime;
			arr[p].state = 'F';
			
			lastTime = arr[p].finishTime;  // ����lastTime
		}
		
		float sum1=0.0f, sum2=0.0f;
		System.out.println( "\n����         ����ʱ��        ����ʱ��      ��ʼʱ��      ���ʱ��      ��תʱ��      ��Ȩ��תʱ��" );
		for( PCB jcb : arr ) {
			System.out.format( "%4s  %8.2f  %8.2f  ", jcb.id, jcb.reachTime, jcb.needTime );
		    System.out.format( "%8.2f  %8.2f  ", jcb.startTime, jcb.finishTime );
			System.out.format( "%8.2f  ", jcb.finishTime-jcb.reachTime );
			System.out.format( "%12.2f\n", (jcb.finishTime-jcb.reachTime)/jcb.needTime );
			sum1 += jcb.finishTime-jcb.reachTime;
			sum2 += (jcb.finishTime-jcb.reachTime)/jcb.needTime;
		}
		System.out.format( "ƽ����תʱ��: %.3f\n", (sum1/num) );
		System.out.format( "ƽ����Ȩ��תʱ��: %.3f", (sum2/num) );
	}
}
