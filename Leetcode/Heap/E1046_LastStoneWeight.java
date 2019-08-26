class Solution {
    public int lastStoneWeight(int[] stones) {
        if(stones.length == 1)
            return stones[0];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int a: stones)
            pq.offer(a*-1);
        while(pq.size() > 1) {
            int s1 = pq.poll()*-1, s2 = pq.poll()*-1;
            // System.out.println("s1: "+s1+", s2: "+s2);
            if(s1 > s2)
                pq.offer((s1-s2)*-1);
            else if(s2 > s1)
                pq.offer((s2-s1)*-1);
        }
        if(pq.size() == 1)
            return pq.poll()*-1;
        else
            return 0;
    }
}
