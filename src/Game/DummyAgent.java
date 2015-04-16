package Game;

public class DummyAgent extends Agent{

	
	public DummyAgent()
	{
		this.Name = "Dummy";
	}
	
	public DummyAgent(String name)
	{
		this.Name = name;
	}
	
	@Override
	public void turn(char input) {
		switch(input)
		{
		case 'w' : System.out.println(this.Name+" moves up"); break;
		case 's' : System.out.println(this.Name+" moves down"); break;
		case 'a' : System.out.println(this.Name+" moves left"); break;
		case 'd' : System.out.println(this.Name+" moves right"); break;
		}
	}

}
