package it.polito.tdp.PremierLeague.model;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;



public class Model {
		PremierLeagueDAO dao;
		Graph<Team,DefaultWeightedEdge>grafo;
		List<Team>vertici;
		
	public Model()
	{
		dao = new PremierLeagueDAO();
		vertici= new ArrayList<>();
	}
	
	public String creaGrafo()
	{
		grafo = new SimpleWeightedGraph<Team, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		vertici= dao.listAllTeams();
		Graphs.addAllVertices(grafo, vertici);
		
		List<Match>partite=dao.listAllMatches();
		for(Match c : partite)
		{
			if(c.getReaultOfTeamHome()==0)
			{
				for(Team t : vertici)
				{ 
					if(t.getTeamID()==c.getTeamHomeID())
					{
					   t.pareggia();
				}

					if(t.getTeamID()==c.getTeamAwayID())
					{
					   t.pareggia();
				}
				
			  }
			}
			if(c.getReaultOfTeamHome()==1)
			{
				for(Team t : vertici)
				{ 
					if(t.getTeamID()==c.getTeamHomeID())
					{
					   t.vince();
				}
				
			  }
			}
			if(c.getReaultOfTeamHome()==-1)
			{
				for(Team t : vertici)
				{ 
					if(t.getTeamID()==c.getTeamAwayID())
					{
					   t.vince();
				}

			  }
			
			}
			
		}
		Collections.sort(vertici);
		for(Team t1 : vertici)
		{
			for(Team t2: vertici)
			{
			if(t1.getPunt()!= t2.getPunt())
			{
				Graphs.addEdgeWithVertices(grafo, t1,t2, t1.getPunt()-t2.getPunt());
			}
		  }
		}
		return "Grafo creato\n#VERTICI: " + grafo.vertexSet().size() + "\n# ARCHI: " + grafo.edgeSet().size();
		
	
     }

	public List<Team> getVertici() {
		return vertici;
	}
	public List<Team>peggiori(Team s)
	{
	   List<Team>peggiori = Graphs.neighborListOf(grafo,s);
	   Collections.sort(peggiori);
		return peggiori;
			   
			   
	}
}