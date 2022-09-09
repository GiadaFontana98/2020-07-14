package it.polito.tdp.PremierLeague.model;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	
	PremierLeagueDAO dao;
	Graph<Team,DefaultWeightedEdge>grafo;
	List<Team>squadra;
	
	public Model()
	{
		dao = new PremierLeagueDAO();
	}
	
    public List<Team>getTeam()
    {
    	
    	return dao.listAllTeams();
    }
    
    public List<Adiacenza>getArchi()
    {
    	return dao.listAllMatches();
    }
	public String creaGrafo()
	{
		 grafo = new SimpleDirectedWeightedGraph<Team,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		 Graphs.addAllVertices(grafo, dao.listAllTeams());
		 int peso=0;
		 for(Adiacenza a : dao.listAllMatches())
		 {
			 for(Adiacenza a1 : dao.listAllMatches())
			 {
				 if(!a.equals(a1))
				 {
					 peso=a.getPunti()-a1.getPunti();
					 if(peso!=0 && a.getPunteggio()>a1.getPunteggio() )
					 {
						 Graphs.addEdgeWithVertices(this.grafo,a1.getTeamId(),a.getTeamId(), peso);
					 }
					 else if(peso!=0 && a1.getPunteggio()>a.getPunteggio())
					 {
						 Graphs.addEdgeWithVertices(this.grafo,a.getTeamId(),a.getTeamId(), peso);
					 }
				 }
			 }
		 }
		
		
		 return  "#Numero di vertici : " + grafo.vertexSet().size()  + "Archi " + grafo.edgeSet().size();
	}
		
}