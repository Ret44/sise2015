package Game;

public class Pokoj {
	public Sciana pn,pd,wsch,zach;
	public Przedmiot przedmiot = null;
	public boolean checked = false;
	public short slad = 0;//0-4;
	public void wypisz(){
		System.out.println(pn.drzwi + ":" + zach.drzwi + ":" + wsch.drzwi + ":" + pd.drzwi+ ":" +checked );
	}
	public Pokoj(Sciana pn,Sciana zach,Sciana wsch,Sciana pd){
		this.pn = pn;
		this.pd = pd;
		this.wsch = wsch;
		this.zach = zach;	
	}
}
