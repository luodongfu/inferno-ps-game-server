package plugin.dialogue;

import org.arios.game.content.dialogue.DialoguePlugin;
import org.arios.game.content.dialogue.FacialExpression;
import org.arios.game.node.entity.npc.NPC;
import org.arios.game.node.entity.player.Player;

/**
 * Represents the plugin used for the fur trader.
 * @author 'Vexia
 * @version 1.0
 */
public final class FurTradePlugin extends DialoguePlugin {

    /**
     * Constructs a new {@code FurTradePlugin} {@code Object}.
     */
    public FurTradePlugin() {
	/**
	 * empty.
	 */
    }

    /**
     * Constructs a new {@code FurTradePlugin} {@code Object}.
     * @param player the player.
     */
    public FurTradePlugin(Player player) {
	super(player);
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
	return new FurTradePlugin(player);
    }

    @Override
    public boolean open(Object... args) {
	npc = (NPC) args[0];
	interpreter.sendDialogues(npc, FacialExpression.NORMAL, "Would you like to trade in fur?");
	stage = 0;
	return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
	switch (stage) {
	case 0:
	    interpreter.sendOptions("Select an Option", "Yes.", "No.");
	    stage = 1;
	    break;
	case 1:
	    switch (buttonId) {
	    case 1:
		end();
		npc.openShop(player);
		break;
	    case 2:
		interpreter.sendDialogues(player, FacialExpression.NORMAL, "No, thanks.");
		stage = 20;
		break;
	    }
	    break;
	case 20:
	    end();
	    break;
	}
	return true;
    }

    @Override
    public int[] getIds() {
	return new int[] { 573 };
    }
}