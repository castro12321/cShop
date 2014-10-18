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

package castro.cshop.items.base;

import java.util.HashMap;
import java.util.NoSuchElementException;

import castro.cshop.items.ChatColors;
import castro.cshop.items.GiveHead;
import castro.cshop.items.Hat;
import castro.cshop.items.NickColor;
import castro.cshop.items.Rabus;
import castro.cshop.items.Schematic;
import castro.cshop.items.VIP;
import castro.cshop.items.VoxelSniperAccess;
import castro.cshop.items.plots.AdditionalPlot2;
import castro.cshop.items.plots.AdditionalPlot3;
import castro.cshop.items.plots.AdditionalPlot4;
import castro.cshop.items.plots.AdditionalPlot5;
import castro.cshop.items.plots.AdditionalSave2;
import castro.cshop.items.plots.AdditionalSave3;
import castro.cshop.items.plots.AdditionalSave4;
import castro.cshop.items.plots.AdditionalSave5;
import castro.cshop.items.plots.MobLimit100;
import castro.cshop.items.plots.MobLimit250;
import castro.cshop.items.plots.MobLimit500;
import castro.cshop.items.plots.PlotSizeDouble;
import castro.cshop.items.plots.PlotSizePlus10;
import castro.cshop.items.plots.PlotSizePlus25;
import castro.cshop.items.plots.PlotSizeTriple;
import castro.cshop.items.worldedit.WorldEditLimit100k;
import castro.cshop.items.worldedit.WorldEditLimit2m;
import castro.cshop.items.worldedit.WorldEditLimit500k;
import castro.cshop.items.worldedit.WorldEditLimitDouble;



public enum ShopItemId
{
	// 0..100 <--- random items
	CHAT_COLORS(1, new ChatColors()),
	NICK_COLOR (2, new NickColor()),
	GIVE_HEAD  (3, new GiveHead()),
	HAT        (4, new Hat()),
	SCHEMATIC  (5, new Schematic()),
	VIP        (6, new VIP()),
	RABUS      (7, new Rabus()),
	
	// 100..1000 <--- plot related
	PLOT2   (102, new AdditionalPlot2()),
	PLOT3   (103, new AdditionalPlot3()),
	PLOT4   (104, new AdditionalPlot4()),
	PLOT5   (105, new AdditionalPlot5()),
	/*
	PLOT6   (106, new AdditionalPlot6()),
	PLOT7   (107, new AdditionalPlot7()),
	PLOT8   (108, new AdditionalPlot8()),
	PLOT9   (109, new AdditionalPlot9()),
	PLOT10  (110, new AdditionalPlot10()),
	*/
	
	PLOT_SIZE_DOUBLE (201, new PlotSizeDouble()),
	PLOT_SIZE_TRIPLE (202, new PlotSizeTriple()),
	PLOT_SIZE_PLUS_10(205, new PlotSizePlus10()),
	PLOT_SIZE_PLUS_25(204, new PlotSizePlus25()),
	
	MOB_LIMIT_100    (301, new MobLimit100()),
	MOB_LIMIT_250    (302, new MobLimit250()),
	MOB_LIMIT_500    (303, new MobLimit500()),
	
	SAVE_SLOT_2 (402, new AdditionalSave2()),
	SAVE_SLOT_3 (403, new AdditionalSave3()),
	SAVE_SLOT_4 (404, new AdditionalSave4()),
	SAVE_SLOT_5 (405, new AdditionalSave5()),
	
	// 1000..1100 <--- WorldEdit
	WE_LIMIT_DOUBLE  (1001, new WorldEditLimitDouble()),
	//WE_LIMIT_TRIPLE  (1002, new WorldEditLimitTriple()),
	WE_LIMIT_100k    (1003, new WorldEditLimit100k()),
	WE_LIMIT_500k    (1004, new WorldEditLimit500k()),
	WE_LIMIT_2m      (1005, new WorldEditLimit2m()),
	
	// 1000..1200 <--- VoxelSniper
	VS_ACCESS        (1100, new VoxelSniperAccess()),
	
	DUNNO_INFINITE_REMOVE_LATER_xD(-42, null);
	
	public final int id;
	public final ShopItem executor;
	
	private ShopItemId(int id, ShopItem item)
	{
		this.id   = id;
		this.executor = item;
	}
	
	private static HashMap<Integer, ShopItemId> byID = new HashMap<>();
	static {
		for(ShopItemId id : values())
			byID.put(id.id, id);
	}
	
	public static ShopItemId get(int id) throws NoSuchElementException
	{
		return byID.get(id);
	}
	
	public static ShopItemId get(String id, String extra) throws NoSuchElementException
	{
		id = id.toLowerCase();
		switch(id)
		{
		case "kolory":
		case "colors": return CHAT_COLORS;
		
		case "nickcolor":
		case "nick":   return NICK_COLOR;
		
		case "glowa":
		case "head":   return GIVE_HEAD;
		
		case "czapka":
		case "hat":    return HAT;
		
		case "vip":    return VIP;
		case "schematic":   return SCHEMATIC;
		case "voxelsniper": return VS_ACCESS;
		case "rabus":
		case "rabusa":      return RABUS;
		
		case "welimit":
			switch(extra)
			{
			case "double": return WE_LIMIT_DOUBLE;
			//case "triple": return WE_LIMIT_TRIPLE;
			case "100k"  : return WE_LIMIT_100k;
			case "500k"  : return WE_LIMIT_500k;
			case "2m"    : return WE_LIMIT_2m;
			}
		case "dzialka":
		case "plot":
			switch(extra)
			{
			case "2" : return PLOT2;
			case "3" : return PLOT3;
			case "4" : return PLOT4;
			case "5" : return PLOT5;
			}
		case "plotsize":
		{
			switch(extra)
			{
			case "double": return PLOT_SIZE_DOUBLE;
			case "triple": return PLOT_SIZE_TRIPLE;
			case "plus10": return PLOT_SIZE_PLUS_10;
			case "plus25": return PLOT_SIZE_PLUS_25;
			}
		}
		case "saveslot":
		{
			switch(extra)
			{
			case "2": return SAVE_SLOT_2;
			case "3": return SAVE_SLOT_3;
			case "4": return SAVE_SLOT_4;
			case "5": return SAVE_SLOT_5;
			}
		}
		case "mobs":
		case "moblimit":
			switch(extra)
			{
			case "100": return MOB_LIMIT_100;
			case "250": return MOB_LIMIT_250;
			case "500": return MOB_LIMIT_500;
			}
		}
		throw new NoSuchElementException();
	}
}