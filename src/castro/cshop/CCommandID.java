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

import java.util.HashMap;
import java.util.NoSuchElementException;

import castro.cshop.items.ShopItem;
import castro.cshop.items.WorldEditLimitDouble;



public enum CCommandID
{
	/*
	// 0..100 <--- random items
	MOB_LIMIT      (3),
	
	// 100..1000 <--- plot related
	PLOT2   (102),
	PLOT3   (103),
	PLOT4   (104),
	PLOT5   (105),
	PLOT6   (106),
	PLOT7   (107),
	PLOT8   (108),
	PLOT9   (109),
	PLOT10  (110),
	
	PLOT_SIZE_DOUBLE (201),
	PLOT_SIZE_TRIPLE (202),
	PLOT_SIZE_PLUS_10(205),
	PLOT_SIZE_PLUS_25(204),
	
	MOB_LIMIT_100    (301),
	MOB_LIMIT_250    (302),
	MOB_LIMIT_500    (303),
	*/
	// 1000..? <--- other
	WE_LIMIT_DOUBLE  (1001, new WorldEditLimitDouble()),
	//WE_LIMIT_TRIPLE  (1002),
	//WE_LIMIT_100k    (1003),
	//WE_LIMIT_500k    (1004),
	//WE_LIMIT_2m      (1005),
	
	DUNNO_INFINITE_REMOVE_LATER_xD(-42, null);
	
	public final int id;
	public final ShopItem item;
	
	private CCommandID(int id, ShopItem item)
	{
		this.id   = id;
		this.item = item;
	}
	
	private static HashMap<Integer, CCommandID> byID = new HashMap<>();
	static {
		for(CCommandID id : values())
			byID.put(id.id, id);
	}
	
	public static CCommandID get(int id) throws NoSuchElementException
	{
		return byID.get(id);
	}
	
	public static CCommandID get(String id, String extra) throws NoSuchElementException
	{
		id = id.toLowerCase();
		switch(id)
		{
		case "welimit":
			switch(extra)
			{
			case "double": return WE_LIMIT_DOUBLE;
			}
			/*
			case "triple": return WE_LIMIT_TRIPLE;
			case "100k"  : return WE_LIMIT_100k;
			case "500k"  : return WE_LIMIT_500k;
			case "2m"    : return WE_LIMIT_2m;
			}
		case "plot":
			switch(extra)
			{
			case "2" : return PLOT2;
			case "3" : return PLOT3;
			case "4" : return PLOT4;
			case "5" : return PLOT5;
			case "6" : return PLOT6;
			case "7" : return PLOT7;
			case "8" : return PLOT8;
			case "9" : return PLOT9;
			case "10": return PLOT10;
			}
		case "mobs":
			switch(extra)
			{
			case "100": return MOB_LIMIT_100;
			case "250": return MOB_LIMIT_250;
			case "500": return MOB_LIMIT_500;
			}*/
		}
		throw new NoSuchElementException();
	}
}