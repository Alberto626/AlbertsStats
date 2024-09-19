package com.example.AlbertStats.Repository.RowMappers;

import com.example.AlbertStats.Entities.MatchDetails;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MatchDetailsRowMapper implements RowMapper<MatchDetails> {
    @Override
    public MatchDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        MatchDetails matchDetails = new MatchDetails();
        long id = rs.getLong("id");
        long matchId = rs.getLong("match_id");
        matchDetails.setId(BigInteger.valueOf(id));
        matchDetails.setMatchId(BigInteger.valueOf(matchId));
        matchDetails.setMatchDuration(rs.getInt("match_duration_seconds"));
        matchDetails.setRadiantVictory(rs.getBoolean("radiant_victory"));
        return matchDetails;
    }
}
