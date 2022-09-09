package it.polito.tdp.PremierLeague.model;

public class Adiacenza implements Comparable <Adiacenza>{
	
	private int teamId;
	private int punteggio;
	private int punti;
	public Adiacenza(int teamId, int punteggio) {
		super();
		this.teamId = teamId;
		this.punteggio = punteggio;
		
	}
	
	
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public int getPunteggio() {
		return punteggio;
		
		
	}
	public int getPunti()
	{
		if(punteggio==1)
		{
			punti+=3;
		}
		else if(punteggio==-1 )
		{
			punti +=0;
		}
		else if(punteggio ==0 )
		{
			punti+= -1;
		}
		return punti;
	}
	
	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}
	@Override
	public String toString() {
		return "Adiacenza [teamId=" + teamId + ", punteggio=" + punteggio + "]";
	}


	@Override
	public int compareTo(Adiacenza o) {
		// TODO Auto-generated method stub
		return this.punteggio-o.punteggio;
	}
	
	

}
