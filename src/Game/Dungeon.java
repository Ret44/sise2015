package Game;

import java.util.*;

public class Dungeon {
	private int width;
	private int height;
	private int turn;
	private boolean verbose;

	private Chamber[][] chambers;
	private Agent[] agents;
	private int[][] agentPos;
	
	public Dungeon(int width, int height, Agent[] agents){
		this(width, height, agents, false);
	}
	
	public Dungeon(int width, int height, Agent[] agents, boolean verbose){
		chambers = new Chamber[width][height];
		this.width = width;
		this.height = height;
		this.agents = agents;
		this.verbose = verbose;
		this.agents = agents;
		agentPos = new int[agents.length][2];
		generateWorld();
	}
	
	private void generateWorld(){
		for(int x = 0; x < width; ++x){
			for(int y = 0; y < height; ++y){
				chambers[x][y] = new Chamber();
			}
		}
		// TODO do actual world generation
	}
	
	private void presentWorld(){
		// TODO do actual world presentation
	}
	
	public int passTurn(){
		for(int i = 0; i < agents.length; ++i){
			if(verbose) presentWorld();
			Vector<Choice> choices = new Vector<Choice>();
			int x = agentPos[i][0];
			int y = agentPos[i][1];
			Chamber agentLoc = chambers[x][y];
			for(int j = 0; j < 4; ++j){
				if(agentLoc.connections[j]){
					choices.add(Choice.values()[j]);
				}
			}
			Choice[] arr = new Choice[1];
			int decision = agents[i].decide(choices.toArray(arr));
			
		}
		return -1; // no winners this turn
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}
}
