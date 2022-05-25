package com.scoresDei.repositories;

import com.scoresDei.data.Team;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<Team, Integer> {

    @Query("select count(*) from Game g where g.isOngoing=false and g.date < CURRENT_TIMESTAMP and (g.teamA=?1 and g.goalsA > g.goalsB or  g.teamB=?1 and g.goalsB > g.goalsA )")
    public int loadWins(Team t);

    @Query("select count(*) from Game g where g.isOngoing=false and g.date < CURRENT_TIMESTAMP and (g.teamA=?1 and g.goalsA < g.goalsB or  g.teamB=?1 and g.goalsB < g.goalsA )")
    public int loadLosses(Team t);

    @Query("select count(*) from Game g where g.isOngoing=false and g.date < CURRENT_TIMESTAMP and (g.teamA =?1 or g.teamB=?1) and g.goalsA = g.goalsB ")
    public int loadDraws(Team t);

    @Query("select t from Team t where t.id = ?1")
    public Team getTeamById(int id);

}
