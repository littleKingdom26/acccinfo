package info.team23h.acc.service;

import info.team23h.acc.config.Team23hException;

import java.util.HashMap;


public interface ApiService  {

	HashMap<String, Object> setRecord(HashMap<String, Object> param) throws Team23hException;

	HashMap<String, Object> playerList();

	HashMap<String, Object> setTestRecord(HashMap<String, Object> param) throws Team23hException;

}
