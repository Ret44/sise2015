package Game;

import java.util.Vector;

public class Arena {
	public class AgentStats{
		public Agent agent;
		public int wins = 0;
		public int loses = 0;
		public int draws = 0;
	}
	
	private Vector<AgentStats> contestants = new Vector<Arena.AgentStats>();
	
	public void AddAgent(Agent a){
		AgentStats ag = new AgentStats();
		ag.agent = a;
		contestants.add(ag);
	}
	
	public void Fight(int fights){
		for(int i = 0; i < contestants.size(); ++i){
			for(int j = 0; j < contestants.size(); ++j){
				if(j == i) continue;
				for(int k = 0; k < fights; ++k){
					AgentStats first = contestants.get(i);
					AgentStats second = contestants.get(j);
					Agent[] agentList = new Agent[2];
					agentList[0] = first.agent;
					agentList[1] = second.agent;
					Dungeon dungeon = new Dungeon(10, 10, 10, agentList, false);
					int res = dungeon.play(100);
					
					if(res == -1){
						first.draws++;
						second.draws++;
					}else if(res == 0){
						first.wins++;
						second.loses++;
					}else{
						first.loses++;
						second.wins++;
					}
				}
			}
		}
	}
	
	public void SaveResults(){
		
	}
	
	public void PrintResults(){
		for(int i = 0; i < contestants.size(); ++i){
			AgentStats c = contestants.get(i);
			System.out.println(c.agent.getName());
			System.out.println(c.wins + " wins");
			System.out.println(c.draws + " draws");
			System.out.println(c.loses + " loses");
		}
	}
	
	public static void TestAgent(Agent a){
		Agent[] agentList = new Agent[1];
		agentList[0] = a;
		Dungeon dungeon = new Dungeon(10, 10, 10, agentList, true);
		int res = dungeon.play(1000);
	}
}
