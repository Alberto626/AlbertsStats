package com.example.AlbertStats.Repository;

import com.example.AlbertStats.Entities.MatchDetails;
import com.example.AlbertStats.Entities.PlayerDetails;
import com.example.AlbertStats.Repository.RowMappers.HeroRowMapper;
import com.example.AlbertStats.Repository.RowMappers.ItemRowMapper;
import com.example.AlbertStats.Entities.Hero;
import com.example.AlbertStats.Entities.Item;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class Dota2Repo implements BasicRepository {

    private final JdbcTemplate mySqlJdbcTemplate;

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
    public void addPlayerDetails() {
        String sql = "Insert into dota2.player_match_details(match_id, " +
                "player_name, " +
                "hero_id, " +
                "is_radiant, " +
                "net_worth, player_slot," +
                "kills, deaths, assists, hero_level, hero_damage, tower_damage," +
                "item_0_id, item_1_id, item_2_id, item_3_id, item_4_id, item_5_id," +
                "backpack_id_0, backpack_id_1, backpack_id_2, item_neutral) VALUES" +
                "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; //22 questions marks

    }
    public void addMatchDetails(MatchDetails matchDetails) {
        String sql = "";
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
    private String listToString(List<String> list) { //TODO check, this function might need to be its own separate class
        StringBuilder stringbuilder = new StringBuilder();
        for(String x: list) {
            stringbuilder.append(x + ",");
        }
        stringbuilder.deleteCharAt(stringbuilder.length() -1);//remove last comma
        return stringbuilder.toString();
    }

}
