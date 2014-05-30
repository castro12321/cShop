/* cShop
 * Copyright (C) 2013 Norbert Kawinski (norbert.kawinski@gmail.com)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
import castro.shop.items.base.ShopItemData;

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
					+ "UNIQUE(id), "
					+ "UNIQUE(nick)"
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
        	ShopItemData item  = new ShopItemData(id, CCommandID.get(itemId), nick, extra, exp);
        	expiredItems.add(item);
        }
		return expiredItems;
	}
	
	public void addItem(String player, CCommandID item, String extra, Timestamp expires) throws SQLException
	{
		PreparedStatement prep = getPreparedStatement("insertItem");
		prep.setString   (1, player);
		prep.setInt      (2, item.id);
		prep.setString   (3, extra);
		prep.setTimestamp(4, expires);
		prep.executeUpdate();
	}
	
	public ShopItemData getItem(String playername, CCommandID itemId) throws SQLException
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
	
	public void updateItem(ShopItemData item) throws SQLException
	{
		PreparedStatement prep = getPreparedStatement("updateItem");
		prep.setString   (1, item.getExtra());
		prep.setTimestamp(2, item.getExpires());
		prep.setInt      (3, item.dbId);
		prep.executeUpdate();
	}
	
	
	public void deleteItem(ShopItemData toDelete) throws SQLException
	{
		int id = toDelete.dbId;
		PreparedStatement prep = getPreparedStatement("deleteItem");
		prep.setInt(1, id);
		prep.executeUpdate();
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
				  " UPDATE TABLE "+TABLENAME
				+ " SET extra=? expires=?"
				+ " WHERE id=?"
				);
		
		addStatementSQL("deleteItem",
				  " DELETE FROM " + TABLENAME
				+ " WHERE id=?"
				);
	}
}
