package ReplayTheSpireMod.patches;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.*;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.helpers.EventHelper;
import com.megacrit.cardcrawl.events.*;
import com.megacrit.cardcrawl.events.thebottom.*;
import com.megacrit.cardcrawl.events.thecity.*;
import com.megacrit.cardcrawl.events.thebeyond.*;
import com.megacrit.cardcrawl.events.shrines.*;

@SpirePatch(cls = "com.megacrit.cardcrawl.helpers.EventHelper", method = "getEvent")
public class ReplayEventPatch {
	/*
	public static String lastKey;
	
	@SpireInsertPatch(
		rloc=1
	)
	public static void Insert(String key)
	{
		ReplayEventPatch.lastKey = key;
	}
	*/
	public static AbstractEvent Postfix(AbstractEvent __result, String key)
	{
		switch (key) {
            case "Stuck": {
                return new Stuck();
            }
			case "Mirror Mist": {
				return new MirrorMist();
			}
			case "Trapped Chest": {
				return new TrappedChest();
			}
			case "Chaos Ring Event": {
				return new ChaosEvent();
			}
			default:
			 return __result;
		}
	}
	
}