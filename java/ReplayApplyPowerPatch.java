package ReplayTheSpireMod.patches;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.rooms.*;
import ReplayTheSpireMod.*;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import basemod.*;

@SpirePatch(cls = "com.megacrit.cardcrawl.actions.common.ApplyPowerAction", method = "update")
public class ReplayApplyPowerPatch {
	
	public static void Prefix(ApplyPowerAction __instance) {
		float duration = (float)ReflectionHacks.getPrivate((Object)__instance, (Class)AbstractGameAction.class, "duration");
		float startDuration = (float)ReflectionHacks.getPrivate((Object)__instance, (Class)ApplyPowerAction.class, "startingDuration");
		if (!__instance.target.isDying && !__instance.target.isDead && duration == startDuration) {
			AbstractPower powerToApply = (AbstractPower)ReflectionHacks.getPrivate((Object)__instance, (Class)ApplyPowerAction.class, "powerToApply");
			if (ReplayTheSpireMod.BypassStupidBasemodRelicRenaming_hasRelic("Mirror") && __instance.target.isPlayer && __instance.source != null && __instance.source != __instance.target) {
				if (powerToApply.ID.equals("Weakened")) {
					ReplayTheSpireMod.BypassStupidBasemodRelicRenaming_getRelic("Mirror").flash();
					AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(__instance.source, AbstractDungeon.player, new WeakPower(__instance.source, __instance.amount, true), __instance.amount));
				}
				if (powerToApply.ID.equals("Vulnerable")) {
					ReplayTheSpireMod.BypassStupidBasemodRelicRenaming_getRelic("Mirror").flash();
					AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(__instance.source, AbstractDungeon.player, new VulnerablePower(__instance.source, __instance.amount, true), __instance.amount));
				}
			}
			
			if (!__instance.target.isPlayer && powerToApply.ID.equals("Strength") && ReplayTheSpireMod.BypassStupidBasemodRelicRenaming_hasRelic("Counterbalance")) {
				AbstractRelic cbr = ReplayTheSpireMod.BypassStupidBasemodRelicRenaming_getRelic("Counterbalance");
				if ((AbstractDungeon.getCurrRoom() instanceof MonsterRoomElite || AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss) && (__instance.amount / 2) > 0) {
					__instance.amount -= (__instance.amount / 2);
					powerToApply.amount  -= (powerToApply.amount / 2);
					if (cbr != null) {
						cbr.flash();
					}
				}
			}
		}
	}
	
}