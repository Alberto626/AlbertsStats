package com.example.AlbertStats.Repository;

import com.example.AlbertStats.DTOs.PlayerDetailsDTO;
import com.example.AlbertStats.Entities.PlayerMatchDetails;
import com.example.AlbertStats.Entities.MatchDetails;
import com.example.AlbertStats.Repository.RowMappers.HeroRowMapper;
import com.example.AlbertStats.Repository.RowMappers.ItemRowMapper;
import com.example.AlbertStats.Entities.Hero;
import com.example.AlbertStats.Entities.Item;
import com.example.AlbertStats.Repository.RowMappers.MatchDetailsRowMapper;
import com.example.AlbertStats.Repository.RowMappers.PlayerDetailsDtoRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class Dota2Repo implements BasicRepository {

    private final JdbcTemplate mySqlJdbcTemplate;
    private final Logger logger = Logger.getLogger(Dota2Repo.class.getName());

    public Dota2Repo(JdbcTemplate mySqlJdbcTemplate) {//get JDBC template from RepoConfig file
        this.mySqlJdbcTemplate = mySqlJdbcTemplate;
    }
    public void addHero(Hero hero) {
        String sql = "Insert into dota2.heroes values (?,?,?,?)";
        mySqlJdbcTemplate.update(sql, hero.getId(), hero.getName(), hero.getPrimaryAttribute(), listToString(hero.getRoles()));
    }
    public void addItem(Item item) {
        String sql = "Insert into dota2.items values (?,?,?,?)";
        mySqlJdbcTemplate.update(sql, item.getId(), item.getName(), item.getDescription(), item.getCost());
    }
    public void replaceHero(Hero hero) {
        String sql = "INSERT into dota2.heroes values (?, ?, ?, ?)" +
                " ON DUPLICATE KEY UPDATE id=VALUES(id), name=VALUES(name), primary_attribute=VALUES(primary_attribute), roles=VALUES(roles)"; //TODO DO THIS https://stackoverflow.com/questions/15773167/prepared-statement-syntax-for-on-duplicate-key-update-number-of-params-error
        mySqlJdbcTemplate.update(sql, hero.getId(), hero.getName(), hero.getPrimaryAttribute(), listToString(hero.getRoles()));
    }
    public void replaceItem(Item item) {
        String sql = "Insert into dota2.items values (?, ?, ?, ?)" +
                " ON DUPLICATE KEY UPDATE id=VALUES(id), name=VALUES(name), description=VALUES(description), cost=VALUES(cost) ";
        mySqlJdbcTemplate.update(sql, item.getId(), item.getName(), item.getDescription(), item.getCost());
    }
    public Hero getLatestHero() {
        String sql = "Select * from dota2.heroes order by id DESC LIMIT 1"; //grab the last record TODO find if theres a more efficient way of find it
        Hero hero = mySqlJdbcTemplate.queryForObject(sql, new HeroRowMapper());
        return hero;
    }
    public Item getLatestItem() {
        String sql = "Select * from dota2.items order by id DESC LIMIT 1";
        Item item = mySqlJdbcTemplate.queryForObject(sql, new ItemRowMapper());
        return item;
    }
    public List<Hero> getAllHeroes() {
        String sql = "Select * from dota2.items";
        List<Hero> heroes = mySqlJdbcTemplate.query(sql, new HeroRowMapper());
        return heroes;
    }
    public List<Item> getAllItems() {
        String sql = "Select * from dota2.items";
        List<Item> items = mySqlJdbcTemplate.query(sql, new ItemRowMapper());
        return items;
    }
    public boolean doesHeroRecordsExists() {
        try {
            String sql = "Select * from dota2.heroes LIMIT 1";
            Hero hero = mySqlJdbcTemplate.queryForObject(sql, new HeroRowMapper());
        }
        catch (DataAccessException e) { //if we get no record then return false
            return false;
        }
        return true;
    }
    public void addPlayerDetails(PlayerMatchDetails playerMatchDetails) {//id will be auto incremented
        try {
            String sql = "Insert into dota2.player_match_details (match_id, " +
                    "player_name, " +
                    "hero_id, " +
                    "is_radiant, " +
                    "net_worth, player_slot," +
                    "kills, deaths, assists, hero_level, hero_damage, tower_damage," +
                    "item_0_id, item_1_id, item_2_id, item_3_id, item_4_id, item_5_id," +
                    "backpack_0_id, backpack_1_id, backpack_2_id, item_neutral) VALUES" +
                    "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; //22 questions marks
            mySqlJdbcTemplate.update(sql,
                    playerMatchDetails.getMatchId().longValueExact(),
                    playerMatchDetails.getPlayerName(),
                    playerMatchDetails.getHeroId(),
                    playerMatchDetails.isRadiant(),
                    playerMatchDetails.getNetWorth(),
                    playerMatchDetails.getPlayerSlot(),
                    playerMatchDetails.getKills(),
                    playerMatchDetails.getDeaths(),
                    playerMatchDetails.getAssists(),
                    playerMatchDetails.getHeroLevel(),
                    playerMatchDetails.getHeroDamage(),
                    playerMatchDetails.getTowerDamage(),
                    playerMatchDetails.getItem0Id(),
                    playerMatchDetails.getItem1Id(),
                    playerMatchDetails.getItem2Id(),
                    playerMatchDetails.getItem3Id(),
                    playerMatchDetails.getItem4Id(),
                    playerMatchDetails.getItem5Id(),
                    playerMatchDetails.getBackpack0Id(),
                    playerMatchDetails.getBackpack1Id(),
                    playerMatchDetails.getBackpack2Id(),
                    playerMatchDetails.getItemNeutral()
            );
        }
        catch (DataAccessException e) {//TODO write an error
            logger.info("something is wrong, figure it out " + e);
        }


    }
    public void addMatchDetails(MatchDetails matchDetails) {//TODO write exception handling if an error occurs
        String sql = "Insert into dota2.match_details (match_id, radiant_victory, match_duration_seconds) values (?,?,?)";
        mySqlJdbcTemplate.update(sql, matchDetails.getMatchId().longValueExact(), matchDetails.isRadiantVictory(), matchDetails.getMatchDuration());

    }
    public boolean doesMatchExist(BigInteger matchId) {//TODO test object to make sure it works
        try {
            String sql = "Select * from dota2.match_details where match_id = ?";
            mySqlJdbcTemplate.queryForObject(sql, new MatchDetailsRowMapper(), matchId.longValueExact());
        }
        catch (DataAccessException e) {
            return false;
        }
        return true;
    }
    public boolean doesItemRecordsExists() {// check if the latest item is okay
        try {
            String sql = "Select * from dota2.items LIMIT 1";
            Item item = mySqlJdbcTemplate.queryForObject(sql, new ItemRowMapper());
        }
        catch (DataAccessException e) {
            return false;
        }
        return true;
    }
    public List<PlayerDetailsDTO> getAllMatchDetails(int page) {//this is more like pagination
        try {
            int offset = (page -1) * 50;//This will be used to get certain pages for
            String sql = "Select match_details.id, match_details.match_id, match_details.radiant_victory, match_details.match_duration_seconds,\n" +
                    "\tplayer_match_details.id as player_id, player_match_details.player_name, player_match_details.is_radiant, player_match_details.net_worth, player_match_details.player_slot, player_match_details.kills, player_match_details.deaths, player_match_details.assists, player_match_details.hero_level, player_match_details.hero_damage, player_match_details.tower_damage,\n" +
                    "\theroes.id as hero_id, heroes.name as hero_name, heroes.primary_attribute, heroes.roles,\n" +
                    "    item0.id as item0_id, item0.name as item0_name, item0.description as item0_description, item0.cost as item0cost,\n" +
                    "    item1.id as item1_id, item1.name as item1_name, item1.description as item1_description, item1.cost as item1cost,\n" +
                    "    item2.id as item0_id, item2.name as item2_name, item2.description as item2_description, item2.cost as item2cost,\n" +
                    "    item3.id as item0_id, item3.name as item3_name, item3.description as item3_description, item3.cost as item3cost,\n" +
                    "    item4.id as item0_id, item4.name as item4_name, item4.description as item4_description, item4.cost as item4cost,\n" +
                    "    item5.id as item5_id, item5.name as item5_name, item5.description as item5_description, item5.cost as item5cost,\n" +
                    "    backpack0.id as backpack0_id, backpack0.name as backpack0_name, backpack0.description as backpack0_description, backpack0.cost as backpack0_cost,\n" +
                    "    backpack1.id as backpack1_id, backpack1.name as backpack1_name, backpack1.description as backpack1_description, backpack1.cost as backpack1_cost,\n" +
                    "    backpack2.id as backpack2_id, backpack2.name as backpack2_name, backpack2.description as backpack2_description, backpack2.cost as backpack2_cost,\n" +
                    "    neutral.id as neutral_id, neutral.name as neutral_name, neutral.description as neutral_description, neutral.cost as neutral_cost\n" +
                    " from dota2.match_details\n" +
                    " inner join dota2.player_match_details on dota2.match_details.match_id = dota2.player_match_details.match_id\n" +
                    " inner join dota2.items as item0 on dota2.player_match_details.item_0_id = item0.id\n" +
                    " inner join dota2.items as item1 on dota2.player_match_details.item_1_id = item1.id\n" +
                    " inner join dota2.items as item2 on dota2.player_match_details.item_2_id = item2.id\n" +
                    " inner join dota2.items as item3 on dota2.player_match_details.item_3_id = item3.id\n" +
                    " inner join dota2.items as item4 on dota2.player_match_details.item_4_id = item4.id\n" +
                    " inner join dota2.items as item5 on dota2.player_match_details.item_5_id = item5.id\n" +
                    " inner join dota2.items as backpack0 on dota2.player_match_details.backpack_0_id = backpack0.id\n" +
                    " inner join dota2.items as backpack1 on dota2.player_match_details.backpack_1_id = backpack1.id\n" +
                    " inner join dota2.items as backpack2 on dota2.player_match_details.backpack_2_id = backpack2.id\n" +
                    " inner join dota2.items as neutral on dota2.player_match_details.item_neutral = neutral.id\n" +
                    " inner join dota2.heroes on dota2.player_match_details.hero_id = heroes.id\n" +
                    " order by match_details.id desc limit 50 \n" +
                    " offset ?;";
            List<PlayerDetailsDTO> list = mySqlJdbcTemplate.query(sql, new PlayerDetailsDtoRowMapper(), offset);
            return list;
        }
        catch(DataAccessException e) {//TODO might need to throw a 404
            logger.info("No pages here");
        }
        return null;
    }
    private String listToString(List<String> list) { //TODO check, this function might need to be its own separate class
        StringBuilder stringbuilder = new StringBuilder();
        for(String x: list) {
            stringbuilder.append(x + ",");
        }
        stringbuilder.deleteCharAt(stringbuilder.length() -1);//remove last comma
        return stringbuilder.toString();
    }

}
