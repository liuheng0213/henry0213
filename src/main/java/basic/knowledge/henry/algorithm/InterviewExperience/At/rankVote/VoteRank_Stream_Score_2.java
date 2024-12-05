package basic.knowledge.henry.algorithm.InterviewExperience.At.rankVote;

import java.util.*;

// use stream
public class VoteRank_Stream_Score_2 {
    public static void main(String[] args) {
        VoteRank_Stream_Score_2 voteRankStream = new VoteRank_Stream_Score_2();

        List<String> list1 = Arrays.asList("A", "B", "C");
        List<String> list2 = Arrays.asList("B", "A", "C");
        List<String> list3 = Arrays.asList("D", "A", "C");
        List<String> list4 = Arrays.asList("B", "A", "D");
        List<String> list5 = Arrays.asList("B", "A", "D");
        List<String> list6 = Arrays.asList("B", "C", "D");
        List<String> list7 = Arrays.asList("B", "A", "C");
        voteRankStream.rankTeams(list1);
        System.out.println("====================");
        voteRankStream.rankTeams(list2);
        System.out.println("====================");
        voteRankStream.rankTeams(list3);
        System.out.println("====================");
        voteRankStream.rankTeams(list4);
        System.out.println("====================");
        voteRankStream.rankTeams(list5);
        System.out.println("====================");
        voteRankStream.rankTeams(list6);
        System.out.println("====================");
        voteRankStream.rankTeams(list7);
    }

    HashMap<String, Vote> nameToScore = new HashMap<>();
    TreeSet<Vote> votes = new TreeSet<>();
//    HashMap<Integer,Vote> scoreToVotes = new HashMap<>();
    int count = 0;
    private void rankTeams(List<String> list) {

        for(int i =0;i< list.size();i++){
            String name = list.get(i);//o(1)
            Vote vote;
            if(!nameToScore.containsKey(name)){
                vote = new Vote(name,0,count);
            }else{
                vote = nameToScore.get(name);
            }
            votes.remove(vote);//o(logn)
            vote.update(i,count++);
            votes.add(vote);//o(logn)
            nameToScore.put(name,vote);//o(1)
        }


        printRank(4);
    }

    private void printRank(int k) {
        for(Vote v : votes){
            System.out.println(v.name);
            k--;
            if(k == 0){
                return;
            }
        }
    }


    class Vote implements Comparable<Vote>{
        int score;
        String name;
        int sequence;
        public Vote(String name,int score,int sequence) {
            this.score = score;
            this.name = name;
            this.sequence = sequence;

        }


        private void update(int rank,int count){
            if(rank== 0){
                this.score += 3;
            }else if(rank == 1){
                this.score+=2;
            }else{
                this.score++;
            }
            this.sequence = count;
        }

        @Override
        public int compareTo(Vote that) {
            if(that.score != this.score){
                return that.score- this.score;
            }
            return this.sequence - that.sequence;
        }
    }
}