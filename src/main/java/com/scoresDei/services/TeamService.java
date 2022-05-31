package com.scoresDei.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.scoresDei.data.Team;
import com.scoresDei.dto.TeamDTO;
import com.scoresDei.repositories.TeamRepository;
import com.scoresDei.utils.FileUploadUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;
    private static final String TEAM_IMAGE_PATH = "/images/teams/";
    private static final String RESOURCE_STRING = "classes/static";

    public void add(Team t) {
        teamRepository.save(t);
    }

    public void addTeamWithPhoto(TeamDTO t, MultipartFile multipartFile) {
        try {
            Team tm = new Team(t.getName(), "");
            Team aid = teamRepository.save(tm);
            String[] spStr = multipartFile.getOriginalFilename().split("\\.");
            System.out.println("Array of split: " + Arrays.toString(spStr));
            FileUploadUtil.saveFile(RESOURCE_STRING + TEAM_IMAGE_PATH,
                    String.valueOf(aid.getId()) + "." + spStr[spStr.length - 1],
                    multipartFile);
            aid.setImagePath(TEAM_IMAGE_PATH + aid.getId() + "." + spStr[spStr.length - 1]);
            teamRepository.save(aid);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editTeamWithPhoto(TeamDTO t, int id, MultipartFile multipartFile) {
        try {
            Team aid = findById(id);
            if (!multipartFile.getOriginalFilename().equals("")) {

                String[] spStr = multipartFile.getOriginalFilename().split("\\.");
                System.out.println("Array of split: " + Arrays.toString(spStr));
                FileUploadUtil.saveFile(RESOURCE_STRING + TEAM_IMAGE_PATH,
                        String.valueOf(aid.getId()) + "." + spStr[spStr.length - 1],
                        multipartFile);
                aid.setImagePath(TEAM_IMAGE_PATH + aid.getId() + "." + spStr[spStr.length - 1]);
            }
            aid.setName(t.getName());
            teamRepository.save(aid);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Team findById(int id) {
        var ans = teamRepository.findById(id);
        return ans.get();
    }

    public void updateTeam(Team t) {
        teamRepository.save(t);
    }

    public ArrayList<Team> getTeams() {
        ArrayList<Team> ans = new ArrayList<>();
        for (var t : teamRepository.findAll())
            ans.add(t);
        return ans;
    }

    public ArrayList<Team> getSortedTeams() {
        ArrayList<Team> ans = new ArrayList<>();
        for (var t : teamRepository.findAll())
            ans.add(t);

        Collections.sort(ans);
        return ans;
    }

    public int getTotalGames(Team t) {
        return t.getnDraws() + t.getnWins() + t.getnLosses();
    }

    public int loadWins(Team t) {
        return teamRepository.loadWins(t);
    }

    public int loadDraws(Team t) {
        return teamRepository.loadDraws(t);
    }

    public int loadLosses(Team t) {
        return teamRepository.loadLosses(t);
    }
}
