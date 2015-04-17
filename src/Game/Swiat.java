package Game;

import java.util.ArrayList;

public class Swiat {
	public Pokoj[][] pokoje;
	public Sciana[][] sciany;
	private int rozmiar;
	private ArrayList<Checker> checkers = new ArrayList<Checker>();
	public void wypisz(){
		for(int i = 0 ; i <rozmiar; i++){
			for(int j = 0; j< rozmiar; j++){
				pokoje[i][j].wypisz();
			}
		}
	}
	public Swiat(int rozmiar){
		this.rozmiar = rozmiar;
		pokoje = new Pokoj[rozmiar][rozmiar];
		tworz_sciany();
		przypisz_pokoje();
		burzenie_scian();
	}
	private void tworz_sciany(){
		sciany = new Sciana[2*rozmiar+1][];
		for(int i = 0;i<rozmiar;i++){
			sciany[0] = new Sciana[rozmiar];
			for(int j =0;j<sciany[0].length;j++){
				sciany[0][j] = new Sciana();
			}
		}
		for(int i = 1; i<rozmiar+1;i++){
			sciany[i*2-1] = new Sciana[rozmiar +1];
			for(int j =0;j<sciany[i*2-1].length;j++){
				sciany[i*2-1][j] = new Sciana();
			}
			sciany[i*2] = new Sciana[rozmiar];
			for(int j =0;j<sciany[i*2].length;j++){
				sciany[i*2][j] = new Sciana();
			}
		}
	}
	private void przypisz_pokoje(){
		for(int i = 0;i<rozmiar;i++){
			for(int j = 0;j<rozmiar;j++){				
				pokoje[i][j] = new Pokoj(sciany[i*2][j],sciany[i*2+1][j],sciany[i*2+1][j+1],sciany[i*2+2][j]);
				
			}
		}
		}
	private void burzenie_scian(){
		if(!pokoje[0][0].checked){
			checkers.add(new Checker(0,0));
		}
		for(int i =0;checkers.size()>0;){
			int x = checkers.get(i).x;
			int y = checkers.get(i).y;
			Pokoj pokoj = pokoje[x][y];
			pokoj.checked = true;
			if(x == 0){
				pokoj.pn.drzwi = false;
			}
			if(x == rozmiar -1){
				pokoj.pd.drzwi = false;
			}
			if(y == 0){
				pokoj.zach.drzwi = false;
			}
			if(y == rozmiar -1){
				pokoj.wsch.drzwi = false;
			}
			if(pokoj.pn.drzwi){
				if(!pokoje[x-1][y].checked){
					checkers.add(new Checker(x-1,y));
				}
			}
			if(pokoj.pd.drzwi){
				if(!pokoje[x+1][y].checked){
					checkers.add(new Checker(x+1,y));
				}
			}
			if(pokoj.zach.drzwi){
				if(!pokoje[x][y-1].checked){
					checkers.add(new Checker(x,y-1));
				}
			}
			if(pokoj.wsch.drzwi){
				if(!pokoje[x][y+1].checked){
					checkers.add(new Checker(x,y+1));
				}
			}
			checkers.remove(i);
		}
		if(checkers.size() == 0){
			
			for(int i = 0;i<rozmiar;i++){
				for(int j = 0;j<rozmiar;j++){
					if(!pokoje[i][j].checked){
					if(j>0){
					pokoje[i][j].zach.drzwi = true;
					}else{
						pokoje[i][j].pn.drzwi = true;
						
					}
					checkers.add(new Checker(i,j));
					burzenie_scian();
					}
				}
			}
			return;
		}
		
	}
}
