/* cShop
 * Copyright (C) 2013 Norbert Kawinski (norbert.kawinski@gmail.com)

 */

package castro.cshop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import castro.base.data.SQLBase;
import castro.cshop.items.base.ShopItemId;
import castro.cshop.items.base.ShopItemData;

public class SQL extends SQLBase 
{
	private static Plugin plugin = Plugin.get;
	
	private final String TABLENAME = "cshop_active";
	
	
	public SQL()
	{
		super(plugin, true);
		
		try 
		{			
			Connection conn = getConn();
			conn.createStatement().executeUpdate(
					  "CREATE TABLE IF NOT EXISTS "+TABLENAME+"("
					+ "id      INT           NOT NULL AUTO_INCREMENT, "
					+ "nick    VARCHAR(16)   NOT NULL, "
					+ "item    INT           NOT NULL, "
					+ "extra   VARCHAR(255)  NOT NULL, "
					+ "expires TIMESTAMP     NOT NULL, "
					+ "PRIMARY KEY(id), "
					+ "UNIQUE(id) "
					+ ") ENGINE=MyIsam "
					);
			
			prepareStatements();
		}
		catch (SQLException e) 
		{
			printErrors(e);
		}
	}
	
	
	public List<ShopItemData> getExpiredItems() throws SQLException
	{
		List<ShopItemData> expiredItems = new ArrayList<>();
		PreparedStatement ps = getPreparedStatement("selectExpiredItems");
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
        	int     id     = rs.getInt   ("id");
        	String  nick   = rs.getString("nick");
        	int     itemId = rs.getInt   ("item"); 
        	String  extra  = rs.getString("extra");
        	Timestamp exp  = rs.getTimestamp("expires");
        	ShopItemData item  = new ShopItemData(id, ShopItemId.get(itemId), nick, extra, exp);
        	expiredItems.add(item);
        }
		return expiredItems;
	}
	
	
	public boolean addItem(String player, ShopItemId item, String extra, Timestamp expires) throws SQLException
	{
		PreparedStatement prep = getPreparedStatement("insertItem");
		prep.setString   (1, player);
		prep.setInt      (2, item.id);
		prep.setString   (3, extra);
		prep.setTimestamp(4, expires);
		prep.executeUpdate();
		return true;
	}
	
	public ShopItemData getItemOrNull(String playername, ShopItemId itemId)
	{
		try
		{
			return getItem(playername, itemId);
		}
		catch(Exception e)
		{
			return null;
		}
	}
	public ShopItemData getItem(String playername, ShopItemId itemId) throws SQLException
	{
		ShopItemData shopItemData = null;
		PreparedStatement ps = getPreparedStatement("selectItem");
		ps.setString(1, playername);
		ps.setInt   (2, itemId.id);
		
        ResultSet rs = ps.executeQuery();
        if(rs.next())
		{
			int     id     = rs.getInt   ("id");
			String  extra  = rs.getString("extra");
			Timestamp exp  = rs.getTimestamp("expires");
			shopItemData = new ShopItemData(id, itemId, playername, extra, exp);
		}
		rs.close();
		return shopItemData;
	}
	
	public boolean updateItem(ShopItemData item) throws SQLException
	{
		PreparedStatement prep = getPreparedStatement("updateItem");
		prep.setString   (1, item.extra);
		prep.setTimestamp(2, item.expires());
		prep.setInt      (3, item.dbId);
		prep.executeUpdate();
		return true;
	}
	
	public boolean deleteItem(String playername, ShopItemId itemId) throws SQLException
	{
		ShopItemData item = getItemOrNull(playername, itemId);
		if(item != null)
			return deleteItem(item);
		return false;
	}
	public boolean deleteItem(ShopItemData toDelete) throws SQLException
	{
		int id = toDelete.dbId;
		PreparedStatement prep = getPreparedStatement("deleteItem");
		prep.setInt(1, id);
		prep.executeUpdate();
		return true;
	}
	
	public void prepareStatements()
	{
		addStatementSQL("selectExpiredItems",
				  " SELECT * FROM "+TABLENAME
				+ " WHERE CURRENT_TIMESTAMP > expires" 
				);
		
		addStatementSQL("insertItem",
				  " INSERT INTO "+TABLENAME+"(nick, item, extra, expires)"
				+ " VALUES(?, ?, ?, ?)"
				);
		
		addStatementSQL("selectItem",
				" SELECT * FROM "+TABLENAME+
				" WHERE nick=? AND item=?"
				);
		
		addStatementSQL("updateItem",
				  " UPDATE "+TABLENAME
				+ " SET extra=?, expires=?"
				+ " WHERE id=?"
				);
		
		addStatementSQL("deleteItem",
				  " DELETE FROM " + TABLENAME
				+ " WHERE id=?"
				);
	}
}
