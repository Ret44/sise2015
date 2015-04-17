package Game;

public class Dungeon {
	private int width;
	private int height;
	private int turn;
	private boolean verbose;

	private Chamber[][] chambers;
	private Agent[] agents;
	
	public Dungeon(int width, int height, Agent[] agents){
		this(width, height, agents, false);
	}
	
	public Dungeon(int width, int height, Agent[] agents, boolean verbose){
		chambers = new Chamber[width][height];
		this.width = width;
		this.height = height;
		this.agents = agents;
		this.verbose = verbose;
		
		generateWorld();
	}
	
	private void generateWorld(){
		// TODO do actual world generation
	}
	
	private void presentWorld(){
		// TODO do actual world presentation
	}
	
	public int passTurn(){
		for(int i = 0; i < agents.length; ++i){
			if(verbose) presentWorld();
			//agents[i].doSomething();
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
